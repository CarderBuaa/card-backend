package cn.card.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.card.domain.*;
import cn.card.exception.*;
import cn.card.service.CardService;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.access_token.TokenManager;
import cn.card.utils.propertyReader.PropertyReader;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.card.service.UserService;
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
	public void createUserController(User user,
									 HttpServletResponse response) throws Exception{
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
								   @RequestBody User user,
								   HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		//对中文路径编码问题的处理
		username = new String(username.getBytes("ISO-8859-1"), "utf8");

		//获取token的username
		String token = request.getHeader("Access-Token");
		String username_token = tokenManager.getUsername(token);

		//判断与当前请求username是否相同
		if(!username_token.equals(username)){
			throw new TokenException();
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
			throw new TokenException();
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

		//获取当前用户生成的token
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
}
