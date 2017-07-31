package cn.card.controller;

import cn.card.domain.UserCustom;
import cn.card.exception.CardNotFoundException;
import cn.card.service.CardService;
import cn.card.utils.Qrcode.GenerateQRcode;
import cn.card.utils.access_token.TokenManager;

import java.awt.image.BufferedImage;
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

/**
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

    //MultipartFile必须与input上来的id同名,不然无法绑定
    @RequestMapping(value = "/card",method = RequestMethod.POST)
    public void generateCard(MultipartFile image,
                             HttpServletResponse response,
                             HttpServletRequest request,
                             @RequestBody(required = false) UserCustom userCustom) throws Exception{

        //如果上传的时候附带了背景图片，则用背景图片生成名片
        if (image != null){
            //获取当前的认证用户的用户名
            String token = request.getHeader("Access-Token");
            String username = tokenManager.getUsername(token);

            //获取当前用户的目录
            String path = PropertyReader.getUploadPath()+ "\\" + username;

            //将上传图片流加载如内存
            BufferedImage background = ImageIO.read(image.getInputStream());

            //在用户目录下生成名片
            GenerateQRcode.createImage(userCustom, GenerateQRcode.createQrcode(userCustom), background, path);

            response.setStatus(HttpStatus.OK.value());
        }
        //如果上传时没有附带背景图片 使用默认的背景图片生成

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //获取当前用户的目录
        String path = PropertyReader.getUploadPath()+ "\\" + username;

        //将本地默认图片流加载如内存
        BufferedImage background = ImageIO.read(new FileInputStream(PropertyReader.getUploadPath() + "\\template.png"));

        //在用户目录下生成名片
        GenerateQRcode.createImage(userCustom, GenerateQRcode.createQrcode(userCustom), background, path);

        response.setStatus(HttpStatus.OK.value());

    }

    @RequestMapping(value = "/card/{card_id}",method = RequestMethod.DELETE)
    public void deleteCard(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("card_id") Integer card_id) throws Exception{

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //获取当前用户的目录
        String path = PropertyReader.getUploadPath()+ "\\" + username;

        File card = new File(path + "\\" + card_id + ".png");

        //如果找不到卡片则报错
        if(!card.exists()){
            throw new CardNotFoundException();
        }

        //找到卡片则删除
        card.delete();

        response.setStatus(HttpStatus.OK.value());
    }

    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.GET)
    public void getCard(HttpServletRequest request, HttpServletResponse response,
                        @PathVariable("card_id") Integer card_id) throws Exception{

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //获取当前用户的目录
        String path = PropertyReader.getUploadPath()+ "\\" + username;

        File card = new File(path + "\\" + card_id + ".png");

        //如果找不到卡片则报错
        if(!card.exists()){
            throw new CardNotFoundException();
        }

        //如果找到图片，则向前端返回图片
        response.setStatus(HttpStatus.OK.value());
    }

}

