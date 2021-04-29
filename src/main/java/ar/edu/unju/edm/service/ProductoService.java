package ar.edu.unju.edm.service;



import java.util.List;


import ar.edu.unju.edm.model.Producto;

//@Service
public interface ProductoService {
	
	
	public void guardarProducto(Producto unProducto);
	public Producto crearProducto();
	public void modificarProducto(Producto productoAModificar);
	public void eliminarProducto(Producto productoAEliminar);
	public Producto obtenerUnProducto(String nombreProducto);
	public List<Producto> obtenerTodosProductos();
	public Producto obtenerProductoNuevo();
	public Producto obtenerUltimoProducto();
}
