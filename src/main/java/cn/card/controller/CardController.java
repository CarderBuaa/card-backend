package cn.card.controller;

import cn.card.domain.Card;
import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;
import cn.card.domain.UserCustom;
import cn.card.exception.BackgroundImageNotFound;
import cn.card.exception.CardNotFoundException;
import cn.card.service.CardService;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.Qrcode.GenerateQRcode;
import cn.card.utils.access_token.TokenManager;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

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

/**
 * Description: 控制名片行为的controller
 * Created by z on 2017/7/27.
 */
@Controller
public class CardController {

    private CardService cardService;
    private TokenManager tokenManager;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    //通过key-value的形式来绑定
    @IgnoreSecurity
    @RequestMapping(value = "/card",method = RequestMethod.POST)
    public void generateCard(@RequestParam(value = "image", required = false) MultipartFile image,
                             CardCustom cardCustom,
                             HttpServletResponse response, HttpServletRequest request) throws Exception{

        //如果有上传图片 则保存图片
        if (image != null){
//            //获取当前的认证用户的用户名
//            String token = request.getHeader("Access-Token");
//            String username = tokenManager.getUsername(token);
//
//            //获取当前用户的目录
//            String path = PropertyReader.getUploadPath()+ "\\" + username;
//
//            //将上传图片流加载如内存
//            BufferedImage background = ImageIO.read(image.getInputStream());
//
//            //在用户目录下生成名片c
//            GenerateQRcode.createImage(userCustom, GenerateQRcode.createQrcode(userCustom), background);
//
//            response.setStatus(HttpStatus.OK.value());
            //获取上传图片的目录
            String upload = PropertyReader.getUploadPath();
            System.out.println(cardCustom.getOccupation());

            System.out.println(image.getOriginalFilename());
            response.setStatus(HttpStatus.OK.value());
        }
        //如果上传时没有图片 则用刚刚上传的图片和现在上传的用户信息 生成名片
        System.out.println(cardCustom.getOccupation());

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //获取当前用户的目录
        String path = PropertyReader.getUploadPath()+ "\\" + username;

        //将本地默认图片流加载如内存
        BufferedImage background = ImageIO.read(new FileInputStream(PropertyReader.getUploadPath() + "\\template.png"));

        //在用户目录下生成名片
//        GenerateQRcode.createImage(userCustom, GenerateQRcode.createQrcode(userCustom), background);

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

        System.out.println(card_id);
        System.out.println(username);

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
        String path = PropertyReader.getUploadPath() + "\\" + check.getBackground();

        //判断背景图片是否存在
        File back = new File(path);
        //如果背景图片不存在 则抛出异常
        if(!back.exists()){
            throw new BackgroundImageNotFound();
        }
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
        response.setContentType("image/png");
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
        //如果找到名片信息 则将前端返回的名片信息写进cardCustom中
        cardService.updateCardInfo(cardQueryVo);

        response.setStatus(HttpStatus.OK.value());

    }

}

