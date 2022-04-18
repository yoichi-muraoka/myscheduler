package com.example.myscheduler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.myscheduler.domain.User;
import com.example.myscheduler.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("user", new User());
        return "login";
    }
    
    @PostMapping("/login")
    public String login(
    		@Valid User user,
    		Errors errors) {
    	
    	// 未入力
    	if(errors.hasErrors()) {
    		return "login";
    	}
    	
    	// 不正なIDまたはパスワード
    	if(!loginService.isCorrectIdPass(user.getLoginId(), user.getLoginPass())) {
    		errors.rejectValue("loginId", "incorrect.login.info");
    		return "login";
    	}
    	
    	return "redirect:/";
    }
    
    @GetMapping("/logout")
    public String logout() {
    	loginService.logout();
    	return "redirect:/login";
    }

}
