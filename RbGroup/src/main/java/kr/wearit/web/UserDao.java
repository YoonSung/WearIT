package kr.wearit.web;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class UserDao extends JdbcDaoSupport{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	DataSource dataSource;
	
	@PostConstruct
	public void create() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		
		logger.info("Database Initialized Success!!");
	}

	public User findById(String userId) {
		String sql = "SELECT * FROM tbl_user WHERE userId = ?";
		
		RowMapper<User> rowMapper = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email")
				);
			}
			
		};
		return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
	}
}
