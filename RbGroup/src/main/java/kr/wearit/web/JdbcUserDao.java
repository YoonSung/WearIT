package kr.wearit.web;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import kr.wearit.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class JdbcUserDao extends JdbcDaoSupport implements UserDao{
	
	private static final Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);
	
	DataSource dataSource;
	
	@PostConstruct
	public void create() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		
		logger.info("Database Initialized Success!!");
	}

	/* (non-Javadoc)
	 * @see kr.wearit.web.IUserDao#findById(java.lang.String)
	 */
	@Override
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
		
		try {
			return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see kr.wearit.web.IUserDao#create(kr.wearit.domain.User)
	 */
	@Override
	public void create(User user) {
		String sql = "INSERT INTO tbl_user VALUES(?, ?, ?, ?)";
		getJdbcTemplate().update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	/* (non-Javadoc)
	 * @see kr.wearit.web.IUserDao#update(kr.wearit.domain.User)
	 */
	@Override
	public void update(User user) {
		String sql = "UPDATE tbl_user SET password = ? , name = ?, email=? WHERE userId = ?";
		getJdbcTemplate().update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}
}
