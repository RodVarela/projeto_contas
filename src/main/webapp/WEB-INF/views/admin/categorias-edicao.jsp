<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Projeto Contas</title>
  
  <!-- controle de cache -->
  <jsp:include page="/WEB-INF/views/components/cache-control.jsp"></jsp:include>
  
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
</head>
<body>

  <!-- menu do sistema -->
  <jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>
  
  <div class="m-4">
  
  	<div class="card">  	
  	
  		<div class="card-body">
  			<h5>Edição de categorias</h5>
  			<p>Utilize o formulário para alterar os dados da categoria.</p>
  			<hr/>
  			
  			<div class="mb-3">
  				<strong>${mensagem}</strong>
  			</div>
  			
  			<form method="post" action="categorias-edicao-post">
  			
  				<!-- campo oculto -->
  				<form:input type="hidden" path="dto.idCategoria"/>
  			
  				<div class="row mb-2">
  					<div class="col-md-9">
  						<label>Nome do categoria:</label>
  						<form:input
  							path="dto.nome"
  							type="text"
  							placeholder="Digite o nome da categoria"
  							required="required"
  							class="form-control"
  						/>
  					</div>
  					<div class="col-md-3">
  						<label>Selecione o tipo:</label>
  						<form:select path="dto.tipo" class="form-select" required="required">
  							<option value="">Escolha uma opção</option>
  							<form:options items="${tipos}"></form:options>
  						</form:select>
  					</div>
  				</div>
  				<div class="row mb-2">
  					<div class="col-md-12">
  						<input type="submit" class="btn btn-primary" value="Salvar Alterações"/>
  						<a href="/projeto_contas/admin/categorias-consulta" class="btn btn-light ms-2">
  							Voltar para a consulta
  						</a>
  					</div>
  				</div>
  			
  			</form>  			
  			
  		</div>
  		
  	</div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
