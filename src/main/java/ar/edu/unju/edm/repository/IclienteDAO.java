package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sun.el.stream.Optional;

import ar.edu.unju.edm.model.Cliente;

public class IclienteDAO {
	@Repository
	public interface IClienteDAO extends CrudRepository<Cliente, Integer>{

		@Query("from Cliente c order by c.nroDocumento")
		public List<Cliente> obtenerClientes();
		
		public IclienteDAO findByNroDocumento(int dni);
		
		
	}
}
