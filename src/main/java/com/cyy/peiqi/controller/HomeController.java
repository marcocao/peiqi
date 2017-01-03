package com.cyy.peiqi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cyy.peiqi.domain.User;
import com.cyy.peiqi.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Model model) {
		model.addAttribute("message", "Welcome to my home page");
		return "home";
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String addUser(Model model) {
		User user = new User();
		user.setEmailId("marco.cao@abc.com");
		user.setFirstName("Marco");
		user.setLastName("Cao");
		user.setPassword("abc123");

		userService.saveUser(user);

		model.addAttribute("message", userService.getUsers());
		return "home";
	}
	
	@RequestMapping(value="/getusers", method=RequestMethod.GET)
	public String getUsers(Model model)
	{
		model.addAttribute("message", userService.getUsers());
		
		return "home";
	}
}
