package cn.card.controller;

import cn.card.domain.Card;
import cn.card.domain.User;
import cn.card.exception.UserNotFoundException;
import cn.card.exception.baseException.BaseException;
import cn.card.service.CardService;
import cn.card.service.UserService;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.access_token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:后台管理用户
 * Created by z on 2017/8/22.
 */

@CrossOrigin
@Controller
public class ManageController {

    private UserService userService;
    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/manage/allUserInfo", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUserInfo() throws Exception{
        return userService.findAllUser();
    }

    @RequestMapping(value = "/manage/allCardInfo", method = RequestMethod.GET)
    public @ResponseBody List<Card> getAllCardInfo() throws Exception{
        return cardService.findAllCard();
    }
}
