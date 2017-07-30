package cn.card.controller;

import cn.card.domain.User;
import cn.card.domain.UserCustom;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.Qrcode.GenerateQRcode;
import cn.card.utils.access_token.TokenManager;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Properties;

import cn.card.utils.propertyReader.PropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    private TokenManager tokenManager;

    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    //MultipartFile必须与input上来的id同名,不然无法绑定
    @RequestMapping(value = "/card",method = RequestMethod.POST)
    public void generateCard(MultipartFile multipart, HttpServletResponse response,
                             HttpServletRequest request, @RequestBody UserCustom userCustom) throws Exception{

        //如果上传的时候附带了背景图片，则用背景图片生成名片
        if (multipart != null){
            //获取当前的认证用户的用户名
            String token = request.getHeader("Access-Token");
            String username = tokenManager.getUsername(token);

            //获取当前用户的目录
            String path = PropertyReader.getUploadPath()+ "/" + username;

            //将上传图片流加载如内存
            BufferedImage background = ImageIO.read(multipart.getInputStream());

            //在用户目录下生成名片
            GenerateQRcode.createImage(userCustom, GenerateQRcode.createQrcode(userCustom), background, path);

            response.setStatus(HttpStatus.OK.value());
        }
        //如果上传时没有附带背景图片 使用默认的背景图片生成

        //获取当前的认证用户的用户名
        String token = request.getHeader("Access-Token");
        String username = tokenManager.getUsername(token);

        //获取当前用户的目录
        String path = PropertyReader.getUploadPath()+ "/" + username;

        //将本地默认图片流加载如内存
        BufferedImage background = ImageIO.read(new FileInputStream(path + "template.png"));

        //在用户目录下生成名片
        GenerateQRcode.createImage(userCustom, GenerateQRcode.createQrcode(userCustom), background, path);

        response.setStatus(HttpStatus.OK.value());

    }

    @RequestMapping(value = "/card/{card_id}",method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable("card_id") String card_id){

    }

    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.GET)
    public void getCard(@PathVariable("card_id") String card_id){

    }

    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.PUT)
    public void putCard(@PathVariable("card_id") String card_id){

    }
}

