package cn.card.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;

public class UserServiceTest {

	private ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] {"spring/applicationContext-dao.xml",
							  "spring/applicationContext-service.xml"}) ;
	}

	@Test
	public void testFindUserByUserName() throws Exception {
		UserService userService = (UserService) applicationContext.getBean("userService");
		
		UserQueryVo userQueryVo = new UserQueryVo();
		
		userQueryVo.setUserCustom(new UserCustom());
		userQueryVo.getUserCustom().setUsername("a5");
		try {
			UserCustom userCustom = userService.findUserByUserName(userQueryVo);
			System.out.println(userCustom);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void testCreateNewUser() throws Exception {
		UserService userService = (UserService) applicationContext.getBean("userService");
		
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		
		userCustom.setUsername("小强");
		userCustom.setPassword("aa5665");
		
		userQueryVo.setUserCustom(userCustom);
		
		userService.createNewUser(userQueryVo);
	}

	@Test
	public void testUpdateUserInfo() throws Exception {
		UserService userService = (UserService) applicationContext.getBean("userService");
		
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();

		userCustom.setUsername("a55555");
		userCustom.setPassword("aa5665");
		userCustom.setAddress_list("年轻就是好呀");

		userQueryVo.setUserCustom(userCustom);
		
		userService.updateUserInfo(userQueryVo);
	}

	@Test
	public void testfind2() throws Exception {
		UserService userService = (UserService) applicationContext.getBean("userService");

		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();

		userCustom.setUsername("a55555");
		userCustom.setPassword("aa5665");

		userQueryVo.setUserCustom(userCustom);

		System.out.print(userService.findUserByUsernameAndPassword(userQueryVo));
	}

}
