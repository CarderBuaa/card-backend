package cn.card.controller;

import javax.servlet.http.HttpServletResponse;

import cn.card.exception.PawordWrongException;
import cn.card.exception.UserNameisNull;
import cn.card.exception.UserNotFoundException;
import cn.card.utils.GenerateMD5.MD5;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.access_token.TokenManager;
import cn.card.utils.propertyReader.PropertyReader;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import cn.card.exception.UserExistException;
import cn.card.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 
 * Description: 控制用户行为的controller
 * @author z
 * @date 2017年7月26日
 */
//添加跨域请求支持
@CrossOrigin
@Controller
public class UserController {


	private UserService userService;
	private TokenManager tokenManager;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}


	//注册方法不需要检查token
	@IgnoreSecurity
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public void createUserController(@Validated UserCustom userCustom, BindingResult result,
									 HttpServletResponse response) throws Exception{

		//没想到好的写法 囧
		if(result.hasErrors()){
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error: allErrors) {
				throw new UserNameisNull(error.getDefaultMessage());
			}
		}

		//新建查询对象
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);

		//生成密码的MD5值并保存
		userCustom.setPassword(MD5.getMD5(userCustom.getPassword()));

		//寻找用户名
		UserCustom check = userService.findUserByUserName(userQueryVo);
		//用户名已存在
		if (check != null) {
			throw new UserExistException();
		}
		
		//创建用户
		userService.createNewUser(userQueryVo);

		//在第一个用户注册时候就为所有用户创建上传图片文件夹

		//在设置的上传路径下创建一个上传文件夹路径
		String upload = PropertyReader.getUploadPath();
		File uploads = new File(upload);
		if(!uploads.exists()){
			uploads.mkdir();
		}

		response.setStatus(HttpStatus.OK.value());
	}

	@RequestMapping(value="/user/{username}",method= RequestMethod.PUT)
	public void editUserController(@PathVariable("username") String username, @RequestBody UserCustom userCustom,
				HttpServletResponse response) throws Exception {

		//对中文路径编码问题的处理
		username = new String(username.getBytes("ISO-8859-1"), "utf8");

		//新建查询对象
		userCustom.setUsername(username);
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);

		//寻找用户
		UserCustom check = userService.findUserByUserName(userQueryVo);
		//用户不存在
		if (check == null) {
			throw new UserNotFoundException();
		}
		//更新用户信息
		userService.updateUserInfo(userQueryVo);
		response.setStatus(HttpStatus.OK.value());
	}

	@RequestMapping(value="/user/{username}",method = RequestMethod.GET)
	public @ResponseBody UserCustom getUserController(@PathVariable("username") String username) throws Exception {

		//对中文路径编码问题的处理
		username = new String(username.getBytes("ISO-8859-1"), "utf8");


		//新建查询对象
		UserCustom userCustom = new UserCustom();
		userCustom.setUsername(username);
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);

		//寻找用户名
		UserCustom check = userService.findUserByUserName(userQueryVo);
		//用户不存在
		if (check == null) {
			throw new UserNotFoundException();
		}
		return check;
	}

	//登录方法不用检查Token
	@IgnoreSecurity
	@RequestMapping(value="/user/accesstoken",method=RequestMethod.POST)
	public void getAccessToken(@Validated UserCustom userCustom, BindingResult result,
							   HttpServletResponse response) throws Exception {

		//验证没想到好的写法 囧
		if(result.hasErrors()){
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error: allErrors) {
				throw new UserNameisNull(error.getDefaultMessage());
			}
		}

		//生成密码的MD5值并保存
		userCustom.setPassword(MD5.getMD5(userCustom.getPassword()));

		//检查用户账号密码
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserCustom(userCustom);

		UserCustom check = userService.findUserByUsernameAndPassword(userQueryVo);

		//账号密码不正确
		if(check == null){
			throw new PawordWrongException();
		}

		//获取当前用户生成的token
		String token = tokenManager.createToken(userCustom.getUsername());

		//将token变成JSON放入response中
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("token", token);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().append(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
