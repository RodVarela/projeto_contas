package br.com.cotiinformatica.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContasEdicaoController {

	@RequestMapping(value = "/admin/contas-edicao")
	public ModelAndView contasEdicao() {
		// WEB-INF/views/admin/contas-edicao.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contas-edicao");
		return modelAndView;
	}
}
