package cn.card.utils.Qrcode;


import cn.card.domain.Card;
import cn.card.domain.User;
import cn.card.exception.baseException.BaseException;
import cn.card.utils.propertyReader.PropertyReader;
import com.swetake.util.Qrcode;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

/**
 * Created by z on 2017/7/27.
 */
public class GenerateQRcode {

    private final static int width_static = 800;
    private final static int height_static = 483;
    //读取上传文件目录
    private static String path = PropertyReader.getUploadPath();

    /**
     *
     * @param content 二维码包含的信息
     * @param errorCorrect 设置二维码的纠错级别
     *  level L : About 7% or less errors can be corrected.
     *  level M : About 15% or less errors can be corrected.
     *  level Q : About 25% or less errors can be corrected.
     *  level H : About 30% or less errors can be corrected.
     * @param version 二维码版本
     *  Size of QRcode is defined as version.
     *  Version is from 1 to 40.
     *  Version 1 is 21*21 matrix. And 4 modules increases whenever 1 version increases.
     *  So version 40 is 177*177 matrix.
     */
   public static BufferedImage createQrcode(String content, char errorCorrect, int version) throws Exception {
       //创建Qrcodede的句柄
       Qrcode qrhand = new Qrcode();
       //设置纠错级别
       qrhand.setQrcodeErrorCorrect(errorCorrect);//qrcode官网查询
       //设置编码模式：二进制
       qrhand.setQrcodeEncodeMode('B');
       //设置版本1-40 version
       qrhand.setQrcodeVersion(version);//qrcode官网查询
       //获取图片缓冲对象
       //根据版本设置二维码图片的宽和高
       int width = 67 + 12 * (version - 1);
       int height = 67 + 12 * (version - 1);
       //创建图片对象
       BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       //获取制图工具
       Graphics2D grap = image.createGraphics();
       //设置二维码图片的背景色
       grap.setBackground(Color.WHITE);
       grap.clearRect(0, 0, width, height);
       //设置画笔颜色
       grap.setColor(Color.BLACK);
       //获取内容的字节数组
       byte[] contentBytes = content.getBytes("utf-8");
       //通过Qrcode获取二维数组  1 0
       try {
           boolean[][] codeOut = qrhand.calQrcode(contentBytes);
           //遍历二维数组，获取值，生成二维码
           for (int i = 0; i < codeOut.length; i++) {
               for (int j = 0; j < codeOut.length; j++) {
                   if (codeOut[j][i]) {//1画0不画绘制方块
                       grap.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
                   }
               }
           }
       }catch (ArrayIndexOutOfBoundsException e){
           throw new BaseException(HttpStatus.BAD_REQUEST, "你输入的信息太多了,二维码装不下了");
       }
       //释放资源
       grap.dispose();
       return image;
   }

