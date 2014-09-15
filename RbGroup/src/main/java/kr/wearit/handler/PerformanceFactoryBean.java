package kr.wearit.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import kr.wearit.web.UserDao;

import org.springframework.beans.factory.FactoryBean;

public class PerformanceFactoryBean implements FactoryBean<UserDao> {

	private InvocationHandler invocationHandler;
	
	public void setInvocationHandler(InvocationHandler invocationHandler) {
		this.invocationHandler = invocationHandler;
	}
	
	@Override
	public UserDao getObject() throws Exception {
		return (UserDao)Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class[] { UserDao.class}, invocationHandler);
	}

	@Override
	public Class<?> getObjectType() {
		return UserDao.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
