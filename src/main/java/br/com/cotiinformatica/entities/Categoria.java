package br.com.cotiinformatica.entities;

import java.util.List;

import br.com.cotiinformatica.enums.TipoCategoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Categoria {

	// atributos
	private Integer idCategoria;
	private String nome;
	private TipoCategoria tipo;
	private Integer idUsuario;

	// relacionamentos (composições)
	private Usuario usuario;
	private List<Conta> contas;
}
