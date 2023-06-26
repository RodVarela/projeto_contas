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
</head>
<body>

  <!-- menu do sistema -->
  <jsp:include page="/WEB-INF/views/components/menu.jsp"></jsp:include>
  
  <div class="m-4">
  	<div class="card">
  		<div class="card-body">  			
  			  			
  			<div class="row mt-2">
  				<div class="col-md-3">  	
  				
  					<div class="card">
  						<div class="card-body">
  							<p>
  								Data de início do período: <br/>
  								<strong><fmt:formatDate value="${dataInicio}" pattern="EEEE dd/MM/yyyy"/></strong>
  							</p>
  							<p>
  								Data de fim do período: <br/>
  								<strong><fmt:formatDate value="${dataFim}" pattern="EEEE dd/MM/yyyy"/></strong>
  							</p>
  						</div>
  					</div>
  							
  					<c:forEach items="${somatorioContas}" var="item">
  						<div class="alert alert-secondary mt-2 mb-2">
  							<h6>${item.name}</h6>
  							<h3><fmt:formatNumber value="${item.value}" type="currency"/></h3>
  						</div>
  					</c:forEach>  					
  				</div>
  				<div class="col-md-9">
  					<div id="graficoPizza"></div>
  				</div>  				
  			</div>
  			
  		</div>
  	</div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.highcharts.com/highcharts.js"></script>
  
  <script>
  
    // Dados para o gráfico de pizza
    var dadosPizza = [
    	<c:forEach items="${somatorioContas}" var="item">
    		['${item.name}', ${item.value}],
    	</c:forEach>
    ];

    // Configurações do gráfico de pizza
    var opcoesPizza = {
      chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
      },
      title: {
        text: 'Total de Receitas e Despesas'
      },
      tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
      },
      plotOptions: {
        pie: {
          allowPointSelect: true,
          cursor: 'pointer',
          dataLabels: {
            enabled: true,
            format: '<b>{point.name}</b>: {point.percentage:.1f} %'
          }
        }
      },
      series: [{
        name: 'Somatório',
        colorByPoint: true,
        data: dadosPizza
      }]
    };   

    // Criação dos gráficos
    var graficoPizza = Highcharts.chart('graficoPizza', opcoesPizza);
    
  </script>
  
</body>
</html>
