package ar.edu.unju.fi.poo.tpfinal.service;
import ar.edu.unju.fi.poo.tpfinal.dto.ClienteDTO;

public interface ClienteService {
	
	public Long contarClientes();
	public void altaCliente(ClienteDTO clienteDTO);
	public void bajaCliente(ClienteDTO clienteDTO);
	public void agregarAdherente(ClienteDTO cliente, ClienteDTO adherente);
	public void eliminarAdherente(ClienteDTO cliente,ClienteDTO clienteDTO);
	
}