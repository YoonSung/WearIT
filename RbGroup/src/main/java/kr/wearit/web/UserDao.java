package kr.wearit.web;

import kr.wearit.domain.User;

public interface UserDao {

	public abstract User findById(String userId);

	public abstract void create(User user);

	public abstract void update(User user);

}