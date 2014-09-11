package kr.wearit.web;

import static org.junit.Assert.assertEquals;
import kr.wearit.domain.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class UserDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Autowired
	UserDao dao;
	
	@Test
	public void findById() {
		User user = dao.findById("Yoonsung");
		logger.info("user = {}", user);
		
		assertEquals(
				new User("Yoonsung", "yoon", "정윤성", "estrella@nhnnext.org"),
				user
		);
		
	}
	
	@Test
	public void create() {
		User user = new User("lvev9925", "password", "JungYoonSung", "lvev9925@naver.com");
		dao.create(user);
		
		assertEquals(user, dao.findById(user.getUserId()));
	}
}