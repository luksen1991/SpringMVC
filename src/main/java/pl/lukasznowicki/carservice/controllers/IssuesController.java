package pl.lukasznowicki.carservice.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.lukasznowicki.carservice.dto.IssueForm;
import pl.lukasznowicki.carservice.services.IssuesService;

@Controller()
@RequestMapping("/issue")
public class IssuesController {

	private IssuesService issuesService;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Autowired
	public IssuesController(IssuesService issuesService) {
		super();
		this.issuesService = issuesService;
	}

//	@RequestMapping(path = "/jeden/{value}", method = RequestMethod.GET)
//	@ResponseBody
//	public String test(@PathVariable("value") String value) {
//		return "Mapowanie GET VALUE:"+value;
//	}
//
//	@RequestMapping(path = "/dwa/{value}/{value2}", method = RequestMethod.GET)
//	@ResponseBody
//	public String testIssue2(@PathVariable Map<String,String>args) {
//		return "MAPA:  "+args;
//	}

	@RequestMapping(path = {"/issueId/{value4}/{value3}","/issueId/{value4}"}, method = RequestMethod.GET)
	@ResponseBody
	public String getHelloWorld(@PathVariable("value3") Optional<String> temp,@PathVariable("value4") String value4) {
		return "Mapowanie POST"+value4+" "+temp.orElse("jeden");
	}
	
	@RequestMapping(path="/add",method=RequestMethod.GET)
	public String addIssue(@ModelAttribute("issueForm") IssueForm issueForm) {
		return "addissue";
		
	}
	
	@RequestMapping(path="/listOfAll",method=RequestMethod.GET)
	public String getIssueList(Model model) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<IssueForm> query = entityManager.createQuery("select i from IssueForm i",IssueForm.class);
		List<IssueForm> result = query.getResultList();
		model.addAttribute("results", result);
		for (IssueForm issueForm : result) {
			System.out.println("ID: "+issueForm.getId());
			System.out.println("Tittle: "+issueForm.getTittle());
			System.out.println("Content: " +issueForm.getContent());
		}
		entityManager.close();
		entityManagerFactory.close();
		return "issueslist";
	}
	@RequestMapping(path="save",method=RequestMethod.POST)
	public String saveIssue(@Valid @ModelAttribute("issueForm") IssueForm issueForm,BindingResult bindingResult) {
		if(bindingResult.hasFieldErrors()) {
			return "addissue";
		}
		// Save to db
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(issueForm);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		entityManagerFactory.close();
		
		
		return"redirect:/issue/listOfAll";
	}
}
