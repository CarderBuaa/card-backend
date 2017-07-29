package cn.card.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by z on 2017/7/27.
 */
@Controller
public class CardController {

    @RequestMapping(value = "/card",method = RequestMethod.POST)
    public void generateCard(){

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

