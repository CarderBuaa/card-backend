package cn.card.utils.TransferData;

import cn.card.domain.CardCustom;
import cn.card.domain.UserCustom;
import cn.card.exception.PhoneException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * Description: 用于数据库的输入数据和输出数据转换的工具类
 * @author z
 * @date 2017年7月25日
 */
public class TransferCard {
	
	/**
	 * Description:用于将从database里面取出的String转换成List
	 * @param cardCustom
	 */
	public static void transferToList(CardCustom cardCustom) throws Exception{

		if(cardCustom != null) {
			//地址转化
			if (cardCustom.getAddress_list() != null) {
				String[] address = cardCustom.getAddress_list().split(";");
				List<String> addressList = new ArrayList<String>();
				Collections.addAll(addressList, address);
				cardCustom.setAddress(addressList);
			}

			//邮箱转化
			if (cardCustom.getEmail_list() != null) {
				String[] email = cardCustom.getEmail_list().split(";");
				List<String> emailList = new ArrayList<String>();
				Collections.addAll(emailList, email);
				cardCustom.setEmail(emailList);
			}

			//电话转化
			if (cardCustom.getPhone_list() != null) {
				String[] phone = cardCustom.getPhone_list().split(";");
				List<String> phonelList = new ArrayList<String>();
				Collections.addAll(phonelList, phone);
				List<BigInteger> phone_integer = new ArrayList<BigInteger>();
				for(String phone_str: phonelList){
					BigInteger temp = new BigInteger(phone_str);
					phone_integer.add(temp);
				}
				cardCustom.setPhone(phone_integer);
			}

			//职位转化
			if (cardCustom.getOccupation_list() != null) {
				String[] occupation = cardCustom.getOccupation_list().split(";");
				List<String> occupationList = new ArrayList<String>();
				Collections.addAll(occupationList, occupation);
				cardCustom.setOccupation(occupationList);
			}
		}

	}
	
	/**
	 * Description:用于将前端获取的List数据转换成String数据
	 * @param cardCustom
	 */
	public static void transferToString(CardCustom cardCustom) throws Exception{

		if (cardCustom != null) {
			//地址变化
			if (cardCustom.getAddress() != null) {
				StringBuilder sb = new StringBuilder();
				for (String s : cardCustom.getAddress()) {
					if (s != null && !"".equals(s)) {
						sb.append(s).append(";");
					}
				}
				cardCustom.setAddress_list(sb.toString());
			}

			//邮箱转化
			if (cardCustom.getEmail() != null) {
				StringBuilder sb1 = new StringBuilder();
				for (String s : cardCustom.getEmail()) {
					if (s != null && !"".equals(s)) {
						sb1.append(s).append(";");
					}
				}
				cardCustom.setEmail_list(sb1.toString());
			}

			//电话转化
			if (cardCustom.getPhone() != null) {
				StringBuilder sb11 = new StringBuilder();
				List<BigInteger> phone_integer = cardCustom.getPhone();
				List<String> phone_str = new ArrayList<String>();
				for(BigInteger phone_int: phone_integer){
					String temp = phone_int.toString();
					if(temp.length() > 11){
						throw new PhoneException();
					}
					phone_str.add(temp);
				}
				for (String s : phone_str) {
					if (s != null && !"".equals(s)) {
						sb11.append(s).append(";");
					}
				}
				cardCustom.setPhone_list(sb11.toString());
			}

			//职位转化
			if (cardCustom.getOccupation() != null) {
				StringBuilder sb111 = new StringBuilder();
				for (String s : cardCustom.getOccupation()) {
					if (s != null && !"".equals(s)) {
						sb111.append(s).append(";");
					}
				}
				cardCustom.setOccupation_list(sb111.toString());
			}
		}
	}

}
