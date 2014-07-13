package kr.wearit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/user/register")
	public String registerView() {
		return "/user/register";
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	public String registerConfirm(User user) {
		logger.info("User : {}", user);
		
		return "redirect:/user/register";
	}
}
