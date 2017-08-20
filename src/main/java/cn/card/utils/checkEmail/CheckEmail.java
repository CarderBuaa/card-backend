package cn.card.utils.checkEmail;

/**
 * Description:
 * Created by z on 2017/8/20.
 */
import java.io.*;
public class CheckEmail
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

    public static void main(String[] args) throws Exception
    {
        String email = "cc**365@163.com"; // 需要进行验证的邮箱
        while(true)
        {
            email = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (CheckEmail.checkEmail(email))// 验证邮箱
            {
                System.out.println(email+"\n是合法的邮箱名。");
            }
            else
            {
                System.out.println(email+"\n不是合法的邮箱名。");
            }
        }
    }
}