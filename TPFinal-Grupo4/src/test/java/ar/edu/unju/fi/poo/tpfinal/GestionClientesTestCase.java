package ar.edu.unju.fi.poo.tpfinal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ar.edu.unju.fi.poo.tpfinal.dto.ClienteDTO;
import ar.edu.unju.fi.poo.tpfinal.exception.ModelException;
import ar.edu.unju.fi.poo.tpfinal.serviceImp.ClienteServiceImp;

@SpringBootTest
public class GestionClientesTestCase {
private static final Log logger = (Log) LogFactory.getLog(GestionClientesTestCase.class);
	
	@Autowired
	ClienteServiceImp clienteService;
	
	private ClienteDTO cliente1;
	private ClienteDTO cliente2;
	private ClienteDTO cliente3;
	private ClienteDTO cliente4;
	private ClienteDTO adherente1;
	private ClienteDTO adherente2;
	
	@BeforeEach
	void setUp() {
		logger.info("Inicializando varianles ...");

		cliente1 = new ClienteDTO();
		cliente2 = new ClienteDTO();
		cliente3 = new ClienteDTO();
		cliente4 = new ClienteDTO();
		adherente1 = new ClienteDTO();
		adherente2 = new ClienteDTO();
		
		cliente1.setDni("14872231");
		cliente1.setNombre("Pedro");
		cliente1.setCorreo("pedro@correo.com");
		cliente1.setDomicilio("Alvear 123");
		cliente1.setEstado("INHABILITADO");
		cliente1.setAdherentesDTO(new ArrayList<ClienteDTO>());
		
		cliente2.setDni("13289345");
		cliente2.setNombre("Mariano");
		cliente2.setCorreo("marian@correo.com");
		cliente2.setDomicilio("Torres 1822");
		cliente2.setEstado("INHABILITADO");
		cliente2.setAdherentesDTO(new ArrayList<ClienteDTO>());
		
		cliente3.setDni("14872231");
		cliente3.setNombre("Juan");
		cliente3.setCorreo("juan@correo.com");
		cliente3.setDomicilio("Flores 7822");
		cliente3.setEstado("INHABILITADO");
		cliente3.setAdherentesDTO(new ArrayList<ClienteDTO>());
		
		cliente4.setDni("34092231");
		cliente4.setNombre("Mariana");
		cliente4.setCorreo("marian@correo.com");
		cliente4.setDomicilio("España 928");
		cliente4.setEstado("INHABILITADO");
		cliente4.setAdherentesDTO(new ArrayList<ClienteDTO>());
		
		adherente1.setDni("87623123");
		adherente1.setNombre("Lucas");
		adherente1.setCorreo("lucas@correo.com");
		adherente1.setDomicilio("Av Cordoba 2981");
		adherente1.setEstado("INHABILITADO");
		adherente1.setAdherentesDTO(null);
		
		adherente2.setDni("65128762");
		adherente2.setNombre("Laura");
		adherente2.setCorreo("laura@correo.com");
		adherente2.setDomicilio("Avellaneda 2387");
		adherente2.setEstado("INHABILITADO");
		adherente2.setAdherentesDTO(null);
		
	}
	
	@AfterEach
	void tearDown() {
		logger.info("Limpiado variables");
		
		cliente1 = null;
		cliente2 = null;
		cliente3 = null;
		cliente4 = null;
		adherente1 = null;
		adherente2 = null;
	}
	
	/**Testea si el cliente ya esta registrado.*/
	@Test
	@Disabled
	void registroCliente() throws ModelException {
		logger.info("Registrando clientes...");
		try {
			clienteService.altaCliente(cliente1);
			clienteService.altaCliente(cliente2);
			clienteService.altaCliente(cliente1);
		}catch(ModelException m) {
			logger.info(m.toString());
		}finally {
			assertTrue(clienteService.contarClientes()==2);
		}
	}
	
