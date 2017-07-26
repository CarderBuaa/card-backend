package cn.card.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.card.domain.UserCustom;

public class TransferTest {


	@Test
	public void testTransferToList() {
		UserCustom userCustom = new UserCustom();
		userCustom.setAddress_list("一个地址;两个地址;三个地址;");
		userCustom.setEmail_list("一个邮箱;两个邮箱;三个邮箱;");
		userCustom.setOccupation_list("一个职位;两个职位;三个职位;");
		userCustom.setPhone_list("一个电话;两个电话;三个电话;");
		Transfer.transferToList(userCustom);
		System.out.println(userCustom);
	}
	
	@Test
	public void testTransferToString() {
		UserCustom userCustom = new UserCustom();
		
		List<String> address = new ArrayList<String>();
		address.add("地址1");
		address.add("地址2");
		userCustom.setAddress(address);
		
		List<String> email = new ArrayList<String>();
		email.add("邮箱1");
		email.add("邮箱2");
		userCustom.setEmail(email);
		
		List<String> phone = new ArrayList<String>();
		phone.add("电话1");
		phone.add("电话2");
		userCustom.setPhone(phone);
		
		List<String> occupation = new ArrayList<String>();
		occupation.add("1");
		occupation.add("2");
		userCustom.setOccupation(occupation);
		
		Transfer.transferToString(userCustom);
		
		System.out.println(userCustom.getAddress_list());
		System.out.println(userCustom.getPhone_list());
		System.out.println(userCustom.getEmail_list());
		System.out.println(userCustom.getOccupation_list());
		
	}

}
