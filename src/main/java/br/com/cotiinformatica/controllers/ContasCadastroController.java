package br.com.cotiinformatica.controllers;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.ContasCadastroDto;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class ContasCadastroController {

	@Autowired //autoinicialização
	private CategoriaRepository categoriaRepository;
	
	@Autowired //autoinicialização
	private ContaRepository contaRepository;
	
	@RequestMapping(value = "/admin/contas-cadastro")
	public ModelAndView contasCadastro(HttpServletRequest request) {
		
		// WEB-INF/views/admin/contas-cadastro.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contas-cadastro");
		
		try {
			//capturando o usuário armazenado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			//DTO utilizado para capturar os campos do formulário
			modelAndView.addObject("dto", new ContasCadastroDto());
			
			//criando uma lista com as categorias cadastradas pelo usuário
			modelAndView.addObject("categorias", categoriaRepository.findAll(usuario.getIdUsuario()));
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/contas-cadastro-post", method = RequestMethod.POST)
	public ModelAndView contasCadastroPost(ContasCadastroDto dto, HttpServletRequest request) {
	
		ModelAndView modelAndView = new ModelAndView("admin/contas-cadastro");
		
		try {
			//capturando o usuário armazenado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");			
			
			Conta conta = new Conta();
			conta.setNome(dto.getNome());
			conta.setValor(Double.parseDouble(dto.getValor()));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getData()));
			conta.setObservacoes(dto.getObservacoes());
			conta.setIdCategoria(dto.getIdCategoria());
			conta.setIdUsuario(usuario.getIdUsuario());
			
			//gravando a conta no banco de dados
			contaRepository.create(conta);
			
			modelAndView.addObject("mensagem", "Conta cadastrada com sucesso!");
			modelAndView.addObject("dto", new ContasCadastroDto());
			modelAndView.addObject("categorias", categoriaRepository.findAll(usuario.getIdUsuario()));
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		
		return modelAndView;
	}	
}




