package cn.card.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.card.domain.User;
import org.apache.ibatis.annotations.ResultMap;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import cn.card.exception.UserExistException;
import cn.card.service.UserService;

/**
 * 
 * Description: 控制用户行为的controller
 * @author z
 * @date 2017年7月26日
 */
@Controller
public class UserController {


	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/user",method=RequestMethod.POST)
	public void createUserController(@RequestBody UserCustom userCustom,
									 HttpServletRequest request,
									HttpServletResponse response) throws Exception{

		//新建查询对象
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);
		
		UserCustom check = userService.findUserByUserName(userQueryVo);
		
		//用户名已存在
		if(check != null) {
			throw new UserExistException();
		}
		
		//创建用户
		userService.createNewUser(userQueryVo);
		response.setStatus(HttpStatus.OK.value());
		
	}
	
	@RequestMapping(value="/user/{username}",method= RequestMethod.PUT)
	public @ResponseBody UserCustom editUserController(@PathVariable("username") String username,
			@RequestBody UserCustom userCustom, HttpServletRequest request) throws Exception {
		
		//新建查询对象
		userCustom.setUsername(username);
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);

		//对PUT请求的响应
		userService.findUserByUserName(userQueryVo);
		userService.updateUserInfo(userQueryVo);
		return null;

	}

	@RequestMapping(value="/user/{username}",method = RequestMethod.GET)
	public @ResponseBody UserCustom getUserController(@PathVariable("username") String username) throws Exception {
		//新建查询对象
		UserCustom userCustom = new UserCustom();
		userCustom.setUsername(username);
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);
		return userService.findUserByUserName(userQueryVo);
	}

	@RequestMapping(value="/user/accesstoken",method=RequestMethod.POST)
	public @ResponseBody String getAccessToken(@RequestBody UserCustom userCustom) {
		
		return null;
	}
}
