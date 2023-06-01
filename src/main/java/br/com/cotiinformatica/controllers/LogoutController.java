package br.com.cotiinformatica.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {

		// apagar os dados do usuário da sessão
		request.getSession().removeAttribute("auth_usuario");

		// redirecionar de volta para a página inicial do sistema
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/");

		return modelAndView;
	}
}
