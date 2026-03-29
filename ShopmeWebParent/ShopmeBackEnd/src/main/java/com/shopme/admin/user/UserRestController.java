package com.shopme.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRestController {
	@Autowired
	private UserService service;
	@PostMapping("/users/new/checkEmail")
	public String checkDuplicateEmail(@RequestBody EmailCheckRequest email) {
		System.out.println(email.getEmail());
		return service.isEmailUnique(email.getEmail()) ? "ok" : "Duplicated";
	}
}
