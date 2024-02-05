package ar.edu.unju.fi.tpfinal.util;
import java.util.List;
import ar.edu.unju.fi.poo.tpfinal.dto.ClienteDTO;
import ar.edu.unju.fi.poo.tpfinal.exception.ModelException;

public class Validar {
	
	/**Valida que el cliente no este registrado, no tenga el dni y el correo repetido.*/
	public static void altaCliente(ClienteDTO clienteDTO,List<ClienteDTO> listaClientesDTO) throws ModelException{
		if(clienteDTO.getId()!=null) 
			throw new ModelException("El cliente: " + clienteDTO.getNombre() + " ya esta registrado.");
		
		for(ClienteDTO c: listaClientesDTO)
			if(c.getDni().equals(clienteDTO.getDni()))
				throw new ModelException("El dni: " +clienteDTO.getDni()+ " ya esta registrado");
		
		for(ClienteDTO c: listaClientesDTO)
			if(c.getCorreo().equals(clienteDTO.getCorreo()))
				throw new ModelException("El correo: " +clienteDTO.getCorreo()+ " ya esta registrado");
	}
	
	/**Valida que el cliente este registrado antes de darlo de baja.*/
	public static void clienteNoExiste(ClienteDTO clienteDTO) throws ModelException{
		throw new ModelException("El cliente: "+clienteDTO.getNombre() +" no se encuentra registrado.");
	}
	
	/**Valida que el adherente pertenezca al titular.*/
	public static void noAsignado(ClienteDTO clienteDTO) throws ModelException{
		throw new ModelException("El cliente: "+clienteDTO.getNombre() +" no le pertenece a titular elegido.");
	}
	
	/**Valida si el adherente tiene titular.
	 * @param listaClientesDTO -> Lista con todos los clientes registrados.
	 */
	public static void tieneTitular(ClienteDTO adherenteDTO,List<ClienteDTO> listaClientesDTO) throws ModelException {
		for(ClienteDTO cliente: listaClientesDTO)
			if(cliente.getAdherentesDTO()!=null)
				for(ClienteDTO adherente: cliente.getAdherentesDTO())
					if(adherente.getId() == adherenteDTO.getId())
						throw new ModelException("El adherente: "+adherente.getNombre()+" ya tiene un titular.");
	}
	
	/**Valida si un cliente tiene cuenta.*/
	public static void tieneCuenta(ClienteDTO clienteDTO) throws ModelException {
		if(clienteDTO.getEstado().equals("HABILITADO"))
			throw new ModelException("El cliente "+clienteDTO.getNombre()+" ya tiene una cuenta");
	}
}
