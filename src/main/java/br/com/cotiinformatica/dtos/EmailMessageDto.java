package br.com.cotiinformatica.dtos;

import lombok.Data;

@Data
public class EmailMessageDto {

	private String emailDestinatario;
	private String assunto;
	private String conteudoMensagem;
	
}
