package kr.wearit.web;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import javax.annotation.Resource;

import kr.wearit.domain.User;
import kr.wearit.handler.PerformanceHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@Transactional
public class UserDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Resource(name="userDaoFactoryBean")
	UserDao dao;
	
	@Test
	public void findById() {
		UserDao userDao = (UserDao) Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class[] { UserDao.class},
				new PerformanceHandler(dao));
		
		
		User user = userDao.findById("Yoonsung");
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
