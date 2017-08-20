package cn.card.service;


import cn.card.domain.Card;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
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

		Card card = new Card();
		card.setUsername("sb");

		List<Card> list = cardService.findRecordList(card);

		System.out.println(list);

	}

	@Test
	public void testcreateRecord() throws Exception {
		CardService cardService = (CardService) applicationContext.getBean("cardService");

        Card card = new Card();
        card.setUsername("sb");
        card.setBackground("321312");
        card.setTemplate(2);

		cardService.createRecord(card);

	}

	@Test
	public void testupdateCardInfo() throws Exception {
		CardService cardService = (CardService) applicationContext.getBean("cardService");

		Card card = new Card();

		card.setId(5);
		card.setTemplate(1);

		card.setName(true);

		cardService.updateCardInfo(card);
	}

	@Test
	public void testDelete() throws Exception{
		CardService cardService = (CardService) applicationContext.getBean("cardService");

		Card card = new Card();
		card.setId(5);

		cardService.deleteCard(card);
	}

	@Test
	public void testfindCardByID() throws Exception{
		CardService cardService = (CardService) applicationContext.getBean("cardService");

		Card card = new Card();

		card.setId(1);
		card.setUsername("sb");

		Card cardCustom1 = cardService.findCardByIDAndUsername(card);

		System.out.println(cardCustom1);
	}


}
