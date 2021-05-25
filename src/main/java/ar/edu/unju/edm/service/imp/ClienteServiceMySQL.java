
package ar.edu.unju.edm.service.imp;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.repository.IClienteDAO;
import ar.edu.unju.edm.service.IClienteService;

@Service
@Qualifier("implementacionmysql")
public class ClienteServiceMySQL implements IClienteService{
	@Autowired
	Cliente unCliente;
	
	@Autowired
	IClienteDAO clienteDAO;

	@Override
	public void guardarCliente(Cliente unCliente) {
		LocalDate fechaNac=unCliente.getFechaNacimiento();
		/*Fecha actual*/
		LocalDate fechaHoy=LocalDate.now();
		Period periodo = Period.between(fechaNac, fechaHoy);
		unCliente.setEdad(periodo.getYears());
		
		
		LocalDate ultimaCompra = unCliente.getFechaUltimaCompra();
        Period periodo1 = Period.between(ultimaCompra,fechaHoy );
        unCliente.setTiempoUltCompra("DD-MM-YY;" + periodo1.getDays() +"-"+ periodo1.getMonths() +"-"+ periodo1.getYears());
		       
        

        /*tiempo que falta hasta el cumpleaños*/
        LocalDate nextBDay = fechaNac.withYear(fechaHoy.getYear());

        /*Si el cumpleaños ya ocurrió este año, agrega 1 año*/
        if (nextBDay.isBefore(fechaHoy) || nextBDay.isEqual(fechaHoy)) {
            nextBDay = nextBDay.plusYears(1);
        }
        
        Period p = Period.between(fechaHoy, nextBDay);
        
        unCliente.setDatosAdicionales("Restan " + p.getDays() + "dias"+ p.getMonths() + " meses, y " + p.getYears() +"años");
               
        
		// TODO Auto-generated method stub
		//puedo implementar guardar en una BD y no en un listado
		clienteDAO.save(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
		//return clienteDAO.obtenerClientes();
	}

	@Override
	public Cliente encontrarUnCliente(int dni) throws Exception {
	//public Cliente encontrarUnCliente(int dni) {
		// TODO Auto-generated method stub
		
		//return clienteDAO.findByNroDocumento();
		return clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("El cliente NO existe"));
	}

	@Override
	public void modificarCliente(Cliente unClienteModificado) throws Exception {
		//Observen aquí como resolver la modificación
		//se busca el Cliente que se quiere modificar en la BD (por algún campo que no se permita modificar)
		//Vean que he utilizado el DNI pero sin embargo en mi app si puedo cambiar el DNI, entonces la sentencia siguiente no sería correcta
		//tal vez sería mejor buscar por ID, que es un campo que no se modifica (findById)
		//sin embargo aquí lo hice para que vean los posibles errores		
		Cliente clienteAModificar = clienteDAO.findByNroDocumento(unClienteModificado.getNroDocumento()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		
		//vean que si utilizan directamente save, lo que se hace es AGREGAR otro cliente a la BD, y lo que nosotros queremos hacer es SUSTITUIR
		// con lo que que clienteDAO.save(unClienteModificado); lo voy a dejar para el final del método
		
		//voy a realizar el intercambio entre el cliente que viene y el cliente que ya está en la BD
		//y guardar el cliente que está en la BD, pero lo voy a hacer en otro método
		cambiarCliente(unClienteModificado, clienteAModificar);
		
		//vuelve el cliente en la BD ya modificado y se guarda
		clienteDAO.save(clienteAModificar);
	}
	
	private void cambiarCliente(Cliente desde, Cliente hacia) {
		//observen que vamos a pasar todos los atributos del cliente que viene, hacia el cliente que ya está en la BD
		hacia.setNroDocumento(desde.getNroDocumento());
		hacia.setFechaUltimaCompra(desde.getFechaUltimaCompra());
		hacia.setnYa(desde.getnYa());
		hacia.setPassword(desde.getPassword());
	
		hacia.setTipoDocumento(desde.getTipoDocumento());
		hacia.setFechaNacimiento(desde.getFechaNacimiento());
		hacia.setCodigoAreaTelefono(desde.getCodigoAreaTelefono());
		hacia.setNumTelefono(desde.getNumTelefono());
		hacia.setEmail(desde.getEmail());
	
		//observen que NO se ha cambiado el id, ya que ese atributo no debería permitirse cambiar
	}

	@Override
	public void eliminarCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		Cliente clienteEliminar = clienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}
}
