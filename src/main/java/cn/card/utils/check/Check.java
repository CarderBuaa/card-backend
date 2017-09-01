package cn.card.utils.check;

/**
 * Description:
 * Created by z on 2017/8/20.
 */
import java.io.*;
public class Check
{
    public static boolean checkEmail(String email)
    {// 验证邮箱的正则表达式
        String format = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$";
        if (email.matches(format))
        {
            return true;// 邮箱名合法，返回true
        }
        else
        {
            return false;// 邮箱名不合法，返回false
        }
    }

    public static boolean checkUrl(String url)
    {// 验证邮箱的正则表达式
        url = url.toLowerCase();
        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        return url.matches(regex);
    }

    public static void main(String[] args) throws Exception
    {
        String url = "http://www.baidu.com"; // 需要进行验证的
        while(true)
        {
            url = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (Check.checkUrl(url))// 验证邮箱
            {
                System.out.println(url+"\n是合法的。");
            }
            else
            {
                System.out.println(url+"\n不是合法的。");
            }
        }
    }
}