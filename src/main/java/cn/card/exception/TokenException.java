package cn.card.exception;

import cn.card.exception.baseException.BaseException;
import org.springframework.http.HttpStatus;

/**
 * 
 * Description: 查找的用户已存在
 * @author z
 * @date 2017年7月26日
 */
public class TokenException extends BaseException{


	/**
	 *
	 */

	private static HttpStatus status = HttpStatus.UNAUTHORIZED;
	private static String message = "当前用户未授权,请登录";

	public TokenException() {
		super(status, message);
	}
}
