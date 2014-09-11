package kr.wearit.web;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.PathVariable;
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
	public String login(@Valid Authentication authentication, BindingResult bindingResult, Model model, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				logger.error("Login Request Error : {}", error.getDefaultMessage());
			}
			return "/user/login";
		}
		
		User searchUser = userDao.findById(authentication.getUserId());
		
		if (searchUser == null) {
			model.addAttribute("errorMessage", "아이디가 존재하지 않습니다");
			return "/user/login";
		}
		
		if (! searchUser.isPasswordEqual(authentication.getPassword())) {
			model.addAttribute("errorMessage", "비밀번호가 틀렸습니다.");
			return "/user/login";
		}
		
		session.setAttribute("userId", authentication.getUserId());
		return "redirect:/";
	}
	
	@RequestMapping("/user/{userId}/form")
	public String modifyView(@PathVariable String userId, Model model) {
		
		//TODO Id값의 존재유무를 체크
		//TODO 현재 SessionId와 요청 Id가 동일한지 체크
		model.addAttribute("user", userDao.findById(userId));
		
		return "/user/register";
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.PUT)
	public String modify(@Valid User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			logger.error("bindingReulst has Error","bindingResult Error");
			return "/user/register";
		}
			
		logger.info("User : {}", user);
		userDao.update(user);
		logger.info("selectUser : {}", userDao.findById(user.getUserId()));
		return "redirect:/";
	}
}
