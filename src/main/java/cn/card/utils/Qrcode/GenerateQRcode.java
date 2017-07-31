package cn.card.utils.Qrcode;

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

    public static Integer count = 1;

    /**
     *
     * @param userCustom 传入用户信息
     */
   public static BufferedImage createQrcode(UserCustom userCustom) throws IOException {
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


       if (userCustom.getName() != null){
           content += "N:"+ userCustom.getName() +"\n";
       }
       if (userCustom.getPhone() != null){
           content += "TEL:" + userCustom.getPhone().get(0) + "\n";
           if (userCustom.getPhone().size() != 1)
           content += "TEL;CELL:" + userCustom.getPhone().get(1) + "\n";
       }
       if (userCustom.getAddress() != null && userCustom.getAddress().size() == 1){
           content +=  "ADR:"+ userCustom.getAddress().get(0) + "\n" ;
       }
       if (userCustom.getOccupation() != null && userCustom.getOccupation().size() == 1){
           content +=  "TITLE:"+ userCustom.getOccupation().get(0) + "\n" ;
       }
       if (userCustom.getEmail() != null && userCustom.getEmail().size() == 1){
           content += "EMAIL;WORK:"+ userCustom.getEmail().get(0) + "\n" ;
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
     * @param path 保存文件的路径包含全路径
     */
   public static void createImage(UserCustom userCustom,BufferedImage qrcode, BufferedImage background, String path){

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
       if(userCustom.getName() != null) {
           g.setFont(new Font("楷体", Font.PLAIN, 77));
           g.setColor(Color.BLACK);
           g.drawString(userCustom.getName(), 10, 100);
       }

       int height = 150;
       //用户职位
       if(userCustom.getOccupation() != null) {
           g.setFont(new Font("宋体",Font.PLAIN,30));
           g.setColor(Color.BLACK);
           for (String Occupation : userCustom.getOccupation()) {
               g.drawString(Occupation, 10, height);
               height += 30;
           }
       }
       //用户地址
       if(userCustom.getAddress() != null) {
           height += 5;
           g.setFont(new Font("宋体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (String Address : userCustom.getAddress()) {
               g.drawString("地址:" + Address, 20, height);
               height += 20;
           }
       }
       //用户电话
       if(userCustom.getPhone() != null) {
           height += 5;
           g.setFont(new Font("宋体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (String Phone : userCustom.getPhone()) {
               g.drawString("电话:" + Phone, 20, height);
               height += 20;
           }
       }
       //用户邮箱
       if(userCustom.getEmail() != null) {
           height += 5;
           g.setFont(new Font("宋体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (String Email : userCustom.getEmail()) {
               g.drawString("邮箱:" + Email, 20, height);
               height += 20;
           }
       }
       //保存图片
       String fileName1 = count + ".png";
       File image = new File(path + "\\" +fileName1);
       try {
           ImageIO.write(background, "png", image);
           count++;
       }catch (Exception e)
       {
           e.printStackTrace();
       }
   }

   @Test
   public void test() throws IOException {
       UserCustom userCustom = new UserCustom();

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

       GenerateQRcode.createImage(userCustom, image, background, "E:");

   }

}
