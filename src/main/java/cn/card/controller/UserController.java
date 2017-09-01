package cn.card.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.card.domain.*;
import cn.card.exception.*;
import cn.card.exception.baseException.BaseException;
import cn.card.service.CardService;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.access_token.TokenManager;
import cn.card.utils.propertyReader.PropertyReader;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cn.card.service.UserService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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
	private CardService cardService;
	private JedisPool jedisPool;

	@Autowired
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	@Autowired
	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

	//注册方法不需要检查token
	@IgnoreSecurity
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public void createUserController(@Validated User user, BindingResult result,
									 HttpServletResponse response) throws Exception{

    	//输出校验错误信息
    	if(result.hasErrors()){
    		List<ObjectError> allErrors = result.getAllErrors();
    		throw new BaseException(HttpStatus.BAD_REQUEST, allErrors.get(0).getDefaultMessage());
		}
		//寻找用户名
		User check = userService.findUserByUserName(user);
		//用户名已存在
		if (check != null) {
			throw new UserExistException();
		}
		//创建用户
		userService.createNewUser(user);

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
	public void editUserController(@PathVariable("username") String username,
								   @RequestBody @Validated User user, BindingResult result,
								   HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		//对中文路径编码问题的处理
		username = new String(username.getBytes("ISO-8859-1"), "utf8");

		//输出校验错误信息
		if(result.hasErrors()){
			List<ObjectError> allErrors = result.getAllErrors();
			throw new BaseException(HttpStatus.BAD_REQUEST, allErrors.get(0).getDefaultMessage());
		}

		//获取token的username
		String token = request.getHeader("Access-Token");
		String username_token = tokenManager.getUsername(token);

		User find = new User();
		find.setUsername(username_token);
		User admin = userService.findUserByUserName(find);

		//当前用户不是管理员
		if(admin == null || admin.getRole() != 1) {
			user.setRole(0);
			//判断与当前请求username是否相同
			if (!username_token.equals(username)) {
				throw new BaseException(HttpStatus.UNAUTHORIZED, "当前用户未授权");
			}
		}

		//新建查询对象
		user.setUsername(username);
		//寻找用户
		User check = userService.findUserByUserName(user);
		//用户不存在
		if (check == null) {
			throw new UserNotFoundException();
		}

        userService.updateUserInfo(user);
		//更新完用户信息后 删除redis中图片信息 重新生成
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			//找到所有card
			Card card = new Card();
			card.setUsername(username);

			//根据username查询当前用户所有的card信息
			List<Card> cardCustomList = cardService.findRecordList(card);
			for (Card card1: cardCustomList){
				Integer card_id = card1.getId();
				if (jedis.exists(("card_" + card_id.toString()).getBytes())) {
					jedis.del(("card_" + card_id.toString()).getBytes());
				}
			}
		}finally {
			if(jedis != null){
				jedis.close();
			}
		}
		response.setStatus(HttpStatus.OK.value());
	}

	@RequestMapping(value="/user/{username}",method = RequestMethod.GET)
	public @ResponseBody UserCustom getUserController(@PathVariable("username") String username,
                                                HttpServletRequest request) throws Exception {

		//对中文路径编码问题的处理
		username = new String(username.getBytes("ISO-8859-1"), "utf8");

		//获取token的username
		String token = request.getHeader("Access-Token");
		String username_token = tokenManager.getUsername(token);

		//判断与当前请求username是否相同
		if(!username_token.equals(username)){
			throw new BaseException(HttpStatus.UNAUTHORIZED, "当前用户未授权");
		}

		//新建查询对象
		User user = new User();
        user.setUsername(username);

		//寻找用户
        User check = userService.findUserByUserName(user);
		//用户不存在
		if (check == null) {
			throw new UserNotFoundException();
		}

		//设置查询条件
        UserCustom userCustom = new UserCustom(check);
		userCustom.setPassword(null);

		Card card = new Card();
        card.setUsername(username);

		//根据username查询当前用户所有的card信息
		List<Card> cardCustomList = cardService.findRecordList(card);

		//设置返回信息
        userCustom.setCards(cardCustomList);

		return userCustom;
	}

	//登录方法不用检查Token
	@IgnoreSecurity
	@RequestMapping(value="/user/accesstoken",method=RequestMethod.POST)
	public void getAccessToken(User user,
							   HttpServletResponse response) throws Exception {

		if(user.getUsername() == null || user.getUsername().equals("")
				|| user.getPassword() == null || user.getPassword().equals("")){
			throw new UserNameisNull("用户名和密码不能为空");
		}

		User check = userService.findUserByUsernameAndPassword(user);

		//获取当前用户生成的token,role
		String token = tokenManager.createToken(check.getUsername());

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

	//针对绑定异常的处理
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleJsonException(HttpServletResponse response){

		//填充message字符串到response的JSON中
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("message", "号码长度不能超过11位");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().append(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
