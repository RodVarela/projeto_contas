package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.EmailMessageDto;
import br.com.cotiinformatica.dtos.RecuperarSenhaDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.helpers.EmailMessageHelper;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class RecuperarSenhaController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Método executado quando a página é aberta
	@RequestMapping(value = "/recuperar-senha") // Rota no navegador
	public ModelAndView recuperarSenha() {
		// WEB-INF/views/recuperar-senha.jsp
		ModelAndView modelAndView = new ModelAndView("recuperar-senha");

		modelAndView.addObject("dto", new RecuperarSenhaDto());

		return modelAndView;
	}

	// Método para capturar o Submit Post do formulário
	@RequestMapping(value = "/recuperar-senha-post", method = RequestMethod.POST)
	public ModelAndView recuperarSenhaPost(RecuperarSenhaDto dto) {

		ModelAndView modelAndView = new ModelAndView("recuperar-senha");

		try {

			// consultar o usuario no banco de dados atraves do email informado
			Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());

			// verificando se o osuario foi encontrado
			if (usuario != null) {

				// gerando uma nova senha para o usuario
				Faker faker = new Faker();
				usuario.setSenha(faker.internet().password(8, 10, true, true, true));

				// criando a mensagem que sera enciada para o email do usuario
				EmailMessageDto messageDto = new EmailMessageDto();
				messageDto.setEmailDestinatario(usuario.getEmail());
				messageDto.setAssunto("Recuperação de senha de usuário - Projeto Contas");
				messageDto.setConteudoMensagem("Olá " + usuario.getNome() + "\n\n"
						+ "Uma nova senha de acesso foi gerada para você. " + "Acesse o sistema com a senha: "
						+ usuario.getSenha() + "\n\n" + "Att\nEquipe COTI Informática");
				
				//enviando o email..
				EmailMessageHelper.send(messageDto);
				//atualizar a senha no banco de dados
				usuarioRepository.update(usuario);
				
				modelAndView.addObject("mensagem", "Recuperação de senha realizada com sucesso. Verifique sua caixa de email.");
				dto = new RecuperarSenhaDto();


			} else {
				modelAndView.addObject("mensagem", "Usuário não encontrado, verifique o email informado.");
			}

		} catch (Exception e) {
			modelAndView.addObject("mensagem", "Falha ao recuperar senha: " + e.getMessage());
		}

		modelAndView.addObject("dto", dto);
		return modelAndView;
	}

}
