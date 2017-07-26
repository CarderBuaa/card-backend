package cn.card.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;

public class UserMapperTest {

	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		this.applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/applicationContext-dao.xml" });
	}

	@Test
	public void testFindUserByUserName() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

		UserQueryVo userQueryVo = new UserQueryVo();

		userQueryVo.setUserCustom(new UserCustom());
		userQueryVo.getUserCustom().setUsername("a502982165");

		UserCustom userCustom = userMapper.findUserByUserName(userQueryVo);

		System.out.println(userCustom);
	}

	@Test
	public void testcreateNewUser() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();

		userCustom.setUsername("a55555");
		userCustom.setPassword("aa5665");

		userQueryVo.setUserCustom(userCustom);

		userMapper.createNewUser(userQueryVo);

	}

	@Test
	public void testupdateUserInfo() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();

		userCustom.setUsername("a555555");
		userCustom.setAddress_list("hhhhhhh;1111");
		userQueryVo.setUserCustom(userCustom);

		userMapper.updateUserInfo(userQueryVo);

	}

}
