package cn.card.domain;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * Description: 用于User类的扩展
 * @author z
 * @date 2017年7月25日
 */
public class UserCustom extends User{

	
	List<String> occupation;
	List<String> address;
	List<String> email;
	List<BigInteger> phone;
	List<CardCustom> cards;
	
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

	public List<BigInteger> getPhone() {
		return phone;
	}

	public void setPhone(List<BigInteger> phone) {
		this.phone = phone;
	}

	public List<CardCustom> getCards() {
		return cards;
	}

	public void setCards(List<CardCustom> cards) {
		this.cards = cards;
	}


	@Override
	public String toString() {
		return 	"User"+ super.toString() +
				"UserCustom{" +
				"occupation=" + occupation +
				", address=" + address +
				", email=" + email +
				", phone=" + phone +
				", cards=" + cards +
				'}';
	}
}
