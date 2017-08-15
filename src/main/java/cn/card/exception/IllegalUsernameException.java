package cn.card.exception;

import cn.card.exception.baseException.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Description:
 * Created by z on 2017/8/15.
 */
public class IllegalUsernameException extends BaseException {

    private static HttpStatus status = HttpStatus.BAD_REQUEST;
    private static String message = "用户名长度应在1到30之间";

    public IllegalUsernameException() {
        super(status, message);
    }
}