    /**
     *
     * @param user 用户信息
     * @param card 卡片信息
     * @param background 存储在内存中的背景图片
     */
   public static BufferedImage createImage(User user,Card card, BufferedImage background) throws Exception{

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
       //将背景图片裁剪
       background = crop(background,0, 0, width_static, height_static);

       Graphics2D graphics2D = background.createGraphics();
       Graphics graphics = background.getGraphics();

       //设置透明背景 提高文字的辨识度
       graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
       graphics2D.setColor(Color.WHITE);
       graphics2D.setStroke(new BasicStroke(1f));
       graphics2D.fillRect(0, 0, 800, 483);
       graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

       int template ;

       //默认使用左样式
       if(card.getTemplate() == null){
           template = 0;
       }
       else{
           template = card.getTemplate();
       }

       //根据template不同 设置不同的画的位置
       //x控制字的位置 qrcodeX控制两个qrcode的位置
       int x = 0, qrcodeX = 0;
       switch(template) {
           //右显示
           case 1:
               x = 350;
               qrcodeX = 35;
               break;
           //左显示
           case 0:
               x = 35;
               qrcodeX = 550;
               break;
           //居中显示
           case 2:
               x = 175;
               qrcodeX = 575;
               break;
       }


       //写入内容生成二维码图片
       String content = "BEGIN:VCARD\n" +
               "VERSION:3.0\n" ;
       Integer height = 150;

       //名字
       if(card.getName() != null && card.getName()) {
           if (user.getName() != null) {
               content += "N:" + user.getName() + "\n";

               Font font = new Font("YouYuan", Font.PLAIN, 50);
               paint(font,graphics, user.getName(),x, 100);
           }
       }
       //职位
       if(card.getOccupation() != null && card.getOccupation()) {
           if (user.getOccupation() != null) {
               content += "TITLE:" + user.getOccupation() + "\n";

               height += 5;
               Font font = new Font("黑体",Font.PLAIN,25);
               height = newLine("", user.getOccupation(),graphics, font, x + 35, height, 15, graphics2D, 0);
           }
       }
       //邮箱
       if(card.getEmail() !=null && card.getEmail()) {
           if (user.getEmail() != null) {
               content += "EMAIL:" + user.getEmail() + "\n";

               height += 5;
               Font font = new Font("黑体", Font.PLAIN, 20);
               height = newLine("邮箱:", user.getEmail(), graphics, font, x + 55, height, 30, graphics2D, 0);
           }
       }
       //手机
       if(card.getPhoneMobile() != null && card.getPhoneMobile()) {
           if (user.getPhoneMobile() != null) {
               content += "TEL;TYPE=CELL,VOICE:" + user.getPhoneMobile() + "\n";

               height += 5;
               Font font = new Font("黑体", Font.PLAIN, 20);
               height = newLine("手机:", user.getPhoneMobile().toString(), graphics, font,x + 55, height, 30, graphics2D, 0);
           }
       }
       //工作电话
       if(card.getPhoneWork() != null && card.getPhoneWork()) {
           if (user.getPhoneWork() != null) {
               content += "TEL;TYPE=WORK,VOICE:" + user.getPhoneWork() + "\n";

               height += 5;
               Font font = new Font("黑体", Font.PLAIN, 20);
               height = newLine("工作电话:", user.getPhoneWork().toString(), graphics, font, x + 55 , height, 30, graphics2D, 0);
           }
       }
       //家庭电话
       if(card.getPhoneHome() != null && card.getPhoneHome()) {
           if (user.getPhoneHome() != null) {
               content += "TEL;TYPE=HOME,VOICE:" + user.getPhoneHome() + "\n";

               height += 5;
               Font font = new Font("黑体", Font.PLAIN, 20);
               height = newLine("家庭电话:", user.getPhoneHome().toString(), graphics, font, x + 55, height, 30, graphics2D, 0);
           }
       }
       //工作地址
       if(card.getAddressWork() != null && card.getAddressWork()) {
           if (user.getAddressWork() != null) {
               content += "ADR;TYPE=WORK:" + user.getAddressWork() + "\n";

               height += 5;
               Font font = new Font("黑体", Font.PLAIN, 20);
               height = newLine("工作地址:", user.getAddressWork(),graphics, font, x + 55, height, 15, graphics2D, 0);
           }
       }
       //家庭地址
       if(card.getAddressHome() !=null && card.getAddressHome()) {
           if (user.getAddressHome() != null) {
               content += "ADR;TYPE=HOME:" + user.getAddressHome() + "\n";

               height += 5;
               Font font = new Font("黑体", Font.PLAIN, 20);
               height = newLine("家庭地址:", user.getAddressHome(), graphics, font, x + 55, height, 15, graphics2D, 0);
           }
       }
       //家庭传真
       if(card.getFaxHome() != null && card.getFaxHome()) {
           if (user.getFaxHome() != null) {
               content += "TEL;TYPE=HOME,FAX:" + user.getFaxHome() + "\n";

               height += 5;
               Font font = new Font("黑体", Font.PLAIN, 20);
               height = newLine("家庭传真:", user.getFaxHome().toString(), graphics, font, x + 55, height, 30, graphics2D, 0);
           }
       }
       //工作传真
       if(card.getFaxWork() != null && card.getFaxWork()) {
           if (user.getFaxWork() != null) {
               content += "TEL;TYPE=WORK,FAX:" + user.getFaxWork() + "\n";

               height += 5;
               Font font = new Font("黑体", Font.PLAIN, 20);
               height = newLine("工作传真:", user.getFaxWork().toString(), graphics, font, x + 55, height, 30, graphics2D, 0);
           }
       }
       //url网址
       if(card.getUrl() != null) {
           if (user.getUrl() != null) {
               content += "URL:" + user.getUrl() + "\n";
               // 1 在名片上显示url字符串
               if(card.getUrl() == 1){
                   Font font = new Font("黑体", Font.PLAIN, 20);
                   if(card.getTemplate() == 2){
                       int tempX = 5 ;
                       height = newLine("",user.getUrl(), graphics, font, tempX, 450, 30, graphics2D, 1);
                   }
                   else{
                       int tempX = qrcodeX - 50 <= 0? 5: qrcodeX-50;
                       height = newLine("",user.getUrl(), graphics, font, tempX, 450, 30, graphics2D, 1);
                   }
               }
               // 2 在名片上显示url二维码
               if(card.getUrl() == 2){
                   BufferedImage urlImage = createQrcode(user.getUrl(),'L', 8);
                   urlImage = zoomInImage(urlImage, 115, 115);

                   if(card.getTemplate() == 2){
                       graphics2D.drawImage(urlImage, 40 , 310 ,
                               urlImage.getWidth(), urlImage.getHeight(), null);
                       Font font = new Font("黑体", Font.PLAIN, 15);
                       paint1(font, graphics, "主页码", 70, urlImage.getHeight() + 330);
                   }
                   else{
                       graphics2D.drawImage(urlImage, qrcodeX + 50 , 300 ,
                               urlImage.getWidth(), urlImage.getHeight(), null);
                       Font font = new Font("黑体", Font.PLAIN, 15);
                       paint1(font, graphics, "主页码", qrcodeX + 85, urlImage.getHeight() + 320);
                   }

               }
           }
       }

       content += "END:VCARD";

       BufferedImage qrcode = createQrcode(content, 'L', 15);

       qrcode = zoomInImage(qrcode, 199, 199);

       //将生成的二维码绘制在背景图片上
       graphics2D.drawImage(qrcode, qrcodeX, 50,
               qrcode.getWidth(),
               qrcode.getHeight(),
               null);

       Font font = new Font("黑体", Font.PLAIN, 15);
       paint1(font, graphics, "名片二维码", qrcodeX + 50, 70 + qrcode.getHeight());

       //绘制logo
       if(card.getLogo() != null) {
           //用户logo
           File logoFile = new File(path + "/" + card.getLogo());


           if (!logoFile.exists()) {
               throw new BaseException(HttpStatus.BAD_REQUEST, "logo文件未找到");
           }
           //将logo绘制到背景图片上
           BufferedImage logo = ImageIO.read(new FileInputStream(logoFile));
           graphics2D.drawImage(logo, card.getLogoX().intValue(), card.getLogoY().intValue(),
                   logo.getWidth(),
                   logo.getHeight(),
                   null);
       }

       graphics2D.dispose();
       graphics.dispose();

       //将内存中的生成的名片返回
       return background;
   }


