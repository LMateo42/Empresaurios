package ar.edu.unju.fi.poo.tpfinal.service;
import java.util.List;
import ar.edu.unju.fi.poo.tpfinal.dto.ClienteDTO;
import ar.edu.unju.fi.poo.tpfinal.dto.CuentaDTO;

public interface CuentaService {
	
	public Long contarCuentas();
	public void guardarCuenta(CuentaDTO cuentaDTO);
	public void crearCuentaPara(ClienteDTO clienteDTO);
	public void eliminarCuentaDel(ClienteDTO clienteDTO);
	public List<CuentaDTO> listaCuentas();
}
