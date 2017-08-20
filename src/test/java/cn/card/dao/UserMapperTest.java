package cn.card.dao;

import cn.card.domain.User;
import cn.card.domain.UserExample;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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

		User user =	userMapper.selectByPrimaryKey("test");

		System.out.println(user);
	}

	@Test
	public void testcreateNewUser() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

		User user = new User();

		user.setUsername("a55");
		user.setPassword("aa5665");

		System.out.println(userMapper.insertSelective(user));

	}

	@Test
	public void testupdateUserInfo() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

		User user = new User();
		user.setUsername("test");
		user.setAddressHome("是这个地址嘛傻吊");

		//int返回影响行数
		System.out.println(userMapper.updateByPrimaryKeySelective(user));

	}

	@Test
	public void testFindByUserNameAndPassword() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");

		UserExample userExample = new UserExample();
		//构造查询条件
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andPasswordEqualTo("41324123");
		criteria.andUsernameEqualTo("test");

		List<User> list = userMapper.selectByExample(userExample);

		System.out.println(list.size()==0);

	}

}
