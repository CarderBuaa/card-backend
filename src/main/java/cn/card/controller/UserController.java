package cn.card.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.card.exception.TokenException;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.JsonResult.JsonResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import cn.card.exception.UserExistException;
import cn.card.service.UserService;

import java.io.IOException;

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

	//注册方法不需要检查token
	@IgnoreSecurity
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public void createUserController(@RequestBody UserCustom userCustom, HttpServletResponse response) throws Exception{

		//新建查询对象
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);

		//生成密码的MD5值并保存,测试时为了方便注释掉
		//userCustom.setPassword(MD5.getMD5(userCustom.getPassword()));

		//寻找用户名
		UserCustom check = userService.findUserByUserName(userQueryVo);
		//用户名已存在
		if (check != null) {
			throw new UserExistException();
		}
		
		//创建用户
		userService.createNewUser(userQueryVo);
		response.setStatus(HttpStatus.OK.value());
	}
	
	@RequestMapping(value="/user/{username}",method= RequestMethod.PUT)
	public @ResponseBody JsonResult<UserCustom> editUserController(@PathVariable("username") String username,
			@RequestBody UserCustom userCustom) throws Exception {
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
		return (userService.findUserByUserName(userQueryVo));
	}

	//登录方法不用检查Token
	@IgnoreSecurity
	@RequestMapping(value="/user/accesstoken",method=RequestMethod.POST)
	public @ResponseBody String getAccessToken(@RequestBody UserCustom userCustom) {
		
		return null;
	}
}
