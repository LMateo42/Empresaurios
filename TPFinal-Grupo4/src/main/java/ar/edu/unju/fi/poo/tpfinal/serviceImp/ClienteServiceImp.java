package ar.edu.unju.fi.poo.tpfinal.serviceImp;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.unju.fi.poo.tpfinal.dto.ClienteDTO;
import ar.edu.unju.fi.poo.tpfinal.entity.Cliente;
import ar.edu.unju.fi.poo.tpfinal.repository.ClienteRepository;
import ar.edu.unju.fi.poo.tpfinal.service.ClienteService;
import ar.edu.unju.fi.tpfinal.util.Transfomar;
import ar.edu.unju.fi.tpfinal.util.Validar;

@Service
public class ClienteServiceImp implements ClienteService{
	
	@Autowired
	ClienteRepository clienteRepository;
	
	
	/**Retorna la cantidad de clientes registrados en la base de datos.*/
	@Override
	public Long contarClientes() {
		return clienteRepository.count();
	}
	
	/**Procesa un cliente para guardardarlo luego guardarlo en la base de datos.*/
	@Override
	public void altaCliente(ClienteDTO clienteDTO) {
		Validar.altaCliente(clienteDTO,listaClientes());
		guardarCliente(clienteDTO); 
	}
	
	/**Procesa a un cliente antes de eliminarlo de la base de datos.*/
	@Override
	public void bajaCliente(ClienteDTO clienteDTO) {
		clienteDTO = buscarPorDni(clienteDTO);
		eliminarCliente(clienteDTO);
	}
	
	/**Método que agrega un adherente asociandolo a un cliente*/
	@Override
	public void agregarAdherente(ClienteDTO clienteDTO, ClienteDTO adherenteDTO) { 
		ClienteDTO clienteBuscadoDTO = buscarPorDni(clienteDTO);
		ClienteDTO adherenteBuscadoDTO = buscarPorDni(adherenteDTO);
		Validar.tieneTitular(adherenteBuscadoDTO,listaClientes());
		
		List<ClienteDTO> adherentesDTO = clienteBuscadoDTO.getAdherentesDTO();
		adherentesDTO.add(adherenteBuscadoDTO);	
		clienteBuscadoDTO.setAdherentesDTO(adherentesDTO);
		
		guardarCliente(clienteBuscadoDTO);
	}

	/**Metodo que elimina un adherente asociado a un cliente*/
	@Override
	public void eliminarAdherente(ClienteDTO clienteDTO,ClienteDTO adherenteDTO) {
		ClienteDTO clienteBuscadoDTO = buscarPorDni(clienteDTO);
		ClienteDTO adherenteBuscadoDTO = buscarPorDni(adherenteDTO);
		
		List<ClienteDTO> adherentesDTO = clienteBuscadoDTO.getAdherentesDTO();
		
		int posicion = buscarPosicion(clienteBuscadoDTO.getAdherentesDTO(),adherenteBuscadoDTO);
		adherentesDTO.remove(posicion);
		clienteBuscadoDTO.setAdherentesDTO(adherentesDTO);
		
		guardarCliente(clienteBuscadoDTO);
	}
	
//	METODOS PRIVADOS
	
	/**Elimina un cliente de la base de datos.*/
	private void eliminarCliente(ClienteDTO clienteDTO) {
		clienteRepository.delete(Transfomar.clienteDTOaEntity(clienteDTO));
	}
	
	/**Guarda un cliente en la base de datos.*/
	private void guardarCliente(ClienteDTO clienteDTO) {
		clienteRepository.save(Transfomar.clienteDTOaEntity(clienteDTO));
	}
	
	/**Retorna una lista con todos los clientes registrados en la base de datos.*/
	private List<ClienteDTO> listaClientes() {
		return Transfomar.clientesToDTO(clienteRepository.findAll());
	}
	
	/**Retorna un cliente buscado por su dni.*/
	private ClienteDTO buscarPorDni(ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.findByDni(clienteDTO.getDni());
		
		if(cliente==null)
			Validar.clienteNoExiste(clienteDTO);
		
		return Transfomar.clienteToDTO(cliente);
	}
	
	/**Retorna la posición del adherente.*/
	private int buscarPosicion(List<ClienteDTO> adherentesDTO,ClienteDTO adherenteDTO) {
		int index =0;
		for (ClienteDTO adherente : adherentesDTO) {
			if(adherente.getDni().equals(adherenteDTO.getDni())) {
				return index;
			}
			index++;
		}
		Validar.noAsignado(adherenteDTO);
		return -1;
	}
}
