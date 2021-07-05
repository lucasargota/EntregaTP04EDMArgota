package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.model.Venta;
import ar.edu.unju.edm.service.IClienteService;
import ar.edu.unju.edm.service.IVentaService;
import ar.edu.unju.edm.service.ProductoService;

@Controller 
public class VentaController {
	

	@Autowired
	@Qualifier("implementacionMYSQL")
	ProductoService iProductoService;
	
	@Autowired
	@Qualifier("implementacionmysql")	
	IClienteService clienteService;
	@Autowired
	Producto productoSeleccionado;
	
	@Autowired
	IVentaService iVentas;
	@GetMapping("/producto/ventas")
	public String cargarVentas(Model model) {
		
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("ventas");
	}
	
	@GetMapping("/producto/vender/{codProducto}")	
	public String realizarVenta(Model model, @PathVariable(name="codProducto") int codigo) throws Exception {
		Venta venta = new Venta();		
		try {		
			productoSeleccionado = iProductoService.encontrarUnProducto(codigo);			
			venta = iVentas.crearVenta();		
			venta.setProducto(productoSeleccionado);
			model.addAttribute("venta",venta);
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "modal-venta";
	}
	
	@PostMapping("/producto/vender")
	public String guardarNuevoProducto(@ModelAttribute("venta") Venta unaVenta, Model model){
		
		
		System.out.println(unaVenta.getIdVenta());
		iVentas.guardarVenta(unaVenta);
		return("redirect:/producto/ventas");
	}



}
