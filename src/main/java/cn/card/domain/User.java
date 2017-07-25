package cn.card.domain;

/**
 * 
 * Description: user的POJO类
 * @author z
 * @date 2017年7月25日
 */
public class User {
	
	private String username;
	private String password;
	private String name;
	private String occupation_list;
	private String email_list;
	private String phone_list;
	private String address_list;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation_list() {
		return occupation_list;
	}
	public void setOccupation_list(String occupation_list) {
		this.occupation_list = occupation_list;
	}
	public String getEmail_list() {
		return email_list;
	}
	public void setEmail_list(String emai_list) {
		this.email_list = emai_list;
	}
	public String getPhone_list() {
		return phone_list;
	}
	public void setPhone_list(String phone_list) {
		this.phone_list = phone_list;
	}
	public String getAddress_list() {
		return address_list;
	}
	public void setAddress_list(String address_list) {
		this.address_list = address_list;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", occupation_list="
				+ occupation_list + ", emai_list=" + email_list + ", phone_list=" + phone_list + ", address_list="
				+ address_list + "]";
	}
	
	
}
