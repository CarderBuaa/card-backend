package cn.card.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.card.dao.UserMapper;
import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import cn.card.service.UserService;
import cn.card.utils.TransferData.Transfer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {


	private UserMapper userMapper;

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public UserCustom findUserByUserName(UserQueryVo userQueryVo) throws Exception {
		
		UserCustom userCustom = userMapper.findUserByUserName(userQueryVo);
		Transfer.transferToList(userCustom);
		return userCustom;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void createNewUser(UserQueryVo userQueryVo) throws Exception {
		Transfer.transferToString(userQueryVo.getUserCustom());
		userMapper.createNewUser(userQueryVo);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateUserInfo(UserQueryVo userQueryVo) throws Exception {
		Transfer.transferToString(userQueryVo.getUserCustom());
		userMapper.updateUserInfo(userQueryVo);
	}

}
