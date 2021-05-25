package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IProductoDAO;

import ar.edu.unju.edm.service.ProductoService;

@Service
@Qualifier("implementacionMYSQL")
public class ProductoServiceMySQL implements ProductoService{
	@Autowired
	Producto unProducto;
	
	@Autowired
	IProductoDAO productoDAO;

	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
		
	}

	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public void eliminarProducto(int id) throws Exception {
		Producto productoEliminar = productoDAO.findByCodProducto(id).orElseThrow(()->new Exception("El producto no fue encontrado"));
		productoDAO.delete(productoEliminar);
		
	}

	@Override
	public Producto obtenerUnProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto encontrarUnProducto(int cod) throws Exception {
		// TODO Auto-generated method stub
		return productoDAO.findByCodProducto(cod).orElseThrow(()->new Exception("El producto NO existe"));
	}

	@Override
	public void modificarProducto(Producto productoModificado) throws Exception {
		// TODO Auto-generated method stub
		Producto productoAModificar = productoDAO.findByCodProducto(productoModificado.getCodProducto()).orElseThrow(()->new Exception("El producto no fue encontrado"));
		cambiarProducto(productoModificado, productoAModificar);
		productoDAO.save(productoAModificar);
		
	}
	private void cambiarProducto(Producto desde, Producto hacia) {
		//observen que vamos a pasar todos los atributos del cliente que viene, hacia el cliente que ya está en la BD
		hacia.setCodProducto(desde.getCodProducto());
		hacia.setMarca(desde.getMarca());
		hacia.setNombre(desde.getNombre());
		hacia.setPrecio(desde.getPrecio());
		hacia.setStock(desde.getStock());
		//observen que NO se ha cambiado el id, ya que ese atributo no debería permitirse cambiar
	}
	

}