package kr.wearit.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/user/register")
	public String registerView(Model model) {
		//model.addAttribute("user", new User());
		model.addAttribute("user", new User("TestAttribute", "testPassword", "Yoonsung","test@navercom"));
		return "/user/register";
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	public String registerConfirm(@Valid User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			logger.error("bindingReulst has Error","bindingResult Error");
			return "/user/register";
		}
			
		logger.info("User : {}", user);
		userDao.create(user);
		logger.info("selectUser : {}", userDao.findById(user.getUserId()));
		return "redirect:/";
	}
}
