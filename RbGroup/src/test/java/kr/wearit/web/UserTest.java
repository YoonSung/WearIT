package kr.wearit.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import kr.wearit.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
	private Validator validator;

	//@BeforeClass
	/*
 			Sometimes several tests need to share computationally expensive setup 
 			(like logging into a database). 
 			While this can compromise the independence of tests, 
 			sometimes it is a necessary optimization. 
 			Annotating a public static void no-arg method with 
 			@BeforeClass causes it to be run once before any of the test methods in the class. 
 			The @BeforeClass methods of superclasses will be run before those the current class.
	 */
	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void userIdWhenIsEmpty() {
		User user = new User("", "yoonsung", "JungYoonSung", "lvev9925@naver.com");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
		
		assertThat(constraintViolations.size() , is(2));
		
		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			logger.info(constraintViolation.getMessage());
		}
	}
	
	@Test
	public void passwordComparison() throws Exception {
		User user = new User("lvev9925", "password", "JungYoonSung", "lvev9925@naver.com");
		
		assertTrue(user.isPasswordEqual("password"));
		assertFalse(user.isPasswordEqual("password2"));
	}
	
}
