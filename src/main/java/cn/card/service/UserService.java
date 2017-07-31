package cn.card.service;

import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import org.springframework.stereotype.Service;

public interface UserService {
	//查询用户
	UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception;
	//新建用户
	void createNewUser(UserQueryVo userQueryVo) throws Exception;
	//修改用户信息
	void updateUserInfo(UserQueryVo userQueryVo) throws Exception;
	//根据账号和密码查询用户
	UserCustom findUserByUsernameAndPassword(UserQueryVo userQueryVo) throws Exception;
}
