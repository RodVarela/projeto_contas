package br.com.cotiinformatica.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.CategoriasCadastroDto;
import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.TipoCategoria;
import br.com.cotiinformatica.repositories.CategoriaRepository;

@Controller
public class CategoriasCadastroController {

	@Autowired //auto-inicialização
	private CategoriaRepository categoriaRepository;
	
	//Método para abrir a página de cadastro de categorias
	@RequestMapping(value = "/admin/categorias-cadastro")
	public ModelAndView categoriasCadastro() {
		
		// WEB-INF/views/admin/categorias-cadastro.jsp
		ModelAndView modelAndView = new ModelAndView("admin/categorias-cadastro");
		
		//criando um objeto DTO que será enviado para a página (formulário)
		modelAndView.addObject("dto", new CategoriasCadastroDto());
		
		//criando um objeto para enviar para a página as opções do enum
		modelAndView.addObject("tipos", TipoCategoria.values());
		
		return modelAndView;
	}
	
	//Método para receber o SUBMIT POST do formulário de cadastro de categorias
	@RequestMapping(value = "/admin/categorias-cadastro-post", method = RequestMethod.POST)
	public ModelAndView categoriasCadastroPost(CategoriasCadastroDto dto, HttpServletRequest request) {
		
		// WEB-INF/views/admin/categorias-cadastro.jsp
		ModelAndView modelAndView = new ModelAndView("admin/categorias-cadastro");
		
		try {
			
			//capturando o usuário da sessão (autenticado no navegador)
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			Categoria categoria = new Categoria();
			categoria.setNome(dto.getNome());
			categoria.setTipo(TipoCategoria.valueOf(dto.getTipo()));
			categoria.setIdUsuario(usuario.getIdUsuario());
			
			//gravando no banco de dados
			categoriaRepository.create(categoria);
			
			modelAndView.addObject("mensagem", "Categoria cadastrada com sucesso!");
			dto = new CategoriasCadastroDto();
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}

		modelAndView.addObject("dto", dto);
		modelAndView.addObject("tipos", TipoCategoria.values());
		
		return modelAndView;
	}	
}




