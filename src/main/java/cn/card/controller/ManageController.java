package cn.card.controller;

import cn.card.domain.User;
import cn.card.exception.UserNotFoundException;
import cn.card.exception.baseException.BaseException;
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
    public @ResponseBody List<User> getAllUserInfo(HttpServletRequest request) throws Exception{
        //获取token的username
        String token = request.getHeader("Access-Token");
        String username_token = tokenManager.getUsername(token);

        //设置查询条件
        User user = new User();
        user.setUsername(username_token);
        User check = userService.findUserByUserName(user);

        if(check == null){
            throw new UserNotFoundException();
        }

        //如果当前用户不是管理员
        if(check.getRole() != 1){
            throw new BaseException(HttpStatus.FORBIDDEN, "当前用户无权限访问该页面");
        }

        return userService.findAllUser();
    }
}
