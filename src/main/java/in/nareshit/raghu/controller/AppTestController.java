package in.nareshit.raghu.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppTestController {

	@GetMapping("/home")
	public String showHome() {
		return "HomePage";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		return "AdminPage";
	}
	
	@GetMapping("/emp")
	public String showEmployee() {
		return "EmployeePage";
	}
	
	@GetMapping("/profile")
	public String showProfile(Principal p,Model model) 
	{
		model.addAttribute("userName", p.getName());
		System.out.println(p.getClass().getName());
		return "ProfilePage";
	}
	
	@GetMapping("/denied")
	public String showAccessDenied() {
		return "AccessDenied";
	}
}