    //处理换行问题的 + 处理文字背景问题 的 paint方法
    public static Integer newLine(String name, String content, Graphics graphics, Font font,
                                   Integer x , Integer y, int z, //z代表每行最多能显示文字的个数
                                    Graphics2D graphics2D, int control //control代表透明背景是否显示 1 显示 0不显示
                                    ) throws Exception{

       //获取当前字体的metrics 以便获得stringWidth
        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);

        if(content.length() > z){
            int count = (int)Math.ceil((double)content.length() / z);
            List<String> list = new ArrayList<>();
            for(int i = 0; i < count; i++) {
                if(i == count - 1){
                    list.add(content.substring(i * z, content.length()));
                }
                else {
                    list.add(content.substring(i * z, i * z + (z-1)));
                }
            }
            if(control == 1) {
                //设置透明背景 提高文字的辨识度
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
                graphics2D.setColor(Color.cyan.brighter().brighter());
                graphics2D.setStroke(new BasicStroke(1f));
                graphics2D.fillRect(x - 5, y-font.getSize(), fm.stringWidth(list.get(0)), 30);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            }
            paint1(font, graphics, name  + list.get(0), x, y);
            y += 20;
            for(int i = 1 ; i < list.size() - 1; i ++){
                if(control == 1) {
                    //设置透明背景 提高文字的辨识度
                    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
                    graphics2D.setColor(Color.cyan.brighter().brighter());
                    graphics2D.setStroke(new BasicStroke(1f));
                    graphics2D.fillRect(x + (name.length()-1) * font.getSize() + 5, y-font.getSize(), fm.stringWidth(list.get(i)), 30);
                    graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
                }
                paint1(font, graphics, list.get(i), x + (name.length()-1) * font.getSize() + 10, y);
                y += 20;
            }
        }
        else {
            if(control == 1) {
                //设置透明背景 提高文字的辨识度
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
                graphics2D.setColor(Color.cyan.brighter().brighter());
                graphics2D.setStroke(new BasicStroke(1f));
                graphics2D.fillRect(x - 5, y-font.getSize(), fm.stringWidth(name + content) + 10, 30);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            }
            paint1(font, graphics, name  + content, x, y);
            y += 30;
        }

