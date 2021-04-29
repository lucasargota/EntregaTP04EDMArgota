package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.model.Home;

@Controller
public class HomeController {
	  
	@Autowired
	Home unHome;
    
@GetMapping({"/home"})
	public String cargarHome(Model model) {
				
		model.addAttribute("home", unHome);
		return "home";
	}

}
