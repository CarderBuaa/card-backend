package cn.card.exception;

import org.springframework.http.HttpStatus;

import cn.card.exception.baseException.BaseException;

/**
 * 
 * Description: 查找用户不存在的异常类
 * @author z
 * @date 2017年7月26日
 */
public class UserNotFoundException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5033721086501338634L;
	
	private static HttpStatus status = HttpStatus.NOT_FOUND;
	private static String message = "用户不存在";
	
	public UserNotFoundException() {
		super(status, message);
	}
}
