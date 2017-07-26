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
	private UserMapper userMapper;

	@Override
	public UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception {
		
		UserCustom userCustom = userMapper.findUserByUserName(userQueryVo);
		//用户不存在 抛出用户不存在的异常
		if (userCustom == null) {
			throw new UserNotFoundException();
		}
		Transfer.transferToList(userCustom);
		return userCustom;
	}

	@Override
	public void createNewUser(UserQueryVo userQueryVo) throws Exception {
		Transfer.transferToString(userQueryVo.getUserCustom());
		userMapper.createNewUser(userQueryVo);
	}

	@Override
	public void updateUserInfo(UserQueryVo userQueryVo) throws Exception {
		Transfer.transferToString(userQueryVo.getUserCustom());
		userMapper.updateUserInfo(userQueryVo);
	}

}
