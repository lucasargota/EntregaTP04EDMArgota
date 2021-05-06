package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.ProductoService;


import ar.edu.unju.edm.util.ListadoProductos;

@Service
@Qualifier("unaImp")
public class ProductoServiceImp implements ProductoService{
	
	

	private List<Producto> listadoProductos = ListadoProductos.productos;
	
	
	//private static final Log LUCAS = LogFactory.getLog(ProductoServiceImp.class);
	
	@Autowired
	Producto unProducto;
	
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		
	//	System.out.println(unProducto.getNombre());
		listadoProductos.add(unProducto);
		
//		
	//	System.out.println(listadoProductos.size());
		
		//LUCAS.info("METHOD: ingresando a Guardar Producto");
		//LUCAS.info("RESULT: guardado " + listadoProductos.get(listadoProductos.size()-1).getNombre());
	}
	
	
	//@Override
	//public Cliente crearProducto() {
	//	// TODO Auto-generated method stub
	//	return unProducto;
	//}

	//@Override
	//public void modificarProducto(Producto productoAModificar) {
	//	// TODO Auto-generated method stub
		
//	}
	

	////@Override
//	public void eliminarProducto(Producto productoAEliminar) {
	//	// TODO Auto-generated method stub
		
//	}

	@Override
	public Producto obtenerUnProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return listadoProductos;
	}

	@Override
	public Producto obtenerProductoNuevo() {
		return unProducto;
	}


	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Producto encontrarUnProducto(int cod) {
		
		

		for (int i = 0; i < listadoProductos.size(); i++){
		    if (listadoProductos.get(i).getCodProducto() == cod) {
		    	unProducto = listadoProductos.get(i);
		    }
		}
		// TODO Auto-generated method stub
		return unProducto;
	}
	


	@Override
	public void eliminarProducto(Producto productoAEliminar) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void modificarProducto(Producto productoModificado) {
		

		for (int i = 0; i < listadoProductos.size(); i++){
		    if (listadoProductos.get(i).getCodProducto() == productoModificado.getCodProducto()) {
		    	listadoProductos.set(i, productoModificado);
		    
		    }
		// TODO Auto-generated method stub
		
	}
	}

	

}
