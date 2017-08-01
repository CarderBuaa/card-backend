package cn.card.exception;

import cn.card.exception.baseException.BaseException;
import org.springframework.http.HttpStatus;

/**
 * Description:
 * Created by z on 2017/7/31.
 */
public class BackgroundImageNotFound extends BaseException{

    private static HttpStatus status = HttpStatus.NOT_FOUND;
    private static String message = "找不到该名片的背景图片";

    public BackgroundImageNotFound() {
        super(status, message);
    }
}
