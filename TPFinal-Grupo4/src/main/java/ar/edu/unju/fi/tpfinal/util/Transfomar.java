package ar.edu.unju.fi.tpfinal.util;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import ar.edu.unju.fi.poo.tpfinal.dto.ClienteDTO;
import ar.edu.unju.fi.poo.tpfinal.dto.CuentaDTO;
import ar.edu.unju.fi.poo.tpfinal.entity.Cliente;
import ar.edu.unju.fi.poo.tpfinal.entity.Cuenta;

public class Transfomar {
	private static ModelMapper mapper = new ModelMapper();
	
	/**
	 * Retorna un cliente(entity).
	 * @param clienteDTO	-> ClienteDTO que se convierte en cliente (entity).
	 */
	public static Cliente clienteDTOaEntity(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		mapper.map(clienteDTO, cliente);
		if(clienteDTO.getAdherentesDTO()!=null)
			cliente.setAdherentes(clientesDTOaEntity(clienteDTO.getAdherentesDTO()));
		return cliente;
	}
	
	/**
	 * Retorna un clienteDTO 
	 * @param cliente	-> Cliente (entity) que se convierte en clienteDTO.
	 */
	public static ClienteDTO clienteToDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		mapper.map(cliente, clienteDTO);
		if(cliente.getAdherentes()!=null)
			clienteDTO.setAdherentesDTO(clientesToDTO(cliente.getAdherentes()));
		return clienteDTO;
	}
	
	/**
	 * Retorna una listaDTO con clientesDTO/adherentesDTO.
	 * @param clientes	-> Lista con clientes/adherentes(entity) que se convierte en una listaDTO.
	 */
	public static List<ClienteDTO> clientesToDTO(List<Cliente> clientes) {
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		for(Cliente c: clientes)
			clientesDTO.add(clienteToDTO(c));
		return clientesDTO;
	}
	
	/**
	 * Retorna una lista con clientes/Adherentes.
	 * @param clientesDTO	-> Lista con clientesDTO que se convierte en una lista (entity).
	 */
	public static List<Cliente> clientesDTOaEntity(List<ClienteDTO> clientesDTO){
		List<Cliente> clientes = new ArrayList<Cliente>();
		for(ClienteDTO a: clientesDTO)
			clientes.add(clienteDTOaEntity(a));
		return clientes;
	}
	
	/**
	 * Retorna una Cuenta (Entity)
	 * @param cuentaDTO	-> CuentaDTO que se convierte a Entity
	 */
	public static Cuenta cuentaDTOaEntity(CuentaDTO cuentaDTO) {
		Cuenta cuenta = new Cuenta();
		mapper.map(cuentaDTO, cuenta);
		cuenta.setCliente(Transfomar.clienteDTOaEntity(cuentaDTO.getCliente()));
		cuenta.setFechaIng(Util.stringToLocalDate(cuentaDTO.getFechaIng()));
		return cuenta;
	}
	
	/**
	 * Retorna una Cuenta (DTO)
	 * @param cuenta	-> Cuenta(entity) que se convierte en DTO.
	 */
	public static CuentaDTO cuentaToDTO(Cuenta cuenta) {
		CuentaDTO cuentaDTO = new CuentaDTO();
		mapper.map(cuenta, cuentaDTO);
		cuentaDTO.setFechaIng(Util.fechaToString(cuenta.getFechaIng()));
		return cuentaDTO;
	}
	
	/**
	 * Retorna una lista de cuentasDTO
	 * @param cuentas	-> Lista que se convertira en una lista DTO
	 */
	public static List<CuentaDTO> cuentasToDTO(List<Cuenta> cuentas) {
		List<CuentaDTO> cuentasDTO = new ArrayList<CuentaDTO>();
		for(Cuenta c: cuentas)
			cuentasDTO.add(cuentaToDTO(c));
		return cuentasDTO;
	}
}
