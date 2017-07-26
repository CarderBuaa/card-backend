package cn.card.controller;

import javax.servlet.http.HttpServletResponse;

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

import cn.card.domain.UserQueryVo;
import cn.card.service.UserService;

/**
 * 
 * Description: 控制用户行为的controller
 * @author z
 * @date 2017年7月26日
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public void createUserController(@RequestBody UserQueryVo userQueryVo,
									HttpServletResponse response){
		
		response.setStatus(200);
	}
	
	@RequestMapping(value="/user/{username}",method= {RequestMethod.GET, RequestMethod.PUT})
	public void getAndEditUserController(@PathVariable("username") String username, 
										 @RequestBody UserQueryVo userQueryVo) {
		
	}
	
	@RequestMapping(value="/user/accesstoken",method=RequestMethod.POST)
	public void getAccessToken(@RequestBody UserQueryVo userQueryVo) {
		
	}
}
