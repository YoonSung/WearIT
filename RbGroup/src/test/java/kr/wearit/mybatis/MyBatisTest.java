package kr.wearit.mybatis;

import static org.junit.Assert.*;

import java.io.InputStream;

import kr.wearit.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisTest {
	
	SqlSession session;
	private static final Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
	
	@Before
	public void setUp() throws Exception {
		String resource = "mybatis-test-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sqlSessionFactory.openSession(); 
		assertNotNull(sqlSessionFactory);
	}
	
	@After
	public void after() throws Exception {
		session.close();
	}
	
	@Test
	public void findById() throws Exception {
		//User result = session.selectOne("kr.wearit.user.UserMapper.findById", "Yoonsung");

		User user = new User("Yoonsung", null, null, null);
		User result = session.selectOne("UserMapper.findById", user);
		
		logger.info("findById : {}", result);
		assertNotNull(result.getEmail());
	}
	
	@Test
	public void create() throws Exception {
		User user = new User("Coby", "password", "Kim Co By", "lvev9926@naver.com");
		
		int afftectedLow = session.insert("UserMapper.create", user);
		
		logger.info("create : {}", session.selectOne("UserMapper.findById", "Coby"));
		assertEquals(1, afftectedLow);
	}
}