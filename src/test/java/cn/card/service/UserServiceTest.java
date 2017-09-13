package cn.card.service;

import static org.junit.Assert.*;

import cn.card.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

		User user = new User();

		user.setUsername("sb");

        User userCustom = userService.findUserByUserName(user);

        System.out.println(userCustom);

	}

	@Test
	public void testCreateNewUser() throws Exception {
		UserService userService = (UserService) applicationContext.getBean("userService");

        User user = new User();

        user.setUsername("小强");
        user.setPassword("aa5665");

		userService.createNewUser(user);
	}

	@Test
	public void testUpdateUserInfo() throws Exception {
		UserService userService = (UserService) applicationContext.getBean("userService");

        User user = new User();

		user.setUsername("sb");
        user.setAddressHome("傻吊 你的家在哪儿");

		userService.updateUserInfo(user);
	}

	@Test
	public void testfind2() throws Exception {
		UserService userService = (UserService) applicationContext.getBean("userService");

		User user = new User();

        user.setUsername("das");
        user.setPassword("aa5665");

		System.out.print(userService.findUserByUsernameAndPassword(user));
	}

	@Test
	public void testfindAllUser() throws Exception {
		UserService userService = (UserService) applicationContext.getBean("userService");
		System.out.println(userService.findAllUser());

	}
}
