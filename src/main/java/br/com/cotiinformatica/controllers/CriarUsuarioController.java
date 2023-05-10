package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CriarUsuarioController {
	
	@RequestMapping(value = "/criar-usuario") //Rota do navegador
	public ModelAndView criarUsuario() {
		// WEB-INF/views/criar-usuario.jsp
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		return modelAndView;
	}

}
