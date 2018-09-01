package pl.lukasznowicki.carservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import pl.lukasznowicki.carservice.converters.RecordRangeConverter;
import pl.lukasznowicki.carservice.services.IssuesService;
import pl.lukasznowicki.carservice.services.WebIssueService;

@Configuration
@ComponentScan(basePackages = { "pl.lukasznowicki.carservice.controllers" })
@EnableWebMvc
public class CarServiceWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public IssuesService getIssueService() {
		return new WebIssueService();
	}
	
	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
		freeMarkerViewResolver.setPrefix("");
		freeMarkerViewResolver.setSuffix(".ftl");
		freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
		return freeMarkerViewResolver;
	}
	
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:/WEB-INF/templates/");
		return freeMarkerConfigurer;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new RecordRangeConverter());
	}
	
	
	
}
