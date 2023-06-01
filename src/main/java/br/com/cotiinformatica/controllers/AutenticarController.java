package br.com.cotiinformatica.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.AutenticarDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class AutenticarController {

	//atributos
	@Autowired //inicialização automática
	private UsuarioRepository usuarioRepository;
	
	// método para abrir a página de autenticação
	@RequestMapping(value = "/") // página raiz do projeto (inicial)
	public ModelAndView autenticar() {
		// WEB-INF/views/autenticar.jsp (nome da página JSP)
		ModelAndView modelAndView = new ModelAndView("autenticar");
		// enviando uma instância do DTO para a página
		modelAndView.addObject("dto", new AutenticarDto());
		// retorando para a página
		return modelAndView;
	}

	// método para receber o SUBMIT POST do formulário
	@RequestMapping(value = "/autenticar-post", method = RequestMethod.POST)
	public ModelAndView autenticarPost(AutenticarDto dto, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("autenticar");

		try {
			
			//procurar o usuário no banco de dados através do email e da senha
			Usuario usuario = usuarioRepository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());
			
			//verificar se o usuário foi encontrado
			if(usuario != null) {
				
				//Gravar os dados do usuário autenticado em sessão
				request.getSession().setAttribute("auth_usuario", usuario);
				
				//redirecionar o usuário para a página principal da área restrita do sistema
				modelAndView.setViewName("redirect:/admin/dashboard");				
			}
			else {
				modelAndView.addObject("mensagem", "Acesso negado. Usuário inválido.");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}

		modelAndView.addObject("dto", dto);
		return modelAndView;
	}
}
