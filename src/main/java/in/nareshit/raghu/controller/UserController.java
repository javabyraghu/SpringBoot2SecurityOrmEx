package in.nareshit.raghu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.raghu.model.User;
import in.nareshit.raghu.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;

	//1. show Register page
	@GetMapping("/register")
	public String showReg() {
		
		return "UserRegister";
	}
	 
	//2. on click submit button
	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute User user, 
			Model model) 
	{
		Integer id = service.saveUser(user);
		model.addAttribute("message", "User '"+id+"' created");
		return "UserRegister";
	}
	
	//3. show login page
	@GetMapping("/login")
	public String showLogin() {
		
		return "UserLogin";
	}
	
	
}
