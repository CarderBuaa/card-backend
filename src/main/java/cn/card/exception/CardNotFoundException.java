package cn.card.exception;

import cn.card.exception.baseException.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Description:
 * Created by z on 2017/7/31.
 */
public class CardNotFoundException extends BaseException{
    private static HttpStatus status = HttpStatus.NOT_FOUND;
    private static String message = "找不到名片";

    public CardNotFoundException() {
        super(status, message);
    }
}
