package pl.lukasznowicki.carservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.lukasznowicki.carservice.services.AppIssueService;
import pl.lukasznowicki.carservice.services.IssuesService;

@Configuration
public class CarServiceAppConfiguration {

	@Bean
	public IssuesService getIssueService() {
		return new AppIssueService();
	}
}
