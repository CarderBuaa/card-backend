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
    public void GenerateCard(){

    }

    @RequestMapping(value = "/card/{card_id}",method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
    public void Card(@PathVariable("card_id") String card_id){

    }
}
