package kr.wearit.web;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import kr.wearit.domain.User;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class MybatisUserDao implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(MybatisUserDao.class);
	private SqlSession sqlSession;
	private DataSource dataSource;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@PostConstruct
	public void create() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		
		logger.info("Database Initialized Success!!");
	}
	
	@Override
	public User findById(String userId) {
		return sqlSession.selectOne("UserMapper.findById", userId);
	}

	@Override
	public void create(User user) {
		sqlSession.insert("UserMapper.create", user);
	}

	@Override
	public void update(User user) {
		sqlSession.update("UserMapper.update", user);
	}

}
