package kr.wearit.web;

import javax.sql.DataSource;

public class UserDao {
	DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
