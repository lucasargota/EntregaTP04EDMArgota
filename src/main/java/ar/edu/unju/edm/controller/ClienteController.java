package ar.edu.unju.edm.controller;

import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@Controller
public class ClienteController {
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	
	@Autowired
	@Qualifier("unImp")
	//@Qualifier ("ClienteServiceImp")
	IClienteService clienteService; 
	
	//@Autowired
	//@Qualifier ("OtraImp")
	//IClienteService otroClienteService;
	
	
	
	@GetMapping("/cliente/mostrar")
	public String cargarCliente(Model model) {
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}

	@PostMapping("/cliente/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unCliente") Cliente nuevoCliente, Model model) {
		LOGGER.info("METHOD: ingresando el metodo Guardar");
		clienteService.guardarCliente(nuevoCliente);		
		LOGGER.info("Tamaño del Listado: "+ clienteService.obtenerTodosClientes().size());
		trabajarConFechas(); //para ver como trabaja fechas 
		return "redirect:/cliente/mostrar";
	}
	
	public void trabajarConFechas() {
		//algunas cosas con fecha;
		//obtengo tres fechas
		LocalDate fecha1 = clienteService.obtenerTodosClientes().get(0).getFechaNacimiento();
		LocalDate fecha2 = LocalDate.now();
		//LocalDate fecha3 = LocalDate.of(2020, 3, 25);
		//calculo el período entre dos de ellas
		Period periodo = Period.between(fecha1,fecha2);
		//una vez que tengo el período puedo saber sus cantidades en días meses y años
		int dias = periodo.getDays();		
		System.out.println("dias: "+dias);
		//revisen la salida en la consola, debería dar la diferencia en días
		//todo esto nos debe hacer revisar la documentación de las clases LocalDate, LocalTime, LocalDateTime y Period
	}
}
