package cn.card.domain;

/**
 * 
 * Description: 用于前端向后端传入查询参数的类
 * @author z
 * @date 2017年7月25日
 */
public class UserQueryVo {
	
	private UserCustom userCustom;

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	@Override
	public String toString() {
		return "UserQueryVo [userCustom=" + userCustom + "]";
	}
	
	
}
