package cn.card.service;

import cn.card.domain.User;

import java.util.List;

/**
 * 用户service类
 */
public interface UserService {
	//查询用户
	User findUserByUserName(User user) throws Exception;
	//新建用户
	void createNewUser(User user) throws Exception;
	//修改用户信息
	void updateUserInfo(User user) throws Exception;
	//根据账号和密码查询用户
	User findUserByUsernameAndPassword(User user) throws Exception;

	List<User> findAllUser() throws Exception;
}
