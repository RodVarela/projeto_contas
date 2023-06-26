package br.com.cotiinformatica.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.ContasConsultaDto;
import br.com.cotiinformatica.dtos.ContasEdicaoDto;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class ContasEdicaoController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@RequestMapping(value = "/admin/contas-edicao")
	public ModelAndView contasEdicao(Integer idConta, HttpServletRequest request) {
		// WEB-INF/views/admin/contas-edicao.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contas-edicao");
		
		try {
			//capturando o usuario da sessao (autenticado no navegador)
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			//buscar no banco de dados a conta atraves do ID
			Conta conta = contaRepository.findById(idConta, usuario.getIdUsuario());
			
			//criando um DTO com os dados da conta para edicao
			ContasEdicaoDto dto = new ContasEdicaoDto();
			dto.setIdConta(conta.getIdConta());
			dto.setNome(conta.getNome());
			dto.setData(new SimpleDateFormat("yyyy-MM-dd").format(conta.getData()));
			dto.setValor(conta.getValor().toString());
			dto.setObservacoes(conta.getObservacoes());
			dto.setIdCategoria(conta.getCategoria().getIdCategoria());
			
			//enviando os dados para a pagina
			modelAndView.addObject("dto", dto);
			modelAndView.addObject("categorias", categoriaRepository.findAll(usuario.getIdUsuario()));			
			
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
			
		
		return modelAndView;
	}
	
	//Metodo para realizar a atualização/edicao de uma conta
	@RequestMapping(value="/admin/contas-edicao-post", method = RequestMethod.POST)
	public ModelAndView contasEdicaoPost(ContasEdicaoDto dto, HttpServletRequest request) {
		
		// WEB-INF/views/admin/contas-edicao.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contas-consulta");
		
		try {
			
			//capturando o usuario da sessao (autenticado no navegador)
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			//atualizar os dados da conta
			Conta conta = new Conta();
			conta.setIdConta(dto.getIdConta());
			conta.setNome(dto.getNome());
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getData()));
			conta.setValor(Double.parseDouble(dto.getValor()));
			conta.setObservacoes(dto.getObservacoes());
			conta.setIdCategoria(dto.getIdCategoria());
			conta.setIdUsuario(usuario.getIdUsuario());
			
			contaRepository.update(conta); //atualizando no banco de dados
			
			modelAndView.addObject("mensagem", "Conta atualizada com sucesso!");
			
			//gerando uma nova consulta de contas
			Date dtInicio = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getSession().getAttribute("dataInicio"));
			Date dtFim = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getSession().getAttribute("dataFim"));
			List<Conta> contas = contaRepository.findAll(dtInicio, dtFim, usuario.getIdUsuario());		
			modelAndView.addObject("contas", contas);						
		
			//modelAndView.addObject("dto", dto);
			//modelAndView.addObject("categorias", categoriaRepository.findAll(usuario.getIdUsuario()));
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		
		//Convertendo as datas em sessao para String
		String dataI = (String) request.getSession().getAttribute("dataInicio");
		String dataF = (String) request.getSession().getAttribute("dataFim");
		
		//Atribuindo as datas da sessao ao DTO
		ContasConsultaDto consultaDto = new ContasConsultaDto();
		consultaDto.setDataInicio(dataI);
		consultaDto.setDataFim(dataF);
		
		modelAndView.addObject("dto", consultaDto);
		return modelAndView;
	}
	
	
	
	//Metodo para voltar a pagina de consulta ao clicar no botao de voltar na pagina de edicao
	//levando as datas que estao salvas em sessao e realizando uma nova consulta
	@RequestMapping(value="/admin/contas-edicao-voltar")
	public ModelAndView contasEdicaoVoltar(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("admin/contas-consulta");
		
		try {
			
			//capturando o usuario da sessao (autenticado no navegador)
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			//Convertendo as datas em sessao para String
			String dataI = (String) request.getSession().getAttribute("dataInicio");
			String dataF = (String) request.getSession().getAttribute("dataFim");
			
			//gerando uma nova consulta de contas
			Date dtInicio = new SimpleDateFormat("yyyy-MM-dd").parse(dataI);
			Date dtFim = new SimpleDateFormat("yyyy-MM-dd").parse(dataF);
			List<Conta> contas = contaRepository.findAll(dtInicio, dtFim, usuario.getIdUsuario());		
			modelAndView.addObject("contas", contas);						
					
			
			//Atribuindo as datas da sessao ao DTO
			ContasConsultaDto consultaDto = new ContasConsultaDto();
			consultaDto.setDataInicio(dataI);
			consultaDto.setDataFim(dataF);
			modelAndView.addObject("dto", consultaDto);
			
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());		
		}
				
		return modelAndView;
	}
	
	
}
