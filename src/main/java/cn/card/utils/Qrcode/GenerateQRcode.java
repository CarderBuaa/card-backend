package cn.card.utils.Qrcode;

import cn.card.domain.CardCustom;
import cn.card.domain.UserCustom;
import com.swetake.util.Qrcode;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

/**
 * Created by z on 2017/7/27.
 */
public class GenerateQRcode {


    /**
     *
     * @param cardCustom 传入用户信息
     */
   public static BufferedImage createQrcode(CardCustom cardCustom) throws IOException {
       //创建Qrcodede的句柄
       Qrcode qrhand = new Qrcode();
       //设置纠错级别
       qrhand.setQrcodeErrorCorrect('M');//qrcode官网查询
       //设置编码模式：二进制
       qrhand.setQrcodeEncodeMode('B');
       //设置版本1-40 version
       qrhand.setQrcodeVersion(15);//qrcode官网查询
       //获取图片缓冲对象
       //定义二维码图片的宽和高
       int width=235,height=235;//15版本生成的大小
       //创建图片对象
       BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       //获取制图工具
       Graphics2D grap = image.createGraphics();
       //设置二维码图片的背景色
       grap.setBackground(Color.WHITE);
       grap.clearRect(0, 0, width, height);
       //设置画笔颜色
       grap.setColor(Color.BLACK);
       //写入内容生成二维码图片
       String content = "BEGIN:VCARD\n" +
               "VERSION:3.0\n" ;

        //电话能显示两个 其余全部只能显示一个
       if (cardCustom.getName() != null){
           content += "N:"+ cardCustom.getName() +"\n";
       }
       if (cardCustom.getPhone() != null){
           content += "TEL:" + cardCustom.getPhone().get(0) + "\n";
           if (cardCustom.getPhone().size() != 1)
           content += "TEL;CELL:" + cardCustom.getPhone().get(1) + "\n";
       }
       if (cardCustom.getAddress() != null && cardCustom.getAddress().size() == 1){
           content +=  "ADR:"+ cardCustom.getAddress().get(0) + "\n" ;
       }
       if (cardCustom.getOccupation() != null && cardCustom.getOccupation().size() == 1){
           content +=  "TITLE:"+ cardCustom.getOccupation().get(0) + "\n" ;
       }
       if (cardCustom.getEmail() != null && cardCustom.getEmail().size() == 1){
           content += "EMAIL;WORK:"+ cardCustom.getEmail().get(0) + "\n" ;
       }

       content += "END:VCARD";

       System.out.println(content);

       //获取内容的字节数组
       byte[] contentBytes = content.getBytes("gbk");
       //通过Qrcode获取二维数组  1 0
       boolean[][] codeOut = qrhand.calQrcode(contentBytes);
       //遍历二维数组，获取值，生成二维码
       for(int i=0;i<codeOut.length;i++){
           for(int j=0;j<codeOut.length;j++){
               if(codeOut[j][i]){//1画0不画绘制方块
                   grap.fillRect(j*3+2,i*3+2,3,3);
               }
           }
       }
       //释放资源
       grap.dispose();

       return image;
   }

    /**
     *
     * @param qrcode 存储在内存中的Qrcode
     * @param background 存储在内存中的背景图片
     */
   public static BufferedImage createImage(CardCustom cardCustom,BufferedImage qrcode, BufferedImage background){

       Graphics g = background.getGraphics();
       double x = (background.getWidth()-qrcode.getWidth()-50);
       double y = (background.getHeight()/2-qrcode.getHeight()/2);

       //将生成的二维码绘制在背景图片上
       g.drawImage(qrcode, (int) x, (int) y,
               qrcode.getWidth() + 20,
               qrcode.getHeight() + 30,
               null);

       //将用户信息打印在图片上
       //用户名字
       if(cardCustom.getName() != null) {
           g.setFont(new Font("楷体", Font.PLAIN, 77));
           g.setColor(Color.BLACK);
           g.drawString(cardCustom.getName(), 10, 100);
       }

       int height = 150;
       //用户职位
       if(cardCustom.getOccupation() != null) {
           g.setFont(new Font("宋体",Font.PLAIN,30));
           g.setColor(Color.BLACK);
           for (String Occupation : cardCustom.getOccupation()) {
               g.drawString(Occupation, 10, height);
               height += 30;
           }
       }
       //用户地址
       if(cardCustom.getAddress() != null) {
           height += 5;
           g.setFont(new Font("宋体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (String Address : cardCustom.getAddress()) {
               g.drawString("地址:" + Address, 20, height);
               height += 20;
           }
       }
       //用户电话
       if(cardCustom.getPhone() != null) {
           height += 5;
           g.setFont(new Font("宋体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (String Phone : cardCustom.getPhone()) {
               g.drawString("电话:" + Phone, 20, height);
               height += 20;
           }
       }
       //用户邮箱
       if(cardCustom.getEmail() != null) {
           height += 5;
           g.setFont(new Font("宋体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (String Email : cardCustom.getEmail()) {
               g.drawString("邮箱:" + Email, 20, height);
               height += 20;
           }
       }
       //将内存中的生成的名片返回
       return background;
   }

   @Test
   public void test() throws IOException {
       CardCustom userCustom = new CardCustom();

       userCustom.setName("sb");
       List<String> Address = new ArrayList<String>(){{add("地址1");}};
       userCustom.setAddress(Address);
       List<String> Email = new ArrayList<String>(){{add("a502982165@qq.com");}};
       userCustom.setEmail(Email);
       List<String> Occupation = new ArrayList<String>(){{add("职位1");}};
       userCustom.setOccupation(Occupation);
       List<String> phone = new ArrayList<String>(){{add("1232321312312");}};
       userCustom.setPhone(phone);

       BufferedImage image = GenerateQRcode.createQrcode(userCustom);

       InputStream imagein = new FileInputStream("E:\\1.png");
       BufferedImage background = ImageIO.read(imagein);

       BufferedImage result = GenerateQRcode.createImage(userCustom, image, background);


       //保存图片
       String fileName1 = UUID.randomUUID() + ".png";
       File image1 = new File("E:" + "\\" + fileName1);
       try {
           ImageIO.write(result, "png", image1);
       }catch (Exception e)
       {
           e.printStackTrace();
       }

   }

}
