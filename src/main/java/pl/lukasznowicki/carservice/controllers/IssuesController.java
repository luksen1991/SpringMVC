package pl.lukasznowicki.carservice.controllers;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.lukasznowicki.carservice.services.IssuesService;

@Controller()
@RequestMapping("/issue/**")
public class IssuesController {

	private IssuesService issuesService;

	@Autowired
	public IssuesController(IssuesService issuesService) {
		super();
		this.issuesService = issuesService;
	}

	@RequestMapping(path = "/jeden/{value}", method = RequestMethod.GET)
	@ResponseBody
	public String test(@PathVariable("value") String value) {
		return "Mapowanie GET VALUE:"+value;
	}

	@RequestMapping(path = "/dwa/{value}/{value2}", method = RequestMethod.GET)
	@ResponseBody
	public String testIssue2(@PathVariable Map<String,String>args) {
		return "MAPA:  "+args;
	}

	@RequestMapping(path = {"/issueId/{value4}/{value3}","/issueId/{value4}"}, method = RequestMethod.GET)
	@ResponseBody
	public String getHelloWorld(@PathVariable("value3") Optional<String> temp,@PathVariable("value4") String value4) {
		return "Mapowanie POST"+value4+" "+temp.orElse("jeden");
	}
}
