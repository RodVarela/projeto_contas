<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Projeto Contas</title>
  
  <!-- controle de cache -->
  <jsp:include page="/WEB-INF/views/components/cache-control.jsp"></jsp:include>
  
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" /> 
</head>
<body>

  <!-- menu do sistema -->
  <jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>
  
  <div class="m-4">
  	<div class="card">
  		<div class="card-body">
  			
  			<h5>Cadastro de contas</h5>
  			<p>Inclua contas para realizar o controle de suas finanças.</p>
  			<hr/>
  			
  			<div class="mb-3">
  				<strong>${mensagem}</strong>
  			</div>
  			
  			<form method="post" action="contas-cadastro-post">
  			
  				<div class="row mb-2">
  					<div class="col-md-6">
  						<label>Nome da conta:</label>
  						<form:input path="dto.nome" type="text" class="form-control" required="required"/>
  					</div>
  					<div class="col-md-3">
  						<label>Valor da conta:</label>
  						<form:input path="dto.valor" pattern="^[0-9.]{1,10}$" type="text" class="form-control" required="required"/>
  					</div>
  					<div class="col-md-3">
  						<label>Data da conta:</label>
  						<form:input path="dto.data" type="date" class="form-control" required="required"/>
  					</div>
  				</div>
  				
  				<div class="row mb-2">
  					<div class="col-md-6">
  						<Label>Observações adicionais:</Label>
  						<form:textarea path="dto.observacoes" class="form-control" required="required"></form:textarea>
  					</div>
  					<div class="col-md-6">
  						<label>Selecione a categoria:</label>
  						<form:select path="dto.idCategoria" class="form-select" required="required">
  							<option value="">Escolha uma opção</option>
  							<c:forEach items="${categorias}" var="item">
  								<option value="${item.idCategoria}">${item.nome} (${item.tipo})</option>
  							</c:forEach>
  						</form:select>
  					</div>  					
  				</div>
  				
  				<div class="row mb-2">
  					<div class="col-md-12">
  						<input type="submit" class="btn btn-success" value="Realizar Cadastro"/>
  					</div>
  				</div>
  			
  			</form>  			
  			
  		</div>
  	</div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
  
  <script>
  	$(document).ready(function() {
	    $('.form-select').select2();
	});
  </script>
  
</body>
</html>
