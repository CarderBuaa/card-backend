package cn.card.controller;

import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;
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
            GenerateQRcode.createImage(userCustom, GenerateQRcode.createQrcode(userCustom), background);

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
        GenerateQRcode.createImage(userCustom, GenerateQRcode.createQrcode(userCustom), background);

        response.setStatus(HttpStatus.OK.value());

    }

    //用于删除生成的名片
    @RequestMapping(value = "/card/{card_id}",method = RequestMethod.DELETE)
    public void deleteCard(HttpServletResponse response, @PathVariable("card_id") Integer card_id) throws Exception{

        //设置查询条件
        CardQueryVo cardQueryVo = new CardQueryVo();
        CardCustom cardCustom = new CardCustom();
        cardCustom.setId(card_id);

        CardCustom check = cardService.findCardByID(cardQueryVo);
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
    public void getCard(HttpServletResponse response, @PathVariable("card_id") Integer card_id) throws Exception{
        //设置查询条件
        CardQueryVo cardQueryVo = new CardQueryVo();
        CardCustom cardCustom = new CardCustom();
        cardCustom.setId(card_id);

        CardCustom check = cardService.findCardByID(cardQueryVo);
        //如果找不到ID的名片信息 就抛出名片不存在异常
        if(check == null){
            throw new CardNotFoundException();
        }
        //如果找到名片信息 则生成名片 并向前端返回

        //TODO:将内存中的名片信息转换成二进制流 像前端返回

        response.setStatus(HttpStatus.OK.value());

    }

    //用于修改已生成名片的数据
    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.PUT)
    public void putCard(HttpServletResponse response, @PathVariable("card_id") Integer card_id) throws Exception{

        //设置查询条件
        CardQueryVo cardQueryVo = new CardQueryVo();
        CardCustom cardCustom = new CardCustom();
        cardCustom.setId(card_id);

        CardCustom check = cardService.findCardByID(cardQueryVo);
        //如果找不到ID的名片信息 就抛出名片不存在异常
        if(check == null){
            throw new CardNotFoundException();
        }
        //如果找到名片信息 则将前端返回的名片信息写进cardCustom中
        //TODO:将名片信息修改

        cardService.updateCardInfo(cardQueryVo);

        response.setStatus(HttpStatus.OK.value());

    }

}

