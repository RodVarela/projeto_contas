package br.com.cotiinformatica.dtos;

import lombok.Data;

@Data
public class CriarUsuarioDto {

	private String nome;
	private String email;
	private String senha;
}
