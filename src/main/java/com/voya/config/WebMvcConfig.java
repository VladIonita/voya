package com.voya.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("com.voya")
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	String engValueDriver = System.getenv("CLEARDB_DATABASE_DRIVER");
	String envValue = System.getenv("CLEARDB_DATABASE_URL");
	String envValueUsername = System.getenv("CLEARDB_DATABASE_USERNAME");
	String envValuePassword = System.getenv("CLEARDB_DATABASE_PASSWORD");

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(engValueDriver);
		dataSource.setUrl(envValue);
		dataSource.setUsername(envValueUsername);
		dataSource.setPassword(envValuePassword);
		return dataSource;
	}

	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		rb.setBasenames(new String[] { "validation" });
		return rb;
	}
}
