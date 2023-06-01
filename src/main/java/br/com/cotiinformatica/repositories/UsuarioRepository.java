package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Usuario;

public class UsuarioRepository {

	//atributo
	private JdbcTemplate jdbcTemplate;
	
	//construtor
	public UsuarioRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void create(Usuario usuario) throws Exception {
		
		String query = "insert into usuario(nome, email, senha) values(?, ?, md5(?))";
		Object[] params = { usuario.getNome(), usuario.getEmail(), usuario.getSenha() };
		
		jdbcTemplate.update(query, params);
	}
	
	public Usuario findByEmail(String email) throws Exception {
		
		String query = "select * from usuario where email = ?";
		Object[] params = { email };
		
		List<Usuario> usuarios = jdbcTemplate.query(query, params, new RowMapper<Usuario>() {
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario usuario = new Usuario();
				
				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				return usuario;
			}
		});
		
		//verificando se algum usuário foi encontrado
		if(usuarios.size() >= 1)
			//retornando o primeiro usuário encontrado
			return usuarios.get(0); //posição zero -> primeiro elemento
		else
			return null; //retornar vazio!
	}
	
	public Usuario findByEmailAndSenha(String email, String senha) throws Exception {
		
		String query = "select * from usuario where email = ? and senha = md5(?)";
		Object[] params = { email, senha };
		
		List<Usuario> usuarios = jdbcTemplate.query(query, params, new RowMapper<Usuario>() {
			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario usuario = new Usuario();
				
				usuario.setIdUsuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				
				return usuario;
			}			
		});
		
		if(usuarios.size() >= 1) //verificando se algum registro foi encontrado
			return usuarios.get(0); //retornando o primeiro usuário encontrado
		else
			return null; //retornando vazio
	}
	
}


