package it.corso.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"it.corso.controller","it.corso.service","it.corso.dao"})

@PropertySource(value = {"classpath:application.properties"})
public class ContextConfiguration implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public MessageSource messageSource() {
		
		ResourceBundleMessageSource source= new ResourceBundleMessageSource();
		source.setBasename("message");
		return source;
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		
		SpringResourceTemplateResolver resolver= new SpringResourceTemplateResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCacheable(true);
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		
		SpringTemplateEngine engine= new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		engine.setTemplateEngineMessageSource(messageSource());
		return engine;
	}
	
	@Bean
	public ThymeleafViewResolver viewResolver() {
		
		ThymeleafViewResolver resolver= new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		
		LocalValidatorFactoryBean validator= new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	@Override
	public Validator getValidator() {
		
		return validator();
	}
	
	
}
