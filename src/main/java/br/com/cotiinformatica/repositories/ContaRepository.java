package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.enums.TipoCategoria;

public class ContaRepository {

	private JdbcTemplate jdbcTemplate;
	
	public ContaRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void create(Conta conta) throws Exception {
		
		String query = "insert into conta(nome, valor, data, observacoes, idcategoria, idusuario) values(?, ?, ?, ?, ?, ?)";
		Object[] params = {
			conta.getNome(),
			conta.getValor(),
			new java.sql.Date(conta.getData().getTime()),
			conta.getObservacoes(),
			conta.getIdCategoria(),
			conta.getIdUsuario()
		};
		
		jdbcTemplate.update(query, params);		
	}
	
	public void update(Conta conta) throws Exception {
		
		String query = "update conta set nome=?, valor=?, data=?, observacoes=?, idcategoria=? where idconta=? and idusuario=?";
		Object[] params = {
			conta.getNome(),
			conta.getValor(),
			new java.sql.Date(conta.getData().getTime()),
			conta.getObservacoes(),
			conta.getIdCategoria(),
			conta.getIdConta(),
			conta.getIdUsuario()
		};
		
		jdbcTemplate.update(query, params);
	}
	
	public void delete(Conta conta) throws Exception {
		
		String query = "delete from conta where idconta=? and idusuario=?";
		Object[] params = {
			conta.getIdConta(),
			conta.getIdUsuario()
		};
		
		jdbcTemplate.update(query, params);
	}
	
	public List<Conta> findAll(Date dataInicio, Date dataFim, Integer idUsuario) throws Exception {
		
		String query = "select "
					 + "conta.idconta, conta.nome as nomeconta, conta.valor, conta.data, conta.observacoes, "
					 + "categoria.idCategoria, categoria.nome as nomecategoria, categoria.tipo "
					 + "from conta "
					 + "inner join categoria on conta.idcategoria = categoria.idcategoria "
					 + "where conta.idusuario = ? and conta.data between ? and ? "
					 + "order by conta.data desc";
		
		Object[] params = {
			idUsuario,
			new java.sql.Date(dataInicio.getTime()),
			new java.sql.Date(dataFim.getTime())
		};
		
		List<Conta> contas = jdbcTemplate.query(query, params, new RowMapper<Conta>() {

			@Override
			public Conta mapRow(ResultSet rs, int rowNum) throws SQLException {

				Conta conta = new Conta();
				conta.setCategoria(new Categoria());
				
				conta.setIdConta(rs.getInt("idconta"));
				conta.setNome(rs.getString("nomeconta"));
				conta.setValor(rs.getDouble("valor"));
				try {
					conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("data")));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				conta.setObservacoes(rs.getString("observacoes"));
				conta.getCategoria().setIdCategoria(rs.getInt("idcategoria"));
				conta.getCategoria().setNome(rs.getString("nomecategoria"));
				conta.getCategoria().setTipo(rs.getInt("tipo") == 1 ? TipoCategoria.RECEITAS 
						: rs.getInt("tipo") == 2 ? TipoCategoria.DESPESAS
						: null);
				
				return conta;
			}			
		});
		
		return contas;
	}
	
	public Conta findById(Integer idConta, Integer idUsuario) throws Exception {
		
		String query = "select "
				 + "conta.idconta, conta.nome as nomeconta, conta.valor, conta.data, conta.observacoes, conta.idusuario, "
				 + "categoria.idCategoria, categoria.nome as nomecategoria, categoria.tipo "
				 + "from conta "
				 + "inner join categoria on conta.idcategoria = categoria.idcategoria "
				 + "where conta.idconta = ? and conta.idusuario = ? ";
		
		Object[] params = {
			idConta,
			idUsuario
		};
		
		List<Conta> contas = jdbcTemplate.query(query, params, new RowMapper<Conta>() {

			@Override
			public Conta mapRow(ResultSet rs, int rowNum) throws SQLException {

				Conta conta = new Conta();
				conta.setCategoria(new Categoria());
				
				conta.setIdConta(rs.getInt("idconta"));
				conta.setNome(rs.getString("nomeconta"));
				conta.setValor(rs.getDouble("valor"));
				try {
					conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("data")));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				conta.setObservacoes(rs.getString("observacoes"));
				conta.getCategoria().setIdCategoria(rs.getInt("idcategoria"));
				conta.getCategoria().setNome(rs.getString("nomecategoria"));
				conta.getCategoria().setTipo(rs.getInt("tipo") == 1 ? TipoCategoria.RECEITAS 
						: rs.getInt("tipo") == 2 ? TipoCategoria.DESPESAS
						: null);
				conta.setIdUsuario(rs.getInt("idusuario"));
				
				return conta;
			}			
		});
		
		//verificar se 1 conta foi encontrada
		if(contas.size() == 1) {
			return contas.get(0); //retornar a 1º conta encontrada
		}
		else {
			return null; //retornar vazio
		}
	}
}












