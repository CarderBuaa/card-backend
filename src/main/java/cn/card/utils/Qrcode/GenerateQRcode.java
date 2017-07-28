package cn.card.utils.Qrcode;

import cn.card.domain.UserCustom;
import com.google.zxing.qrcode.encoder.QRCode;
import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

/**
 * Created by z on 2017/7/27.
 */
public class GenerateQRcode {

    /**
     *
     * @param userCustom 传入用户信息
     * @param path 保存图片路径
     */
   public static void Qrcode(UserCustom userCustom, String path){
       //2.创建Qrcodede的句柄
       Qrcode qrhand = new Qrcode();

       //3.设置纠错级别
       qrhand.setQrcodeErrorCorrect('M');//qrcode官网查询

       //4.设置编码模式：二进制
       qrhand.setQrcodeEncodeMode('B');

       //5.设置版本1-40 version
       qrhand.setQrcodeVersion(15);//qrcode官网查询

       //6.获取图片缓冲对象
       //定义二维码图片的宽和高
       int width=235,height=235;//15版本生成的大小
       //创建图片对象
       BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

       //7.获取制图工具
       Graphics2D grap = image.createGraphics();
       //设置二维码图片的背景色
       grap.setBackground(Color.WHITE);
       grap.clearRect(0, 0, width, height);
       //设置画笔颜色
       grap.setColor(Color.BLACK);

       //8.写入内容生成二维码图片

       String content = "BEGIN:VCARD\n" +
               "VERSION:3.0\n" +
               "N:"+ userCustom.getName() +"\n" +
               "TEL:12345678912\n" +
               "TEL;CELL:12345678912\n" +
               "ADR;WORK:"+ userCustom.getAddress_list() + "\n" +
               "TITLE:"+ userCustom.getOccupation() +"\n" +
               "EMAIL;WORK:"+ userCustom.getEmail_list() +"\n"+
               "NOTE:呼呼测试下吧。。。\n" +
               "END:VCARD";
       //释放资源
       grap.dispose();
       image.flush();
       String fileName = UUID.randomUUID()+".png";

       File imgFile = new File(path+fileName);
       try {
           ImageIO.write(image, "png", imgFile);
       }catch (Exception e){
           e.printStackTrace();
       }
   }

}
