package cn.card.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.card.domain.UserCustom;

/**
 * 
 * Description: 用于数据库的输入数据和输出数据转换的工具类
 * @author z
 * @date 2017年7月25日
 */
public class Transfer {
	
	/**
	 * Description:用于将从database里面取出的String转换成List
	 * @param userCustom
	 */
	public static void transferToList(UserCustom userCustom) {
		
		String[] address = userCustom.getAddress_list().split(";");
		String[] email = userCustom.getEmail_list().split(";");
		String[] phone = userCustom.getPhone_list().split(";");
		String[] occupation = userCustom.getOccupation_list().split(";");
		
		//地址转化
		List<String> addressList = new ArrayList<String>();
		Collections.addAll(addressList, address);
		userCustom.setAddress(addressList);
		
		//邮箱转化
		List<String> emailList = new ArrayList<String>();
		Collections.addAll(emailList, email);
		userCustom.setEmail(emailList);
		
		//电话转化
		List<String> phonelList = new ArrayList<String>();
		Collections.addAll(phonelList, phone);
		userCustom.setPhone(phonelList);
		
		//职位转化
		List<String> occupationList = new ArrayList<String>();
		Collections.addAll(occupationList, occupation);
		userCustom.setOccupation(occupationList);
		
	}
	
	/**
	 * Description:用于将前端获取的List数据转换成String数据
	 * @param userCustom
	 */
	public static void transferToString(UserCustom userCustom) {
		
		//地址变化
		StringBuilder sb = new StringBuilder();  
		for (String s : userCustom.getAddress()) {  
		    if (s != null && !"".equals(s)) {  
		        sb.append(s).append(";");  
		    }  
		} 
		userCustom.setAddress_list(sb.toString());
		
		//邮箱转化
		StringBuilder sb1 = new StringBuilder();  
		for (String s : userCustom.getEmail()) {  
		    if (s != null && !"".equals(s)) {  
		        sb1.append(s).append(";");  
		    }  
		} 
		userCustom.setEmail_list(sb1.toString());
		
		//电话转化
		StringBuilder sb11 = new StringBuilder();  
		for (String s : userCustom.getPhone()) {  
		    if (s != null && !"".equals(s)) {  
		        sb11.append(s).append(";");  
		    }  
		} 
		userCustom.setPhone_list(sb11.toString());
		
		//职位转化
		StringBuilder sb111 = new StringBuilder();  
		for (String s : userCustom.getOccupation()) {  
		    if (s != null && !"".equals(s)) {  
		        sb111.append(s).append(";");  
		    }  
		} 
		userCustom.setOccupation_list(sb111.toString());
	}

}
