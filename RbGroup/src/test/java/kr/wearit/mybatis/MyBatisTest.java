package kr.wearit.mybatis;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import kr.wearit.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyBatisTest {
	
	SqlSession session;

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
		User user = new User("Yoonsung", null, null, null);
		User result = session.selectOne("kr.wearit.user.UserMapper.findById", user);
		//User result = session.selectOne("kr.wearit.user.UserMapper.findById", "Yoonsung");
		
		System.out.println(result.toString());
		assertNotNull(result.getEmail());
	}
}