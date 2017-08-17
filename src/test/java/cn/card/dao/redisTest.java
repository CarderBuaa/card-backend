package cn.card.dao;

import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

public class redisTest {

	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		this.applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring/applicationContext-dao.xml" });
	}

	@Test
	public void testredis() throws Exception {

		JedisPool shardedJedisPool = (JedisPool) applicationContext.getBean("jedisPool");

		Jedis shardedJedis = shardedJedisPool.getResource();

		System.out.println(shardedJedis);
		//释放redis资源
		shardedJedis.close();
	}


}
