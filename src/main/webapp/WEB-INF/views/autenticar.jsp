<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login de Usuários</title>
  
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
</head>
<body>

  <div class="container my-5">
    <div class="row justify-content-center">
      <div class="col-lg-4">
        <div class="card">
          <div class="card-header text-center">
            <h2 class="card-title">Projeto Contas</h2>
            <h5>Acesso ao Sistema</h5>
          </div>
          
          <div class="card-body">
          
          	<div class="m-3 text-center">
          		<h5>${mensagem}</h5>
          	</div>
          
            <form method="post" action="autenticar-post">
            
              <div class="mb-3">
                <label for="email" class="form-label">
                	Email de acesso:
                </label>
                <form:input 
                	path="dto.email"
                	type="email" 
                	class="form-control" 
                	id="email" 
                	name="email"
                	required="required"
                />
              </div>
              <div class="mb-3">
                <label for="senha" class="form-label">
                	Senha de acesso:
                </label>
                <form:input 
                	path="dto.senha"
                	type="password" 
                	class="form-control" 
                	id="senha" 
                	name="senha" 
                	required="required"
                	pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()])[A-Za-z\d!@#$%^&*()]{8,}$"
                	title="Informe uma senha com pelo menos 1 letra maiúscula, 1 letra minúscula, 1 número, 1 símbolo e no mínimo 8 caracteres."
                />
              </div>
              <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Entrar</button>
              </div>
              
            </form>
            
          </div>
          <div class="card-footer text-center">
            <a href="/projeto_contas/criar-usuario">Criar Usuário</a>             | 
            <a href="/projeto_contas/recuperar-senha">Esqueci Minha Senha</a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
