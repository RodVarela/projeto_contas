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
            <form>
              <div class="mb-3">
                <label for="email" class="form-label">Email de acesso:</label>
                <input type="email" class="form-control" id="email" name="email" required>
              </div>
              <div class="mb-3">
                <label for="senha" class="form-label">Senha de acesso:</label>
                <input type="password" class="form-control" id="senha" name="senha" required>
              </div>
              <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Entrar</button>
              </div>
            </form>
          </div>
          <div class="card-footer text-center">
            <a href="/projeto_contas/criar-usuario">Criar Usuário</a> 
            | 
            <a href="/projeto_contas/recuperar-senha">Esqueci Minha Senha</a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>



