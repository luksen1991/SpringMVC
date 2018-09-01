package pl.lukasznowicki.carservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/parts/**")
public class PartsController {
	
	String orderParts;

}
