package kr.wearit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("/")
	public String entrance(Model model) {
		logger.info("logTest");
		model.addAttribute("test", System.currentTimeMillis());
		return "index";
	}
}
