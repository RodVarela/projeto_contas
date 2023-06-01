<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
  						<input type="date" class="form-control" required="required"/>
  					</div>
  					<div class="col-md-2">
  						<input type="date" class="form-control" required="required"/>
  					</div>
  					<div class="col-md-8">
  						<input type="submit" class="btn btn-success" value="Realizar Consulta"/>
  					</div>
  				</div>
  			
  			</form>  	
  			
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
							
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>
								<a href="#" 
									class="btn btn-sm btn-outline-primary">
									Editar 
								</a> 
								<a href="#" 
									class="btn btn-sm btn-outline-danger">
									Excluir 
								</a>
							</td>
						</tr>
							
					</tbody>
				</table>
			</div>
  			
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
</body>
</html>
