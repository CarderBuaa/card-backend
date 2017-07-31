package cn.card.domain;

import java.util.List;

/**
 * 
 * Description: 用于card类的扩展
 * @author z
 * @date 2017年7月25日
 */
public class CardCustom extends Card{

	
	List<String> occupation;
	List<String> address;
	List<String> email;
	List<String> phone;
	
	public List<String> getOccupation() {
		return occupation;
	}
	public void setOccupation(List<String> occupation) {
		this.occupation = occupation;
	}
	public List<String> getAddress() {
		return address;
	}
	public void setAddress(List<String> address) {
		this.address = address;
	}
	public List<String> getEmail() {
		return email;
	}
	public void setEmail(List<String> emai) {
		this.email = emai;
	}
	public List<String> getPhone() {
		return phone;
	}
	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return super.toString() + "CardCustom{" +
				"occupation=" + occupation +
				", address=" + address +
				", email=" + email +
				", phone=" + phone +
				'}';
	}
}
