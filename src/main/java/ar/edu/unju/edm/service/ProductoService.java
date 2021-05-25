package ar.edu.unju.edm.service;



import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;

@Service
public interface ProductoService {
	
	
	public void guardarProducto(Producto unProducto);
	public Producto crearProducto();
	//public void modificarProducto(Producto productoAModificar);
	public void eliminarProducto(int id) throws Exception;
	public Producto obtenerUnProducto(String nombreProducto);
	public List<Producto> obtenerTodosProductos();
	public Producto obtenerProductoNuevo();
	public Producto obtenerUltimoProducto();
	public Producto encontrarUnProducto(int cod) throws Exception;
	public void modificarProducto(Producto productoModificado) throws Exception;
}
