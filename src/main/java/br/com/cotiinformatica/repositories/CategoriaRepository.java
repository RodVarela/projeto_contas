package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.enums.TipoCategoria;

public class CategoriaRepository {

	// atributo
	private JdbcTemplate jdbcTemplate;

	// construtor
	public CategoriaRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//método para inserir categoria no banco de dados
	public void create(Categoria categoria) throws Exception {

		String query = "insert into categoria(nome, tipo, idusuario) values(?,?,?)";
		Object[] params = {
			categoria.getNome(),
			categoria.getTipo() == TipoCategoria.RECEITAS ? 1 
				: categoria.getTipo() == TipoCategoria.DESPESAS ? 2 
				: 0,
			categoria.getIdUsuario()
		};
		
		jdbcTemplate.update(query, params);
	}

	public void update(Categoria categoria) throws Exception {

		String query = "update categoria set nome=?, tipo=? where idcategoria=? and idusuario=?";
		Object[] params = {
			categoria.getNome(),
			categoria.getTipo() == TipoCategoria.RECEITAS ? 1 
					: categoria.getTipo() == TipoCategoria.DESPESAS ? 2 
					: 0,
			categoria.getIdCategoria(),
			categoria.getIdUsuario()
		};
		
		jdbcTemplate.update(query, params);
	}

	public void delete(Categoria categoria) throws Exception {

		String query = "delete from categoria where idcategoria=? and idusuario=?";
		Object[] params = {
			categoria.getIdCategoria(),
			categoria.getIdUsuario()
		};
		
		jdbcTemplate.update(query, params);
	}

	public List<Categoria> findAll(Integer idUsuario) throws Exception {

		String query = "select * from categoria where idusuario = ? order by nome";
		Object[] params = { idUsuario };
		
		List<Categoria> categorias = jdbcTemplate.query(query, params, new RowMapper<Categoria>() {

			@Override
			public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idcategoria"));
				categoria.setNome(rs.getString("nome"));
				categoria.setTipo(rs.getInt("tipo") == 1 ? TipoCategoria.RECEITAS 
						: rs.getInt("tipo") == 2 ? TipoCategoria.DESPESAS
						: null);
				categoria.setIdUsuario(rs.getInt("idusuario"));
				return categoria;
			}				
		});
		
		return categorias;
	}

	public Categoria findById(Integer idCategoria, Integer idUsuario) throws Exception {

		String query = "select * from categoria where idcategoria=? and idusuario=?";
		Object[] params = {
			idCategoria, idUsuario	
		};
		
		List<Categoria> categorias = jdbcTemplate.query(query, params, new RowMapper<Categoria>() {

			@Override
			public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt("idcategoria"));
				categoria.setNome(rs.getString("nome"));
				categoria.setTipo(rs.getInt("tipo") == 1 ? TipoCategoria.RECEITAS 
						: rs.getInt("tipo") == 2 ? TipoCategoria.DESPESAS
						: null);
				categoria.setIdUsuario(rs.getInt("idusuario"));
				return categoria;
			}				
		});
		
		//verificando se a lista trouxe 1 categoria do banco de dados
		if(categorias.size() == 1)
			return categorias.get(0); //retornar o primeiro elemento da lista
		else 
			return null; //retornar vazio
	}
}
