package kr.wearit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String entrance(Model model) {
		model.addAttribute("test", System.currentTimeMillis());
		return "index";
	}
}
