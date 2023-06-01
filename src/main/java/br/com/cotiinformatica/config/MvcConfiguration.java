package br.com.cotiinformatica.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.cotiinformatica.filters.CacheControlFilter;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import br.com.cotiinformatica.repositories.ContaRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Configuration
@ComponentScan(basePackages = "br.com.cotiinformatica")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CacheControlFilter());
	}

	/*
	 * Método para configurar a conexão com o banco de dados no projeto Spring MVC
	 */
	@Bean
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/bd_projeto_contas");
		dataSource.setUsername("postgres");
		dataSource.setPassword("coti");

		return dataSource;
	}

	/*
	 * Método para configurar a classe UsuarioRepository, definindo o seu datasource
	 * (configuração para acesso ao BD)
	 */
	@Bean
	public UsuarioRepository getUsuarioRepository() {
		return new UsuarioRepository(getDataSource());
	}
	
	/*
	 * Método para configurar a classe CategoriaRepository, definindo o seu datasource
	 * (configuração para acesso ao BD)
	 */
	@Bean
	public CategoriaRepository getCategoriaRepository() {
		return new CategoriaRepository(getDataSource());
	}
	
	/*
	 * Método para configurar a classe ContaRepository, definindo o seu datasource
	 * (configuração para acesso ao BD)
	 */
	@Bean
	public ContaRepository getContaRepository() {
		return new ContaRepository(getDataSource());
	}
}











