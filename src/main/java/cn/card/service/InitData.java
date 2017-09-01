package cn.card.service;

import cn.card.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Description:在启动时 初始化初始管理员
 * Created by z on 2017/8/23.
 */

@Component
public class InitData {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() throws Exception{
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("password");
        //设置admin角色
        admin.setRole(1);

        //构造查询条件
        User check = userService.findUserByUserName(admin);
        if(check == null) {
            userService.createNewAdmin(admin);
        }
    }
}
