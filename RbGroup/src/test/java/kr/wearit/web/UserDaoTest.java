package kr.wearit.web;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class UserDaoTest {
	
	@Test
	public void create() {
		UserDao dao = new UserDao();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
		DataSource dataSource = context.getBean("dataSource", BasicDataSource.class);
		dao.setDataSource(dataSource);
		
		assertThat(dao, notNullValue());
	}
}