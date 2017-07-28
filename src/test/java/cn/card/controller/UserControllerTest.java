package cn.card.controller;

import static org.junit.Assert.*;

import cn.card.service.UserService;
import com.swetake.util.Qrcode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

public class UserControllerTest {

	private ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] {"spring/applicationContext-dao.xml",
						"spring/applicationContext-service.xml",
						"spring/springmvc.xml"}) ;
	}

	@Test
	public void testCreateUserController() {
		UserController userController = (UserController) applicationContext.getBean("userController");
	}

}
