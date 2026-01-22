package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public String listAllUsers(Model model) {
		List<User> allUsers = service.allUsers();
		model.addAttribute("allUsers",allUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> listRoles = service.listRoles();
		User user = new User();
		user.setEnabled(true);
		model.addAttribute("user",user);
		model.addAttribute("listRoles",listRoles);
		return "user-form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user,RedirectAttributes redirectAttribute) {
		System.out.println(user);
		User savedUser = service.saveUser(user);
		if(savedUser != null) {
			redirectAttribute.addFlashAttribute("message", "The user has been saved successfully");
		}
		return "redirect:/users";
	}
	
}
