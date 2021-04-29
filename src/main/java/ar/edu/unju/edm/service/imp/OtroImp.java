package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@Service
@Qualifier("otroImp")
public class OtroImp implements IClienteService{

	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		//puedo implementar guardar en una BD y no en un listado
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return null;
	}

}