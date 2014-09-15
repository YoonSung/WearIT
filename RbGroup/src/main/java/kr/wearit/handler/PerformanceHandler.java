package kr.wearit.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler {

	Object target;

	public PerformanceHandler(Object object) {
		this.target = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		long start = System.currentTimeMillis();
		
		Object ret = method.invoke(target, args);
		
		long end = System.currentTimeMillis();
		System.out.println(method.getName() + " : " + (end-start) + " ms");
		return ret;
	}

}
