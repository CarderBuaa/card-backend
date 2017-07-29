package cn.card.exception;

import cn.card.exception.baseException.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Description:用户账号密码错误
 * Created by z on 2017/7/29.
 */
public class PawordWrongException extends BaseException{

    private static HttpStatus status = HttpStatus.BAD_REQUEST;
    private static String message = "用户账号密码错误";

    public PawordWrongException() {
        super(status, message);
    }
}
