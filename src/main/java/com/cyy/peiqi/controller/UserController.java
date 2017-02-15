package com.cyy.peiqi.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cyy.peiqi.domain.User;
import com.cyy.peiqi.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageResource;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Model model) {
		model.addAttribute("message", "Welcome to my home page");
		return "home";
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String addUser(Model model) {
		User user = new User();
		user.setEmailId("marco.cao@ge.com");
		user.setFirstName("Marco");
		user.setLastName("Cao");
		user.setPassword("abc123");

		userService.saveUser(user);

		model.addAttribute("message", userService.findAllUsers());
		return "home";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "user";
	}

	@RequestMapping(value = { "/new", "/edit-{id}-user" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user";
		}

		if (!userService.isUserEmailUnique(user.getId(), user.getEmailId())) {
			FieldError emailUniqueError = new FieldError("user", "emailId", messageResource
					.getMessage("non.unique.email_id", new String[] { user.getEmailId() }, Locale.getDefault()));
			result.addError(emailUniqueError);

			if (user.getId() != 0) {
				model.addAttribute("edit", true);
			}
			
			return "user";
		}

		if (user.getId() == 0) {
			userService.saveUser(user);
		} else {
			userService.updateUser(user);
		}

		return "redirect:/users";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUsers(Model model) {
		model.addAttribute("users", userService.findAllUsers());

		return "users";
	}

	@RequestMapping(value = "/edit-{id}-user", method = RequestMethod.GET)
	public String editUser(@PathVariable String id, Model model) {
		User user = userService.findById(Long.parseLong(id));
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "user";
	}

	/*
	 * @RequestMapping(value = "/edit-{id}-user", method = RequestMethod.POST)
	 * public String editUser(@Valid User user, BindingResult result, Model
	 * model) { if (result.hasErrors()) { return "user"; }
	 * 
	 * if (!userService.isUserEmailUnique(user.getId(), user.getEmailId())) {
	 * FieldError emailUniqueError = new FieldError("user", "emailId",
	 * messageResource .getMessage("non.unique.email_id", new String[] {
	 * user.getEmailId() }, Locale.getDefault()));
	 * result.addError(emailUniqueError); return "user"; }
	 * 
	 * userService.updateUser(user);
	 * 
	 * model.addAttribute("success", "User " + user.getEmailId() +
	 * "created successfully"); return "users"; }
	 */
	@RequestMapping(value = "/delete-{id}-user", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String id) {
		userService.deleteUserById(Long.parseLong(id));
		return "redirect:/users";
	}
}
