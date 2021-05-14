package org.nagarro.hrmanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.nagarro.hrmanager.model.User;
import org.nagarro.hrmanager.service.interfaces.JwtTokenService;
import org.nagarro.hrmanager.service.interfaces.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@Autowired
	JwtTokenService jwtTokenService;

	@GetMapping("/login")
	public String getLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
 
        return "redirect:/";
	}

	@GetMapping("/authuser")
	public String addJWT(HttpServletRequest request) {
		String userName = request.getUserPrincipal().getName();
		final String jwtToken = jwtTokenService.generateToken(userName);
		request.getSession().setAttribute("jwtToken", jwtToken);
		System.out.println(jwtToken);
		return "redirect:/";
	}

	@GetMapping("/register")
	public String registerPage(@ModelAttribute("user") User user) {
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user) {
		userDaoService.saveUser(user);
		return "redirect:login";
	}

}
