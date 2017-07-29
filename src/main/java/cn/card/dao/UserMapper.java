package cn.card.dao;

import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;

public interface UserMapper {
	
	//根据用户名查询用户
	UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception;
	//根据用户名和密码查找用户
	UserCustom findUserByUsernameAndPassword(UserQueryVo userQueryVo) throws Exception;
	//新建用户
	void createNewUser(UserQueryVo userQueryVo) throws Exception;
	//修改用户信息
	void updateUserInfo(UserQueryVo userQueryVo) throws Exception;

}
