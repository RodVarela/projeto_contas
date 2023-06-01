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
  			<h5>Dashboard principal</h5>
  			<p>Seja bem vindo ao projeto contas!</p>
  			
  			<div class="row mt-3">
  				<div class="col-md-6">
  					<div id="graficoPizza"></div>
  				</div>
  				<div class="col-md-6">
  					<div id="graficoColunas"></div>	
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
      ['Chrome', 45.0],
      ['Firefox', 26.8],
      ['Edge', 12.8],
      ['Safari', 8.5],
      ['Outros', 6.9]
    ];

    // Dados para o gráfico de colunas
    var dadosColunas = {
      categories: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio'],
      series: [{
        name: 'Vendas',
        data: [100, 80, 120, 90, 110]
      }]
    };

    // Configurações do gráfico de pizza
    var opcoesPizza = {
      chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
      },
      title: {
        text: 'Distribuição de Navegadores'
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
        name: 'Porcentagem',
        colorByPoint: true,
        data: dadosPizza
      }]
    };

    // Configurações do gráfico de colunas
    var opcoesColunas = {
      chart: {
        type: 'column'
      },
      title: {
        text: 'Vendas Mensais'
      },
      xAxis: {
        categories: dadosColunas.categories
      },
      yAxis: {
        title: {
          text: 'Valor'
        }
      },
      series: dadosColunas.series
    };

    // Criação dos gráficos
    var graficoPizza = Highcharts.chart('graficoPizza', opcoesPizza);
    var graficoColunas = Highcharts.chart('graficoColunas', opcoesColunas);
    
  </script>
  
</body>
</html>
