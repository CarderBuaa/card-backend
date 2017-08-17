package cn.card.utils.Qrcode;

import cn.card.domain.CardCustom;
import cn.card.domain.UserCustom;
import com.swetake.util.Qrcode;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

/**
 * Created by z on 2017/7/27.
 */
public class GenerateQRcode {

    private final static int width_static = 800;
    private final static int height_static = 483;

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

        //全部只能显示一个
       if (cardCustom.getName() != null){
           content += "N:"+ cardCustom.getName() +"\n";
       }
       if (cardCustom.getPhone() != null && cardCustom.getPhone().size() == 1){
           content += "TEL:" + cardCustom.getPhone().get(0) + "\n";
       }
       if (cardCustom.getAddress() != null && cardCustom.getAddress().size() == 1){
           content +=  "ADR;WORK:"+ cardCustom.getAddress().get(0) + "\n" ;
       }
       if (cardCustom.getOccupation() != null && cardCustom.getOccupation().size() == 1){
           content +=  "TITLE:"+ cardCustom.getOccupation().get(0) + "\n" ;
       }
       if (cardCustom.getEmail() != null && cardCustom.getEmail().size() == 1){
           content += "EMAIL;WORK:"+ cardCustom.getEmail().get(0) + "\n" ;
       }
       content += "END:VCARD";

       //获取内容的字节数组
       byte[] contentBytes = content.getBytes("utf-8");
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
   public static BufferedImage createImage(CardCustom cardCustom, BufferedImage qrcode, BufferedImage background){

       Integer height_origin = background.getHeight();
       Integer width_origin = background.getWidth();
       //将图片等比例放大
       if(height_origin < height_static || width_origin < width_static){
           //比较两个的放大倍数
           double height_compare = (double)height_static / height_origin;
           double width_compare = (double)width_static / width_origin;
           double result = height_compare > width_compare ? height_compare : width_compare;
           background = zoomInImage(background, (int)(width_origin * Math.ceil(result)), (int)(height_origin * Math.ceil(result)));
       }
       //将图片裁剪
       background = crop(background,0, 0, width_static, height_static);

       Graphics2D g = background.createGraphics();
       double x = (background.getWidth()-qrcode.getWidth()-50);
       double y = (background.getHeight()/2-qrcode.getHeight()/2);

       //将生成的二维码绘制在背景图片上
       g.drawImage(qrcode, (int) x, (int) y,
               qrcode.getWidth() + 20,
               qrcode.getHeight() + 30,
               null);

       // 设置“抗锯齿”的属性
       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

       //打印二维码名字
       g.setFont(new Font("楷体", Font.PLAIN, 15));
       g.setColor(Color.BLACK);
       g.drawString("名片二维码", (int) x + qrcode.getWidth()/2 - 20, (int) y + qrcode.getHeight() + 50);

       //将用户信息打印在图片上
       //用户名字
       if(cardCustom.getName() != null) {
           g.setFont(new Font("楷体", Font.PLAIN, 77));
           g.setColor(Color.BLACK);
           g.drawString(cardCustom.getName(), 30, 185);
       }

       int height = 235;
       //用户职位
       if(cardCustom.getOccupation() != null) {
           g.setFont(new Font("黑体",Font.PLAIN,30));
           g.setColor(Color.BLACK);
           for (String Occupation : cardCustom.getOccupation()) {
               g.drawString(Occupation, 50, height);
               height += 40;
           }
       }
       //用户地址
       if(cardCustom.getAddress() != null) {
           height += 5;
           g.setFont(new Font("黑体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (String Address : cardCustom.getAddress()) {
               g.drawString("地址:" + Address, 60, height);
               height += 30;
           }
       }
       //用户电话
       if(cardCustom.getPhone() != null) {
           height += 5;
           g.setFont(new Font("黑体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (BigInteger Phone : cardCustom.getPhone()) {
               g.drawString("电话:" + Phone, 60, height);
               height += 30;
           }
       }
       //用户邮箱
       if(cardCustom.getEmail() != null) {
           height += 5;
           g.setFont(new Font("黑体", Font.PLAIN, 20));
           g.setColor(Color.BLACK);
           for (String Email : cardCustom.getEmail()) {
               g.drawString("邮箱:" + Email, 60, height);
               height += 30;
           }
       }
       //将内存中的生成的名片返回
       return background;
   }

   //缩放图片方法
   private static BufferedImage zoomInImage(BufferedImage originalImage, int width, int height) {
       BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());

       Graphics g = newImage.getGraphics();
       g.drawImage(originalImage, 0, 0, width, height, null);
       g.dispose();
       return newImage;
   }

   //裁剪图片方法
   private static BufferedImage crop(BufferedImage source, int startX, int startY, int endX, int endY) {
       int width = source.getWidth();
       int height = source.getHeight();

       if (startX <= -1) {
           startX = 0;
       }
       if (startY <= -1) {
           startY = 0;
       }
       if (endX <= -1) {
           endX = width - 1;
       }
       if (endY <= -1) {
           endY = height - 1;
       }
       BufferedImage result = new BufferedImage(endX, endY , source.getType());
       for (int y = startY; y < endY+startY; y++) {
           for (int x = startX; x < endX+startX; x++) {
               int rgb = source.getRGB(x, y);
               result.setRGB(x - startX, y - startY, rgb);
           }
       }
       return result;
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
       List<BigInteger> phone = new ArrayList<BigInteger>(){{add(new BigInteger("312312312"));}};
       userCustom.setPhone(phone);

       BufferedImage image = GenerateQRcode.createQrcode(userCustom);

       InputStream imagein = new FileInputStream("E:/uploads/2.jpg");
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
