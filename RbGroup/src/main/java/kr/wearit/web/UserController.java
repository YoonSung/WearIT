package kr.wearit.web;

import javax.validation.Valid;

import kr.wearit.domain.Authentication;
import kr.wearit.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	
	@RequestMapping("/user/login/form")
	public String loginView(Model model) {
		model.addAttribute("authentication", new Authentication());
		return "/user/login";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	public String login(@Valid Authentication authentication, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				logger.error("Login Request Error : {}", error.getDefaultMessage());
			}
			return "/user/login";
		}
		
		//TODO 아이디가 존재하지 않을경우
		
		//TODO 아이디가 존재하나 비밀번호가 다를경우
		
		return "redirect:/";
	}
}
