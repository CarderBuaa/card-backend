package cn.card.controller;

import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by z on 2017/7/27.
 */
@Controller
public class CardController {

    @IgnoreSecurity
    //MultipartFile必须与input上来的id同名,不然无法绑定
    @RequestMapping(value = "/card",method = RequestMethod.POST)
    public void generateCard(MultipartFile multipart, HttpServletResponse response) throws Exception{

        if (multipart != null){

            //获取当前环境的根目录
            String path = this.getClass().getClassLoader().getResource("/").getPath();

            //图片扩展名
            String ex = multipart.getOriginalFilename().substring(multipart.getOriginalFilename().lastIndexOf("."));
            //图片名称

        }
        response.setStatus(HttpStatus.OK.value());

    }

    @RequestMapping(value = "/card/{card_id}",method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable("card_id") String card_id){

    }

    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.GET)
    public void getCard(@PathVariable("card_id") String card_id){

    }

    @RequestMapping(value = "/card/{card_id}", method = RequestMethod.PUT)
    public void putCard(@PathVariable("card_id") String card_id){

    }

}

