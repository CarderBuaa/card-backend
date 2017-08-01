package cn.card.dao;

import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;
import cn.card.domain.UserCustom;
import cn.card.domain.UserQueryVo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class cardMapperTest {

	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		this.applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/applicationContext-dao.xml" });
	}

	@Test
	public void testfindRecordList() throws Exception {
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		CardQueryVo cardQueryVo= new CardQueryVo();

		cardQueryVo.setCardCustom(new CardCustom());
		cardQueryVo.getCardCustom().setUsername("sb");

		List<CardCustom> list = cardMapper.findRecordList(cardQueryVo);

		System.out.println(list.get(list.size()-1));
	}

	@Test
	public void testcreateRecord() throws Exception {
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		CardQueryVo cardQueryVo = new CardQueryVo();
		CardCustom cardCustom = new CardCustom();

		cardCustom.setUsername("sb");
		cardCustom.setBackground("312312");

		cardQueryVo.setCardCustom(cardCustom);

		cardMapper.createRecord(cardQueryVo);

	}

	@Test
	public void testupdateCardInfo() throws Exception {
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		CardQueryVo cardQueryVo = new CardQueryVo();
		CardCustom cardCustom = new CardCustom();

		cardCustom.setUsername("sb");
		cardQueryVo.setCardCustom(cardCustom);

		//获取最新的提交记录
		CardCustom cardCustom1 = cardMapper.findRecordList(cardQueryVo).get(cardMapper.findRecordList(cardQueryVo).size()-1);

		cardCustom1.setName("小学弟");
		cardQueryVo.setCardCustom(cardCustom1);

		cardMapper.updateCardInfo(cardQueryVo);

	}

	@Test
	public void testDelete() throws Exception{
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		CardQueryVo cardQueryVo = new CardQueryVo();
		CardCustom cardCustom = new CardCustom();

		cardCustom.setId(10);
		cardQueryVo.setCardCustom(cardCustom);

		cardMapper.deleteCard(cardQueryVo);
	}

	@Test
	public void testfindCardByID() throws Exception{
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		CardQueryVo cardQueryVo = new CardQueryVo();
		CardCustom cardCustom = new CardCustom();

		cardCustom.setId(11);
		cardCustom.setUsername("a555");
		cardQueryVo.setCardCustom(cardCustom);

		CardCustom cardCustom1 = cardMapper.findCardByIDAndUsername(cardQueryVo);

		System.out.println(cardCustom1);
	}


}
