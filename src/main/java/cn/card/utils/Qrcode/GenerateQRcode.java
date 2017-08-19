package cn.card.utils.Qrcode;

import cn.card.domain.CardCustom;
import com.swetake.util.Qrcode;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
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
     * description:以后可以把绘制方法写在一个方法里面
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

       Graphics2D graphics2D = background.createGraphics();
       Graphics graphics = background.getGraphics();

       double x = (background.getWidth()-qrcode.getWidth()-50);
       double y = (background.getHeight()/2-qrcode.getHeight()/2);

       //设置透明度 提高文字的辨识度
       graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f));
       graphics2D.setColor(Color.WHITE);
       graphics2D.setStroke(new BasicStroke(1f));
       graphics2D.fillRect(0, 185-77, 800, 483-185+77-50 );
       graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

       //将生成的二维码绘制在背景图片上
       graphics2D.drawImage(qrcode, (int) x, (int) y,
               qrcode.getWidth() + 20,
               qrcode.getHeight() + 30,
               null);

       //用户名字
       if(cardCustom.getName() != null) {
           Font font = new Font("YouYuan", Font.PLAIN, 65);
           paint(font,graphics ,cardCustom.getName(), 35 ,185);
       }

       int height = 235;
       //用户职位
       if(cardCustom.getOccupation() != null) {
           Font font = new Font("黑体",Font.PLAIN,30);
           for (String Occupation : cardCustom.getOccupation()) {
               paint1(font, graphics, Occupation, 70, height);
               height += 40;
           }
       }
       //用户地址
       if(cardCustom.getAddress() != null) {
           height += 5;
           Font font = new Font("黑体", Font.PLAIN, 20);
           for (String Address : cardCustom.getAddress()) {
               //处理换行
               if(Address.length() > 30){
                   int count = (int)Math.ceil((double)Address.length() / 30);
                   List<String> list = new ArrayList<>();
                   for(int i = 0; i < count; i++) {
                       if(i == count - 1){
                           list.add(Address.substring(i * 30, Address.length()));
                       }
                       else {
                           list.add(Address.substring(i * 30, i * 30 + 29));
                       }
                   }
                   paint1(font, graphics, "地址:" + list.get(0), 90, height);
                   height += 20;
                   for(int i = 1 ; i < list.size() - 1; i ++){
                       paint1(font, graphics, list.get(i), 140, height);
                       height += 20;
                   }
               }
               else {
                   paint1(font, graphics, "地址:" + Address, 90, height);
                   height += 30;
               }
           }
       }
       //用户电话
       if(cardCustom.getPhone() != null) {
           height += 5;
           Font font = new Font("黑体", Font.PLAIN, 20);
           for (BigInteger Phone : cardCustom.getPhone()) {
               paint1(font, graphics, "电话:" + Phone.toString(), 90, height);
               height += 30;
           }
       }
       //用户邮箱
       if(cardCustom.getEmail() != null) {
           height += 5;
           Font font = new Font("黑体", Font.PLAIN, 20);
           for (String Email : cardCustom.getEmail()) {
               paint1(font, graphics, "邮箱:" + Email, 90, height);
               height += 30;
           }
       }

       graphics2D.dispose();
       graphics.dispose();

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

   //绘制string方法
   private static void paint(Font f, Graphics g, String content, int x, int y){
       GlyphVector v = f.createGlyphVector(g.getFontMetrics(f).getFontRenderContext(), content);
       Shape shape = v.getOutline();

       Graphics2D gg = (Graphics2D) g;
       //移动graphics2D原点到x,y
       gg.translate(x,y);
       // 设置“抗锯齿”的属性
       gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_DEFAULT);
       gg.setColor(Color.WHITE);
       gg.fill(shape);
       gg.setColor(Color.BLACK);
       gg.setStroke(new BasicStroke());
       gg.draw(shape);
       //将graphics2D原点重置
       gg.translate(-x,-y);
   }

   //绘制string方法2
   private static void paint1(Font f, Graphics g, String content, int x, int y){
       GlyphVector v = f.createGlyphVector(g.getFontMetrics(f).getFontRenderContext(), content);
       Shape shape = v.getOutline();

       Graphics2D gg = (Graphics2D) g;
       //移动graphics2D原点到x,y
       gg.translate(x,y);
       // 设置“抗锯齿”的属性
       gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_DEFAULT);
       gg.setColor(Color.black);
       gg.fill(shape);
       //将graphics2D原点重置
       gg.translate(-x,-y);
   }

   @Test
   public void test() throws IOException {
       CardCustom userCustom = new CardCustom();

       userCustom.setName("路新喜");
       List<String> Address = new ArrayList<String>(){{add("12312312312321314523154354514354353451432513451432513245143534543151435435512435345435435143514353451435143");}};
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
