package cn.card.exception.baseException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import cn.card.exception.UserNotFoundException;
import net.sf.json.JSONObject;

public class BaseExceptionTest {

	@Test
	public void testBaseException() {
		BaseException baseException = new UserNotFoundException();
		System.out.println(((BaseException)baseException).getStatus());
		if(baseException instanceof BaseException) {
			System.out.println("11111111");
		}
		String JSON= baseException.getMessage();
		
		 JSONObject jsonObj = new JSONObject();
		 jsonObj.put("message", JSON);
		 System.out.println(jsonObj.toString());
	}

}
