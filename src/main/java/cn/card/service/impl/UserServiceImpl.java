package cn.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.card.dao.UserMapper;
import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import cn.card.exception.UserExistException;
import cn.card.exception.UserNotFoundException;
import cn.card.service.UserService;
import cn.card.utils.Transfer;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper UserMapper;

	@Override
	public UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception {
		UserCustom userCustom = UserMapper.findUserByUserName(userQueryVo);
		Transfer.transferToList(userCustom);
		return userCustom;
	}

	@Override
	public void createNewUser(UserQueryVo userQueryVo) throws Exception {
		Transfer.transferToString(userQueryVo.getUserCustom());
		UserMapper.createNewUser(userQueryVo);
	}

	@Override
	public void updateUserInfo(UserQueryVo userQueryVo) throws Exception {
		
		Transfer.transferToString(userQueryVo.getUserCustom());
		UserMapper.updateUserInfo(userQueryVo);
	}

}
