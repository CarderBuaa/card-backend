package cn.card.controller;

import cn.card.domain.User;
import cn.card.service.UserService;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.access_token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Description:后台管理用户
 * Created by z on 2017/8/22.
 */

@CrossOrigin
@Controller
public class ManageController {

    private UserService userService;
    private TokenManager tokenManager;

    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/allUserInfo", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUserInfo() throws Exception{
        return userService.findAllUser();
    }
}
