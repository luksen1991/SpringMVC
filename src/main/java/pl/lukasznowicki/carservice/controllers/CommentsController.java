package pl.lukasznowicki.carservice.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import pl.lukasznowicki.carservice.DAO.CommentDAO;
import pl.lukasznowicki.carservice.DAO.UserDAO;
import pl.lukasznowicki.carservice.services.IssuesService;

@Controller()
@RequestMapping("/comments/")
public class CommentsController {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private IssuesService issuesService;

	@Autowired
	public CommentsController(IssuesService issuesService) {
		super();
		this.issuesService = issuesService;
	}

	@RequestMapping("/kic")
	public String test(Model model) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<CommentDAO> query = entityManager.createQuery("select c from CommentDAO c", CommentDAO.class);
		List<CommentDAO> result = query.getResultList();
		model.addAttribute("result", result);
		for (CommentDAO commentDAO : result) {
			System.out.println("Title: " + commentDAO.getTittle() + " Content: " + commentDAO.getContent());
		}
		entityManager.close();
		entityManagerFactory.close();
		return "index";
	}

//	@RequestMapping("/")
//	@ResponseBody
//	public String testParam(@RequestParam(name = "numberPage", required = false, defaultValue = "12") String pagenumber,
//			@RequestHeader() Map<String, String> args,
//			@RequestParam(name = "recordCount", required = false) String recordCount,
//			@CookieValue(name = "recordCount", required = false, defaultValue = "10") String cookie,
//			@SessionAttribute(name = "login") String login, HttpServletResponse response) {
//
//		String recCount = null;
//
//		if (recordCount != null) {
//			response.addCookie(new Cookie("recordCount", recordCount));
//			recCount = "Ustawiłem cookie " + recordCount;
//		} else {
//			recCount = "Odczytałem z ciastka: " + cookie;
//		}
//		return " RecCount: " + recCount + " " + login;
//	}

	@RequestMapping(name = "/addcomment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(@RequestBody MultiValueMap<String, String> args) {
		CommentDAO commentDAO = new CommentDAO();
		commentDAO.setTittle(args.get("Title").toString());
		commentDAO.setContent(args.get("Content").toString());
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(commentDAO);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return "Dodalem komentarz" + args.get("Title").toString();
	}
}
