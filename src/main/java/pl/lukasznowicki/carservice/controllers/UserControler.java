package pl.lukasznowicki.carservice.controllers;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.lukasznowicki.carservice.DAO.UserDAO;
import pl.lukasznowicki.carservice.services.IssuesService;

@Controller()
@RequestMapping("/user")
public class UserControler {

	@RequestMapping(path = "/{login}", method = RequestMethod.GET)
	@ResponseBody
	public String getHelloWorld(@PathVariable("login") String login, HttpServletRequest request,
			@CookieValue(name = "login", required=false) String logins, HttpServletResponse response){
		String answer =null;
		if(response!=null)
		{		response.addCookie(new Cookie("logins", login));
				answer="ok dodalem cookie";
		}else {
			answer = "mam juz cookie";
		}
		request.getSession().setAttribute("login", login);
		UserDAO userDAO = new UserDAO();
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		userDAO.setLogin(login);
		entityManager.getTransaction().begin();
		entityManager.persist(userDAO);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return "Ustawiłem w sesji login " + login+ " Cookie: "+answer;
	}
}
