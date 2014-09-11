package kr.wearit.web;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import kr.wearit.domain.Authentication;

import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {

	Validator validator;
	
	@Before
	public void setUp() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@Test
	public void userIdWhenIsEmpty() {
		Authentication auth = new Authentication(null, "test"); 
		 Set<ConstraintViolation<Authentication>> result  = validator.validate(auth);
		 
		 assertEquals(1, result.size());
	}
	
	@Test
	public void passwordWhenIsEmpty() {
		 Authentication auth = new Authentication("testtest", null); 
		 Set<ConstraintViolation<Authentication>> result  = validator.validate(auth);
		 
		 assertEquals(1, result.size());
	}

}