        if(y > height_static){
            throw new BaseException(HttpStatus.BAD_REQUEST , "所选信息过多无法绘制名片");
        }

        return y;
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
   public static void paint(Font f, Graphics g, String content, int x, int y){
       GlyphVector v = f.createGlyphVector(g.getFontMetrics(f).getFontRenderContext(), content);
       Shape shape = v.getOutline();

       Graphics2D gg = (Graphics2D) g;
       //移动graphics2D原点到x,y
       gg.translate(x,y);
       // 设置“抗锯齿”的属性
       gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
       gg.setColor(Color.WHITE);
       gg.fill(shape);
       gg.setColor(Color.BLACK);
       gg.setStroke(new BasicStroke());
       gg.draw(shape);
       //将graphics2D原点重置
       gg.translate(-x,-y);
   }


   //绘制string方法1
   public static void paint1(Font f, Graphics g, String content, int x, int y){
       GlyphVector v = f.createGlyphVector(g.getFontMetrics(f).getFontRenderContext(), content);
       Shape shape = v.getOutline();


       Graphics2D gg = (Graphics2D) g;
       //移动graphics2D原点到x,y
       gg.translate(x,y);
       // 设置“抗锯齿”的属性
       gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
       gg.setColor(Color.black);
       gg.fill(shape);
       //将graphics2D原点重置
       gg.translate(-x,-y);
   }


    @Test
    public void test1() throws Exception{
       User user = new User();
       Card card = new Card();

       user.setName("1");
       user.setOccupation("2");
       user.setEmail("502982165@qq.com");
       user.setUrl("http://shi.buaa.edu.cn/buaa_luxinxi/zh_CN/index.htm");
       user.setPhoneWork(5L);
       user.setPhoneMobile(6L);
       user.setPhoneHome(7L);
       user.setAddressWork("小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小小");
       user.setAddressHome("9");
       user.setFaxHome(62100001L);
       user.setFaxWork(800L);

       card.setLogo("2.jpg");
       card.setLogoY(1.0);
       card.setLogoX(1.0);
        card.setName(true);
        card.setOccupation(true);
        card.setEmail(true);
        card.setPhoneHome(true);
        card.setAddressWork(true);
        card.setPhoneWork(true);
        card.setPhoneMobile(true);
        card.setAddressHome(true);
        card.setFaxHome(true);
        card.setFaxWork(true);
        card.setUrl(2);
        card.setTemplate(0);

        InputStream imagein = new FileInputStream("E:/uploads/2.jpg");
        BufferedImage background = ImageIO.read(imagein);

        BufferedImage image = createImage(user,card, background);

        String fileName1 = UUID.randomUUID() + ".png";
        File image1 = new File("E:" + "\\" + fileName1);
        try {
            ImageIO.write(image, "png", image1);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
