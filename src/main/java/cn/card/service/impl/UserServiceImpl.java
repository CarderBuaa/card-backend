package cn.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.card.dao.UserMapper;
import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import cn.card.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper UserMapper;

	@Override
	public UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewUser(UserQueryVo userQueryVo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserInfo(UserQueryVo userQueryVo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
