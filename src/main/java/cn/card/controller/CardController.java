package cn.card.controller;

import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;
import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import cn.card.exception.BackgroundImageNotFound;
import cn.card.exception.CardNotFoundException;
import cn.card.service.CardService;
import cn.card.service.UserService;
import cn.card.utils.Qrcode.GenerateQRcode;
import cn.card.utils.TransferData.TransferCard;
import cn.card.utils.access_token.TokenManager;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

import cn.card.utils.propertyReader.PropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
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

    private CardService cardService;
    private TokenManager tokenManager;
    private UserService userService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //用于返回当前用户信息 用于下拉菜单用户选择自己的信息生成key-value
    @RequestMapping(value = "/card/userInfo", method = RequestMethod.GET)
    public @ResponseBody UserCustom queryUser(HttpServletRequest request) throws Exception {

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);


        //设置查找信息
        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setUsername(username);
        userQueryVo.setUserCustom(userCustom);

        return userService.findUserByUserName(userQueryVo);
    }


    //上传图片和名片信息
    @RequestMapping(value = "/card",method = RequestMethod.POST)
    public void addCard(@RequestParam(value = "image", required = false) MultipartFile image,//接收前端的图片文件
                        CardCustom cardCustom,//接收前端的名片信息
                        HttpServletResponse response, HttpServletRequest request) throws Exception{

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //设置查询条件
        CardQueryVo cardQueryVo = new CardQueryVo();
        cardCustom.setUsername(username);

        //如果有上传图片 则保存图片 并将背景图片文件名写入数据库中
        if (image != null){
            //保存文件扩展名
            String ex = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
            //重新生成UUID文件名
            String filename = UUID.randomUUID() + ex;
            //保存文件到上传目录文件
            image.transferTo(new File(path + "/" + filename));
            //保存文件名
            cardCustom.setBackground(filename);
        }
        //如果上传时没有图片 则用默认的背景
        else {
            cardCustom.setBackground("template.png");
        }
        //将前端的信息转化
        TransferCard.transferToString(cardCustom);

        //将以上信息保存
        cardQueryVo.setCardCustom(cardCustom);

        //创建新的card记录
        cardService.createRecord(cardQueryVo);

        //再将card内的其他信息保存到数据库中
        cardService.updateCardInfo(cardQueryVo);

        response.setStatus(HttpStatus.OK.value());
    }

    //用于删除生成的名片
    @RequestMapping(value = "/card/{card_id}",method = RequestMethod.DELETE)
    public void deleteCard(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("card_id") Integer card_id) throws Exception{

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //设置查询条件
        CardQueryVo cardQueryVo = new CardQueryVo();
        CardCustom cardCustom = new CardCustom();
        //必须设置cardCustom的ID
        cardCustom.setId(card_id);
        cardCustom.setUsername(username);
        cardQueryVo.setCardCustom(cardCustom);

        CardCustom check = cardService.findCardByIDAndUsername(cardQueryVo);
        //如果找不到ID的名片信息 就抛出名片不存在异常
        if(check == null){
            throw new CardNotFoundException();
        }

        //如果找到了对应的名片信息 删除名片
        cardService.deleteCard(cardQueryVo);
        response.setStatus(HttpStatus.OK.value());

    }

    //用于获得生成的名片
    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.GET)
    public void getCard(HttpServletRequest request, HttpServletResponse response,
                        @PathVariable("card_id") Integer card_id) throws Exception{
        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //设置查询条件
        CardQueryVo cardQueryVo = new CardQueryVo();
        CardCustom cardCustom = new CardCustom();
        cardCustom.setId(card_id);
        cardCustom.setUsername(username);
        cardQueryVo.setCardCustom(cardCustom);

        CardCustom check = cardService.findCardByIDAndUsername(cardQueryVo);

        //如果找不到ID的名片信息 就抛出名片不存在异常
        if(check == null){
            throw new CardNotFoundException();
        }
        //如果找到名片信息 则生成名片 并向前端返回

        //从check中获取背景图片路径
        String backgroundPath = path + "/" + check.getBackground();

        //判断背景图片是否存在
        File back = new File(backgroundPath);
        //如果背景图片不存在 则抛出异常
        if(!back.exists()){
            throw new BackgroundImageNotFound();
        }

        response.setContentType("image/png");

        //如果找到背景图片 讲背景图片放入内存中
        BufferedImage background = ImageIO.read(new FileInputStream(back));
        //在内存中生成QRcode
        BufferedImage Qrcode = GenerateQRcode.createQrcode(check);
        //在内存中生成名片
        BufferedImage card = GenerateQRcode.createImage(check, Qrcode, background);
        //将内存的名片转化成字节流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(card, "png", out);
        //获取字节流
        byte[] result = out.toByteArray();
        //将字节制流写入response中
        OutputStream stream = response.getOutputStream();
        stream.write(result);

        //释放资源
        Qrcode.flush();
        card.flush();
        stream.close();


        response.setStatus(HttpStatus.OK.value());
    }

    //用于修改已生成名片的数据
    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.PUT)
    public void putCard(HttpServletRequest request, HttpServletResponse response,
                        @PathVariable("card_id") Integer card_id,
                        @RequestBody CardCustom cardCustom//前端返回的名片信息
                        ) throws Exception{

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //设置查询条件
        CardQueryVo cardQueryVo = new CardQueryVo();
        cardCustom.setId(card_id);
        cardCustom.setUsername(username);
        cardQueryVo.setCardCustom(cardCustom);

        CardCustom check = cardService.findCardByIDAndUsername(cardQueryVo);
        //如果找不到ID的名片信息 就抛出名片不存在异常
        if(check == null){
            throw new CardNotFoundException();
        }
        //更新名片信息并且存在需要更改信息才更改
        if(cardCustom.getEmail() != null || cardCustom.getAddress() != null || cardCustom.getOccupation() != null
                || cardCustom.getPhone() != null || cardCustom.getName() != null)
            cardService.updateCardInfo(cardQueryVo);

        response.setStatus(HttpStatus.OK.value());
    }

}

