package cn.card.service;

import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;

public interface UserService {
	//查询用户
	public UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception;
	//新建用户
	public void createNewUser(UserQueryVo userQueryVo) throws Exception;
	//修改用户信息
	public void updateUserInfo(UserQueryVo userQueryVo) throws Exception;
}
