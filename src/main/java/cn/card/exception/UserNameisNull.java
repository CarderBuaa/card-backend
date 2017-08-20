package cn.card.exception;

import cn.card.exception.baseException.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Description:
 * Created by z on 2017/7/29.
 */
public class UserNameisNull extends BaseException {

    private static HttpStatus status = HttpStatus.BAD_REQUEST;

    public UserNameisNull(String message) {
        super(status, message);
    }
}
