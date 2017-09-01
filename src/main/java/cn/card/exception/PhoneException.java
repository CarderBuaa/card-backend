package cn.card.exception;

import cn.card.exception.baseException.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Description:电话长度应该小于等于11位
 * Created by z on 2017/8/16.
 */
public class PhoneException extends BaseException{

    private static HttpStatus status = HttpStatus.BAD_REQUEST;
    private static String message = "号码长度不能超过11位";

    public PhoneException() {
        super(status, message);
    }
}
