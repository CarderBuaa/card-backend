package cn.card.exception;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.card.exception.baseException.BaseException;
import net.sf.json.JSONObject;

/**
 *
 * Description: 全局异常处理器
 * @author z
 * @date 2017年7月26日
 */
public class ExceptionHandler implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        ModelAndView mv = new ModelAndView();
        String message = null;

        //如果是系统自定义异常，返回异常信息
        if(ex instanceof BaseException){

            message = ex.getMessage();
            //填充HTTPStatus到response中
            response.setStatus(((BaseException) ex).getStatus().value());

            //填充message字符串到response的JSON中
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("message", message);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            OutputStream stream = null;
            try {
                try {
                    byte[] result = jsonObj.toString().getBytes("UTF-8");
                    stream = response.getOutputStream();
                    stream.write(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(stream != null){
                        stream.flush();
                        stream.close();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        //不是系统自定义异常，返回位置错误
        else {
            message = "未知错误";
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            //填充message字符串到response的JSON中
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("message", message);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            OutputStream stream = null;
            try {
                try {
                    //将字节制流写入response中
                    byte[] result = jsonObj.toString().getBytes("UTF-8");
                    stream = response.getOutputStream();
                    stream.write(result);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (stream != null) {
                        stream.flush();
                        stream.close();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return mv;
    }

}