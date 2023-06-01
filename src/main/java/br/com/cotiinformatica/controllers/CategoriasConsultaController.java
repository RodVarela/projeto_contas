package br.com.cotiinformatica.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.CategoriaRepository;

@Controller
public class CategoriasConsultaController {

	@Autowired //autoinicialização
	private CategoriaRepository categoriaRepository;
	
	//Método executando quando a página é aberta
	@RequestMapping(value = "/admin/categorias-consulta")
	public ModelAndView categoriasConsulta(HttpServletRequest request) {
		
		// WEB-INF/views/admin/categorias-consulta.jsp
		ModelAndView modelAndView = new ModelAndView("admin/categorias-consulta");
		
		try {
			//capturando o usuário armazenado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			//consultando todas as categorias cadastradas para o usuário
			List<Categoria> categorias = categoriaRepository.findAll(usuario.getIdUsuario());
			
			//enviando a lista de categorias para a página
			modelAndView.addObject("categorias", categorias);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		
		return modelAndView;
	}
	
	//Método executado quando o link de exclusão for clicado
	@RequestMapping(value = "/admin/excluir-categoria")
	public ModelAndView excluirCategoria(Integer idCategoria, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("/admin/categorias-consulta");
		
		try {
			//capturando o usuário armazenado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			//buscar a categoria no banco de dados e excluindo
			Categoria categoria = categoriaRepository.findById(idCategoria, usuario.getIdUsuario());
			categoriaRepository.delete(categoria);
			
			modelAndView.addObject("mensagem", "Categoria excluída com sucesso.");
			
			//fazendo uma nova consulta de categorias para exibir na página
			List<Categoria> categorias = categoriaRepository.findAll(usuario.getIdUsuario());
			modelAndView.addObject("categorias", categorias);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		
		return modelAndView;
	}
	
}