	/**Testea si un cliente que se quiere dar de alta tiene un dni ya registrado.*/
	@Test
	@Disabled
	void dniRepetido() throws ModelException {
		logger.info("Registrando clientes...");
		try {
			clienteService.altaCliente(cliente1);
			clienteService.altaCliente(cliente2);
			clienteService.altaCliente(cliente3); //Tiene el mismo dni que cliente1
		}catch(ModelException m) {
			logger.info(m.toString());
		}finally {
			assertTrue(clienteService.contarClientes()==2);
		}
	}
	
	/**Testea si un cliente que se quiere dar de alta tiene un correo ya registrado.*/
	@Test
	@Disabled
	void correoRepetido() throws ModelException {
		logger.info("Registrando clientes...");
		try {
			clienteService.altaCliente(cliente1);
			clienteService.altaCliente(cliente2);
			clienteService.altaCliente(cliente4); //Tiene el mismo correo que cliente2
		}catch(ModelException m) {
			logger.info(m.toString());
		}finally {
			assertTrue(clienteService.contarClientes()==2);
		}
	}
	
	/**Testea la baja de un cliente.*/
	@Test
	@Disabled
	void eliminarCliente() {
		logger.info("Registrando Clientes...");
		clienteService.altaCliente(cliente1);
		clienteService.altaCliente(cliente2);
		assertEquals(2,clienteService.contarClientes());
		
		
		logger.info("Eliminando cliente1");
		clienteService.bajaCliente(cliente1);
		assertEquals(1,clienteService.contarClientes());
		
		try {
		logger.info("Eliminando cliente3");
		clienteService.bajaCliente(cliente3);
		}catch(ModelException m) {
			logger.info(m.toString());
		}finally {
			clienteService.bajaCliente(cliente2);
			assertTrue(clienteService.contarClientes()==0);
		}
	}
	
	/**Testea si dos clientes diferentes puden tener el mismo adherente.*/
	@Test
	@Disabled
	void asignarAdherente() throws ModelException {
		logger.info("Registrando clientes...");
		clienteService.altaCliente(cliente1);
		clienteService.altaCliente(cliente2);
		
		logger.info("Registrando adherente...");
		clienteService.altaCliente(adherente1);
		
		try {
			clienteService.agregarAdherente(cliente1,adherente1);
			clienteService.agregarAdherente(cliente2,adherente1);
		}catch(ModelException m) {
			logger.info(m.toString());
		}finally {
			
			logger.info("Registrando adherente...");
			clienteService.altaCliente(adherente2);
			clienteService.agregarAdherente(cliente2,adherente2);
			
			assertTrue(clienteService.contarClientes()==4);
		}
	}
	
	/**Testea la baja de un cliente con adherentes.*/
	@Test
	@Disabled
	void eliminarClienteConAdherentes() {
		logger.info("Registrando clientes...");
		clienteService.altaCliente(cliente1);
		clienteService.altaCliente(cliente4);
		
		logger.info("Registrando adherentes...");
		clienteService.altaCliente(adherente1);
		clienteService.altaCliente(adherente2);
		
		logger.info("Asignando adherentes..");
		clienteService.agregarAdherente(cliente4, adherente1);
		clienteService.agregarAdherente(cliente4, adherente2);

		assertTrue(clienteService.contarClientes()==4);
		
		logger.info("Eliminando cliente: "+cliente4.getNombre());
		clienteService.bajaCliente(cliente4);
		assertTrue(clienteService.contarClientes()==1);
	}
	
	/*Testea la baja de un cliente con adherentes*/
	@Test
//	@Disabled
	void eliminarAdherente() throws ModelException{
		logger.info("Registrando cliente4..");
		clienteService.altaCliente(cliente4);
		
		logger.info("Registrando adherente1..");
		clienteService.altaCliente(adherente1);
		
		assertTrue(clienteService.contarClientes()==2);
		
		try {
			logger.info("Eliminando adherente");
			clienteService.eliminarAdherente(cliente4,adherente1);
		}catch(ModelException m) {
			logger.info(m.toString());
			assertTrue(clienteService.contarClientes()==2);
		}finally {	
			logger.info("Asignando adherente..");
			clienteService.agregarAdherente(cliente4, adherente1);

			clienteService.eliminarAdherente(cliente4,adherente1);
			
			assertTrue(clienteService.contarClientes()==1);
		}
	}
}
