package kr.wearit.web;

import javax.sql.DataSource;

import kr.wearit.domain.User;

import org.apache.ibatis.session.SqlSession;

public class MybatisUserDao implements UserDao {
	
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
