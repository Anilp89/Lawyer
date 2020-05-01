package com.jeshtah.demoLawyer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jeshtah.demoLawyer.model.ClientDetails;
import com.jeshtah.demoLawyer.repository.ClientDetailsRepository;


@Controller
public class LoginController {
	@Autowired
	private ClientDetailsRepository clientRepo;


	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@PostMapping(value = "/lawyerDashboard")
	public ModelAndView lawyerDashboard(HttpServletRequest req, HttpServletResponse res) {
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		if (uname.equals("admin") && pwd.equals("admin")) {
			List<ClientDetails> client = (List<ClientDetails>) clientRepo.findAll();
			return new ModelAndView("lawyerDashboard","clients",client);
		}
		return new ModelAndView("login", "messagge", "Please Enter Valid Credentials");
	}
}
