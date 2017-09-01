package cn.card.exception.baseException;

import org.springframework.http.HttpStatus;

/**
 * 
 * Description:自定义异常基类 
 * @author z
 * @date 2017年7月26日
 */
public class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	
	
	public BaseException(HttpStatus status, String message) {
		this.status = status; 
		this.message = message;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
