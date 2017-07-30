package cn.card.utils.JsonResult;

import org.springframework.http.HttpStatus;

/**
 * Description:用于返回JSON结果
 * Created by z on 2017/7/28.
 */
public class JsonResult<T> {

    //返回所用信息
    private T data;
    //错误信息
    private String message;

    //成功构造器
    public JsonResult(T data){
        this.data = data;
    }

    //失败构造器
    public JsonResult(String message){
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
