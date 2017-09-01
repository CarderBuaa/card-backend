package cn.card.service.impl;

import cn.card.dao.UserMapper;
import cn.card.domain.User;
import cn.card.domain.UserExample;
import cn.card.exception.IllegalUsernameException;
import cn.card.exception.PawordWrongException;
import cn.card.exception.PhoneException;
import cn.card.exception.UserNameisNull;
import cn.card.exception.baseException.BaseException;
import cn.card.utils.GenerateMD5.MD5;
import cn.card.utils.check.Check;
import org.springframework.beans.factory.annotation.Autowired;

import cn.card.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {


	private UserMapper userMapper;

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public User findUserByUserName(User user) throws Exception {
	    //从上层传递的username一定不能为空
	    if(user.getUsername() == null || user.getUsername().equals("")){
	        throw new UserNameisNull("用户名不能为空");
        }

		return userMapper.selectByPrimaryKey(user.getUsername());
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void createNewUser(User user) throws Exception {
	    //用户名、密码、邮箱、手机均为非空选项
        if((user.getUsername() == null || user.getUsername().equals("")) ||
                (user.getPassword() == null || user.getPassword().equals("")) ||
				user.getPhoneMobile() == null || user.getPhoneMobile().toString().equals("") ||
				user.getEmail() == null || user.getEmail().equals("")){
            throw new UserNameisNull("用户名、密码、邮箱、手机均不能为空");
        }
        //用户名长度不能超过30
        if(user.getUsername().length() > 30){
            throw new IllegalUsernameException();
        }
        //电话长度过长
        if(user.getPhoneMobile().toString().length() > 11){
        	throw new PhoneException();
		}
		//邮箱格式不正确
		if(!Check.checkEmail(user.getEmail())){
        	throw new BaseException(HttpStatus.BAD_REQUEST, "邮箱格式错误");
		}
        //生成MD5密码
        user.setPassword(MD5.getMD5(user.getPassword()));
        userMapper.insertSelective(user);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateUserInfo(User user) throws Exception {
	    //一定要传入username信息
	    //信息为空则不更新mapper.xml实现

		//邮箱格式不正确
		if(user.getEmail() != null && !user.getEmail().equals("") && !Check.checkEmail(user.getEmail()) ){
			throw new BaseException(HttpStatus.BAD_REQUEST, "邮箱格式错误");
		}
		//url格式不正确
		if(user.getUrl() != null && !user.getUrl().equals("") && !Check.checkUrl(user.getUrl())){
			throw new BaseException(HttpStatus.BAD_REQUEST, "url格式错误");
		}

		//用户更改密码
		if(user.getPassword() != null){
			user.setPassword(MD5.getMD5(user.getPassword()));
		}
        userMapper.updateByPrimaryKeySelective(user);
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public User findUserByUsernameAndPassword(User user) throws Exception {

	    //构造查询条件
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        //获得密码的MD5值
        criteria.andPasswordEqualTo(MD5.getMD5(user.getPassword()));

        //返回查找的list 因为username是主键 所以list长度只可能为1或者0
	    List<User> list = userMapper.selectByExample(example);

	    //list长度为0
	    if(list == null || list.isEmpty()){
	        throw new PawordWrongException();
        }
        //list长度不为0
		return list.get(0);
	}

	@Override
	public List<User> findAllUser() throws Exception {

		UserExample example = new UserExample();

		return userMapper.selectByExample(example);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void createNewAdmin(User user) throws Exception {
		//用户名、密码均为非空选项
		if((user.getUsername() == null || user.getUsername().equals("")) ||
				(user.getPassword() == null || user.getPassword().equals(""))){
			throw new UserNameisNull("用户名、密码不能为空");
		}
		//用户名长度不能超过30
		if(user.getUsername().length() > 30){
			throw new IllegalUsernameException();
		}
		//生成MD5密码
		user.setPassword(MD5.getMD5(user.getPassword()));
		//设置管理员身份
		user.setRole(1);
		userMapper.insertSelective(user);
	}

}
