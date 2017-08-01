package cn.card.service;

import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CardServiceTest {

	private ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] {"spring/applicationContext-dao.xml",
							  "spring/applicationContext-service.xml"}) ;
	}

	@Test
	public void testfindRecordList() throws Exception {
		CardService cardService = (CardService) applicationContext.getBean("cardService");

		CardQueryVo cardQueryVo= new CardQueryVo();

		cardQueryVo.setCardCustom(new CardCustom());
		cardQueryVo.getCardCustom().setUsername("sb");

		List<CardCustom> list = cardService.findRecordList(cardQueryVo);

		System.out.println(list);
		
	}

	@Test
	public void testcreateRecord() throws Exception {
		CardService cardService = (CardService) applicationContext.getBean("cardService");

		CardQueryVo cardQueryVo = new CardQueryVo();
		CardCustom cardCustom = new CardCustom();

		cardCustom.setUsername("sb");
		cardCustom.setBackground("321312");

		cardQueryVo.setCardCustom(cardCustom);

		cardService.createRecord(cardQueryVo);
	}

	@Test
	public void testupdateCardInfo() throws Exception {
		CardService cardService = (CardService) applicationContext.getBean("cardService");

		CardQueryVo cardQueryVo = new CardQueryVo();
		CardCustom cardCustom = new CardCustom();

		cardCustom.setUsername("sb");
		cardQueryVo.setCardCustom(cardCustom);

		//获取最新的提交记录
		CardCustom cardCustom1 = cardService.findRecordList(cardQueryVo).get(cardService.findRecordList(cardQueryVo).size()-1);

		cardCustom1.setName("小学弟1");
		cardQueryVo.setCardCustom(cardCustom1);

		cardService.updateCardInfo(cardQueryVo);
	}

	@Test
	public void testDelete() throws Exception{
		CardService cardService = (CardService) applicationContext.getBean("cardService");

		CardQueryVo cardQueryVo = new CardQueryVo();
		CardCustom cardCustom = new CardCustom();

		cardCustom.setId(1);
		cardQueryVo.setCardCustom(cardCustom);

		cardService.deleteCard(cardQueryVo);
	}

	@Test
	public void testfindCardByID() throws Exception{
		CardService cardService = (CardService) applicationContext.getBean("cardService");

		CardQueryVo cardQueryVo = new CardQueryVo();
		CardCustom cardCustom = new CardCustom();

		cardCustom.setId(11);
		cardCustom.setUsername("a555");
		cardQueryVo.setCardCustom(cardCustom);

		CardCustom cardCustom1 = cardService.findCardByIDAndUsername(cardQueryVo);

		System.out.println(cardCustom1);
	}


}
