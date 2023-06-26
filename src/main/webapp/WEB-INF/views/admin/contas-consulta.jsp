<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Projeto Contas</title>
  
  <!-- controle de cache -->
  <jsp:include page="/WEB-INF/views/components/cache-control.jsp"></jsp:include>
  
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>

</head>
<body>

  <!-- menu do sistema -->
  <jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>
 
  <c:if test="${mensagem != null}">
	  <div class="alert alert-success alert-dismissible fade show">
	    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	    <strong>${mensagem}</strong> 
	  </div>
  </c:if>
  
  <div class="m-4">
  	<div class="card">
  	
  		<div class="card-body">  		
  		
  			<h5>Consulta de contas</h5>
  			<p>Consulte suas contas por período de datas.</p>
  			<hr/>
  			
  			<div class="mb-3">
  				<strong>${mensagem}</strong>
  			</div>
  			
  			<form method="post" action="contas-consulta-post">
  			
  				<div class="row mb-2">
  					<div class="col-md-2">
  						<form:input path="dto.dataInicio" type="date" class="form-control" required="required"/>
  					</div>
  					<div class="col-md-2">
  						<form:input path="dto.dataFim" type="date" class="form-control" required="required"/>
  					</div>
  					<div class="col-md-8">
  						<input type="submit" class="btn btn-success" value="Realizar Consulta"/>
  					</div>
  				</div>
  			
  			</form>  	
  			
  			<c:if test="${contas != null && contas.size() == 0}">
  				<div class="mt-3">
  					<strong>Nenhuma conta foi encontrada para o período de datas especificado.</strong>
  				</div>
  			</c:if>
  			  			
  			<c:if test="${contas != null && contas.size() > 0}">
  			<div class="table-responsive mt-3">
				<table id="tabela_contas" class="table table-sm mt-3">
					<thead>
						<tr>
							<th>Data</th>
							<th>Nome da conta</th>
							<th>Valor</th>
							<th>Categoria</th>
							<th>Tipo</th>
							<th>Operações</th>
						</tr>
					</thead>
					<tbody>		
					
						<c:forEach items="${contas}" var="item">
						<tr>
							<td>
								<fmt:formatDate value="${item.data}" pattern="dd/MM/yyyy"/>
							</td>
							<td>
								${item.nome}
								<span class="badge rounded-pill bg-info" style="font-size:10px" data-toggle="obs-tooltip" title="${item.observacoes}">
									i
								</span>							
							</td>
							<td>
								<fmt:formatNumber value="${item.valor}" type="currency"/>
							</td>
							<td>
								${item.categoria.nome}
							</td>
							<td>
								${item.categoria.tipo}
							</td>
							<td>
								<a href="/projeto_contas/admin/contas-edicao?idConta=${item.idConta}" 
									class="btn btn-sm btn-outline-primary">
									Editar 
								</a> 
								<a href="/projeto_contas/admin/excluir-conta?idConta=${item.idConta}&dataInicio=${dto.dataInicio}&dataFim=${dto.dataFim}" 
									class="btn btn-sm btn-outline-danger"
									onclick="return confirm('Deseja realmente excluir a conta: ${item.nome}?');">
									Excluir 
								</a>
							</td>
						</tr>
						</c:forEach>	
													
					</tbody>
				</table>
			</div>
  			</c:if>
  			
  		</div>
  	</div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	
  <script>
      $(document).ready(function(){
          $("#tabela_contas").DataTable({
              language: {
                  url: 'https://cdn.datatables.net/plug-ins/1.13.4/i18n/pt-BR.json'
              }
          });
      })
  </script>
  
  <script>
		$(document).ready(function(){
 		$('[data-toggle="obs-tooltip"]').tooltip();   
		});
 </script>

</body>
</html>
