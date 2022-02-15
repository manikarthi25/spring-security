package com.spring.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

	@GetMapping("/")
	public String getAppStatus() {
		return "Success";
	}

	@GetMapping("/user")
	public String getUser() {
		return "user";
	}

	@GetMapping("/admin")
	public String getAdmin() {
		return "admin";
	}

}
