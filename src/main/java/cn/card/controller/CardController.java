package cn.card.controller;


import cn.card.domain.Card;
import cn.card.domain.User;
import cn.card.exception.BackgroundImageNotFound;
import cn.card.exception.CardNotFoundException;
import cn.card.exception.baseException.BaseException;
import cn.card.service.CardService;
import cn.card.service.UserService;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.Qrcode.GenerateQRcode;
import cn.card.utils.access_token.TokenManager;

import java.awt.image.BufferedImage;
import java.io.*;

import cn.card.utils.propertyReader.PropertyReader;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Description: 控制名片行为的controller
 * Created by z on 2017/7/27.
 */
//添加跨域请求支持
@CrossOrigin
@Controller
public class CardController {


    private static String path = PropertyReader.getUploadPath();

    private UserService userService;
    private CardService cardService;
    private TokenManager tokenManager;
    private JedisPool jedisPool;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }


    //上传图片和名片信息
    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public void addCard(@RequestParam(value = "image", required = false) MultipartFile image,//接收前端的图片文件
                        @RequestParam(value = "logoImage", required = false) MultipartFile logoImage, //接受前端上传的logo文件
                        Card card,//接收前端的名片信息
                        HttpServletResponse response, HttpServletRequest request) throws Exception{

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //设置查询条件
        card.setUsername(username);

        if(card.getTemplate() == null || (card.getTemplate() != 0 && card.getTemplate() != 1 && card.getTemplate() != 2)){
            throw new BaseException(HttpStatus.BAD_REQUEST, "名片格式信息错误");
        }

        //如果有上传图片 则保存图片 并将背景图片文件名写入数据库中
        if(image != null) {
            //保存文件扩展名
            String ex = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
            //重新生成UUID文件名
            String filename = UUID.randomUUID() + ex;
            //保存文件到上传目录文件
            image.transferTo(new File(path + "/" + filename));
            //保存文件名
            card.setBackground(filename);
        }
        //如果上传时没有背景图片 则用默认的背景
        else {
            card.setBackground("template.png");
        }

        if(logoImage != null){
            //保存文件扩展名
            String ex = logoImage.getOriginalFilename().substring(logoImage.getOriginalFilename().lastIndexOf("."));
            //重新生成UUID文件名
            String filename = UUID.randomUUID() + ex;
            //保存文件到上传目录文件
            logoImage.transferTo(new File(path + "/" + filename));
            //保存文件名
            card.setLogo(filename);

            //设置默认logoImage位置为(0,0) 左上角
            if(card.getLogoX() == null){
                card.setLogoX(0D);
            }
            if(card.getLogoY() == null){
                card.setLogoY(0D);
            }
        }
        //card的name始终为true
        card.setName(true);

        //查找user对象
        User userFind = new User();
        userFind.setUsername(username);

        User user = userService.findUserByUserName(userFind);

        //获取背景图片路径
        String backgroundPath = path + "/" + card.getBackground();

        //判断背景图片是否存在
        File back = new File(backgroundPath);
        //如果背景图片不存在 则抛出异常
        if (!back.exists()) {
            throw new BackgroundImageNotFound();
        }

        //读取图片
        BufferedImage background =ImageIO.read(new FileInputStream(back));
        //生成图片
        BufferedImage cardImage = GenerateQRcode.createImage(user, card, background);

        //创建新的card记录
        cardService.createRecord(card);
        Integer card_id = card.getId();
        //如果能够生成名片 将其放入redis中
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();

            //将内存的名片转化成字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(cardImage, "png", out);
            //获取字节流
            byte[] result = out.toByteArray();
            //将字节数组放入redis中
            jedis.set(("card_" + card_id.toString()).getBytes(), result);
        }
        //释放资源
        finally {
            if(jedis != null){
                jedis.close();
            }
            background.flush();
            cardImage.flush();
        }
        response.setStatus(HttpStatus.OK.value());
    }


    //用于修改已生成名片的数据
    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.POST)
    public void putCard(HttpServletRequest request, HttpServletResponse response,
                        Card card, //前端返回的名片信息
                        @PathVariable("card_id") Integer card_id,
                        @RequestParam(value = "image", required = false) MultipartFile image,//接收前端的图片文件
                        @RequestParam(value = "logoImage", required = false) MultipartFile logoImage//接受前端上传的logo文件
                        ) throws Exception{

        //名片格式信息不对
        if(card.getTemplate() != null){
            if(card.getTemplate() != 0 && card.getTemplate() != 1 && card.getTemplate() != 2)
                throw new BaseException(HttpStatus.BAD_REQUEST, "名片格式信息错误");
        }

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //设置查询条件
        card.setId(card_id);
        card.setUsername(username);

        Card check = cardService.findCardByIDAndUsername(card);
        //找不到ID的名片信息
        if(check == null){
            throw new CardNotFoundException();
        }

        //如果有上传图片 则保存图片 并将背景图片文件名写入card中
        if(image != null) {
            //保存文件扩展名
            String ex = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
            //重新生成UUID文件名
            String filename = UUID.randomUUID() + ex;
            //保存文件到上传目录文件
            image.transferTo(new File(path + "/" + filename));
            //保存文件名
            card.setBackground(filename);
        }

        if(logoImage != null){
            //保存文件扩展名
            String ex = logoImage.getOriginalFilename().substring(logoImage.getOriginalFilename().lastIndexOf("."));
            //重新生成UUID文件名
            String filename = UUID.randomUUID() + ex;
            //保存文件到上传目录文件
            logoImage.transferTo(new File(path + "/" + filename));
            //保存文件名
            card.setLogo(filename);

            //设置默认logoImage位置为(0,0) 左上角
            if(card.getLogoX() == null){
                card.setLogoX(0D);
            }
            if(card.getLogoY() == null){
                card.setLogoY(0D);
            }
        }
        //card的name始终为true
        card.setName(true);

        //删除背景文件
        if(check.getBackground() != null && !check.getBackground().equals("template.png")){
            //从check中获取背景图片路径
            String backgroundPath = path + "/" + check.getBackground();
            File backgroundFile = new File(backgroundPath);
            if(backgroundFile.exists()){
                backgroundFile.delete();
            }
        }
        //删除logo文件
        if(check.getLogo() != null){
            String logoPath = path + "/" + check.getLogo();
            File logoFile = new File(logoPath);
            if(logoFile.exists()){
                logoFile.delete();
            }
        }

        //查找user对象
        User userFind = new User();
        userFind.setUsername(username);

        User user = userService.findUserByUserName(userFind);

        //获取背景图片路径
        String backgroundPath = path + "/" + card.getBackground();

        //判断背景图片是否存在
        File back = new File(backgroundPath);
        //如果背景图片不存在 则抛出异常
        if (!back.exists()) {
            throw new BackgroundImageNotFound();
        }

        //读取图片
        BufferedImage background =ImageIO.read(new FileInputStream(back));
        //生成图片
        BufferedImage cardImage = GenerateQRcode.createImage(user, card, background);
        //更新用户信息
        cardService.updateCardInfo(card);

        Jedis jedis = null;
        //修改信息后 保存在redis中的名片信息应该删除 同时将新的图片信息保存
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(("card_" + card_id.toString()).getBytes())) {
                jedis.del(("card_" + card_id.toString()).getBytes());
            }
            //将内存的名片转化成字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(cardImage, "png", out);
            //获取字节流
            byte[] result = out.toByteArray();
            //将字节数组放入redis中
            jedis.set(("card_" + card_id.toString()).getBytes(), result);

        }
        //释放资源
        finally {
            if(jedis != null){
                jedis.close();
            }
            background.flush();
            cardImage.flush();
        }
    }


    //用于删除生成的名片
    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.DELETE)
    public void deleteCard(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("card_id") Integer card_id) throws Exception{

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //设置查询条件
        Card card = new Card();
        //必须设置cardCustom的ID
        card.setId(card_id);
        card.setUsername(username);

        Card check = cardService.findCardByIDAndUsername(card);
        //如果找不到ID的名片信息 就抛出名片不存在异常
        if(check == null){
            throw new CardNotFoundException();
        }

        //删除redis中名片信息
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(("card_" + card_id.toString()).getBytes())) {
                jedis.del(("card_" + card_id.toString()).getBytes());
            }
        }finally {
            //释放资源
            if(jedis != null)
                jedis.close();
        }

        //如果找到了对应的名片信息 删除名片
        cardService.deleteCard(check);
        //删除背景文件
        if(check.getBackground() != null && !check.getBackground().equals("template.png")){
            //从check中获取背景图片路径
            String backgroundPath = path + "/" + check.getBackground();
            File backgroundFile = new File(backgroundPath);
            if(backgroundFile.exists()){
                backgroundFile.delete();
            }
        }
        //删除logo文件
        if(check.getLogo() != null){
            String logoPath = path + "/" + check.getLogo();
            File logoFile = new File(logoPath);
            if(logoFile.exists()){
                logoFile.delete();
            }
        }
        response.setStatus(HttpStatus.OK.value());
    }

    //用于获得生成的名片
    @IgnoreSecurity
    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.GET)
    public void getCard(HttpServletResponse response,
                        @PathVariable("card_id") Integer card_id,
                        @RequestParam("username") String username
                        ) throws Exception{
        //设置查询条件
        Card card = new Card();
        User userFind = new User();
        userFind.setUsername(username);
        card.setId(card_id);
        card.setUsername(username);

        //获取名片信息同时防止其他的人去访问不属于自己的名片
        Card check = cardService.findCardByIDAndUsername(card);

        Jedis jedis = null;
        try {
            //获取jedis对象
            jedis = jedisPool.getResource();

            //redis中已经缓存了图片
            if (jedis.exists(("card_" + card_id.toString()).getBytes())) {

                response.reset();
                response.setContentType("image/png");
                byte[] response_image = jedis.get(("card_" + card_id.toString()).getBytes());
                //如果需要对图片压缩用此可方便实现
                Thumbnails.of(new ByteArrayInputStream(response_image)).scale(1f).outputFormat("png").toOutputStream(response.getOutputStream());
            }
            //redis中没有缓存图片,则通过方法生成图片并返回，并且将图片字节数组放入redis中缓存
            else {
                //获取用户信息
                User user = userService.findUserByUserName(userFind);
                //从check中获取背景图片路径
                String backgroundPath = path + "/" + check.getBackground();

                //判断背景图片是否存在
                File back = new File(backgroundPath);
                //如果背景图片不存在 则抛出异常
                if (!back.exists()) {
                    throw new BackgroundImageNotFound();
                }

                response.reset();
                response.setContentType("image/png");

                //如果找到背景图片 讲背景图片放入内存中
                BufferedImage background = ImageIO.read(new FileInputStream(back));
                //在内存中生成名片
                BufferedImage cardImage = GenerateQRcode.createImage(user, check, background);

                Thumbnails.of(cardImage).scale(1f).outputFormat("png").toOutputStream(response.getOutputStream());
                //将内存的名片转化成字节流
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(cardImage, "png", out);
                //获取字节流
                byte[] result = out.toByteArray();
                //将字节制流写入response中

                //将字节数组放入redis中
                jedis.set(("card_" + card_id.toString()).getBytes(), result);

                //释放资源
                cardImage.flush();
                background.flush();
            }
        }
        finally {
            //释放redis资源
            if(jedis != null)
                jedis.close();
        }
    }
}

