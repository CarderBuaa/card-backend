package cn.card.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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

//		shardedJedis.set("1", "2");
//		shardedJedis.expire("1", 20);

		System.out.println(shardedJedis.ttl("1000"));

		//释放redis资源
		shardedJedis.close();
	}


}
