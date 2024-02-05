package ar.edu.unju.fi.poo.tpfinal.serviceImp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.tpfinal.dto.ClienteDTO;
import ar.edu.unju.fi.poo.tpfinal.dto.CuentaDTO;
import ar.edu.unju.fi.poo.tpfinal.entity.Cuenta;
import ar.edu.unju.fi.poo.tpfinal.repository.CuentaRepository;
import ar.edu.unju.fi.poo.tpfinal.service.CuentaService;
import ar.edu.unju.fi.tpfinal.util.Transfomar;
import ar.edu.unju.fi.tpfinal.util.Validar;

@Service
public class CuentaServiceImp implements CuentaService {
	
	@Autowired
	CuentaRepository cuentaRepository;
	
	/**
	 * Devuelve la cantidad cuentas tiene la base de datos.
	 */
	@Override
	public Long contarCuentas() {
		return cuentaRepository.count();
	}
	
	/**
	 * Guarda una cuenta en la base de datos.
	 * @param cuentaDTO	-> Cuenta guardada en la base de datos.
	 */
	@Override
	public void guardarCuenta(CuentaDTO cuentaDTO) {
		Cuenta cuenta = Transfomar.cuentaDTOaEntity(cuentaDTO);
		cuentaRepository.save(cuenta);
		cuentaDTO.setId(cuenta.getId());
		
		ClienteDTO cliente = Transfomar.clienteToDTO(cuenta.getCliente());
		cuentaDTO.setCliente(cliente);
	}
	
	/**
	 * Crea una cuenta para un cliente.
	 */
	@Override
	public void crearCuentaPara(ClienteDTO clienteDTO){
		Validar.tieneCuenta(clienteDTO);
		
		CuentaDTO cuentaNuevaDTO = Transfomar.cuentaToDTO(new Cuenta("HABILITADA"));
		
		clienteDTO.setEstado("HABILITADO");
		cuentaNuevaDTO.setCliente(clienteDTO);
		guardarCuenta(cuentaNuevaDTO);
		
		clienteDTO.setId(cuentaNuevaDTO.getCliente().getId());
	}
	/**
	 * Elimina la cuenta de un cliente
	 */
	@Override
	public void eliminarCuentaDel(ClienteDTO clienteDTO) {
		CuentaDTO cuentaDTO = buscarCuenta(clienteDTO);
		cuentaRepository.delete(Transfomar.cuentaDTOaEntity(cuentaDTO));
		clienteDTO.setEstado("INHABILITADO");
	}
	/**
	 * Retorna una lista con las cuentas registradas en la base de datos
	 */
	@Override
	public List<CuentaDTO> listaCuentas() {
		return Transfomar.cuentasToDTO(cuentaRepository.findAll());
	}
	
	/**
	 * Retorna la cuenta de un cliente.
	 
	 */
	private CuentaDTO buscarCuenta(ClienteDTO clienteDTO) {
		for(CuentaDTO c: listaCuentas())
			if(c.getCliente().getId()==clienteDTO.getId())
				return c;
		return null;
	}
}
