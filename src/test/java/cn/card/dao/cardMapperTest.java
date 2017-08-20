package cn.card.dao;

import cn.card.domain.Card;
import cn.card.domain.CardExample;
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

		CardExample example = new CardExample();
		//设置查询条件
		CardExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo("sb");

		List<Card> list = cardMapper.selectByExample(example);

		System.out.println(list);
	}

	@Test
	public void testcreateRecord() throws Exception {
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		Card card = new Card();

		card.setUsername("sb");
		card.setBackground("312312");
		card.setTemplate(1);

		cardMapper.insertSelective(card);

		System.out.println(card.getId());

	}

	@Test
	public void testupdateCardInfo() throws Exception {
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		Card card = new Card();

		card.setUsername("sb");
		card.setId(2);
		card.setEmail(true);

		cardMapper.updateByPrimaryKeySelective(card);

	}

	@Test
	public void testDelete() throws Exception{
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		Card card = new Card();
		card.setId(2);
		cardMapper.deleteByPrimaryKey(card.getId());

	}

	@Test
	public void testfindCardByIDAndUsername() throws Exception{
		CardMapper cardMapper = (CardMapper) applicationContext.getBean("cardMapper");

		CardExample example = new CardExample();
		CardExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(3);
		criteria.andUsernameEqualTo("sb");

		List<Card> list = cardMapper.selectByExample(example);
		System.out.println(list);


	}


}
