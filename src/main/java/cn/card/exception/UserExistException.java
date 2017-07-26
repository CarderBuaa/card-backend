package cn.card.exception;

import org.springframework.http.HttpStatus;

import cn.card.exception.baseException.BaseException;

/**
 * 
 * Description: 查找的用户已存在
 * @author z
 * @date 2017年7月26日
 */
public class UserExistException extends BaseException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2941647793007103727L;
	
	private static HttpStatus status = HttpStatus.BAD_REQUEST;
	private static String message = "用户已存在";
	
	public UserExistException() {
		super(status, message);
	}
}
