package br.com.cotiinformatica.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.DashboardDto;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.TipoCategoria;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class DashboardController {

	@Autowired
	private ContaRepository contaRepository;

	@RequestMapping(value = "/admin/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) {
		// WEB-INF/views/admin/dashboard.jsp
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");

		try {

			// capturar o primeiro e o ultimo dia do mês atual
			LocalDate primeiroDiaDoMes = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
			LocalDate ultimoDiaDoMes = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

			// capturar o usuário autenticado no sistema (sessão)
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");

			// converter as datas para java.util.Date
			Date dataInicio = Date.from(primeiroDiaDoMes.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date dataFim = Date.from(ultimoDiaDoMes.atStartOfDay(ZoneId.systemDefault()).toInstant());

			// consultar todas as contas do usuário
			// dentro do período de datas
			List<Conta> contas = contaRepository.findAll(dataInicio, dataFim, usuario.getIdUsuario());

			// somando o total de receitas e despesas
			Double totalReceitas = 0.0;
			Double totalDespesas = 0.0;

			for (Conta item : contas) {
				if (item.getCategoria().getTipo() == TipoCategoria.RECEITAS)
					totalReceitas += item.getValor(); // somar cada receita
				else if (item.getCategoria().getTipo() == TipoCategoria.DESPESAS)
					totalDespesas += item.getValor(); // somar cada despesa
			}

			// enviando os dados para a página
			List<DashboardDto> somatorioContas = new ArrayList<DashboardDto>();
			somatorioContas.add(new DashboardDto("Total de Receitas", totalReceitas));
			somatorioContas.add(new DashboardDto("Total de Despesas", totalDespesas));

			modelAndView.addObject("somatorioContas", somatorioContas);
			modelAndView.addObject("dataInicio", dataInicio);
			modelAndView.addObject("dataFim", dataFim);	


		} catch (Exception e) {
			modelAndView.addObject("mensagem", "falha ao gerar dashboard: " + e.getMessage());
		}

		return modelAndView;
	}

}
