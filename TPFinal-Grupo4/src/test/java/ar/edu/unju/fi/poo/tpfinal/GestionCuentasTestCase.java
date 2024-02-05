package ar.edu.unju.fi.poo.tpfinal;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import ar.edu.unju.fi.poo.tpfinal.service.ClienteService;
import ar.edu.unju.fi.poo.tpfinal.service.CuentaService;

@SpringBootTest
public class GestionCuentasTestCase {
	private static final Log logger = (Log) LogFactory.getLog(GestionClientesTestCase.class);
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private CuentaService cuentaService;
	
	static ClienteDTO cliente1;
	static ClienteDTO cliente2;
	static ClienteDTO adherente1;
	
	@BeforeEach
	void setUp() {
		logger.info("Preparando setUp...");
		cliente1 = new ClienteDTO();
		cliente2 = new ClienteDTO();
		adherente1 = new ClienteDTO();
		
		cliente1.setDni("78201992");
		cliente1.setNombre("Florencia");
		cliente1.setCorreo("flor@correo.com");
		cliente1.setDomicilio("Lavalle 2329");
		cliente1.setEstado("INHABILITADO");
		cliente1.setAdherentesDTO(new ArrayList<ClienteDTO>());
		
		cliente2.setDni("63902123");
		cliente2.setNombre("Gabriel");
		cliente2.setCorreo("gabi@correo.com");
		cliente2.setDomicilio("Av. Roca 379");
		cliente2.setEstado("INHABILITADO");
		cliente2.setAdherentesDTO(new ArrayList<ClienteDTO>());
		
		adherente1.setDni("78291675");
		adherente1.setNombre("Camila");
		adherente1.setCorreo("cami@correo.com");
		adherente1.setDomicilio("Av Córdoba 762");
		adherente1.setEstado("INHABILITADO");
		adherente1.setAdherentesDTO(new ArrayList<ClienteDTO>());
	}
	
	@AfterEach
	void tearDown() {
		logger.info("Limpiando variables...");
		cliente1 = null;
		cliente2 = null;
		adherente1 = null;
	}
	
	@Test
//	@Disabled
	void crearCuentas() throws ModelException{
		try {
			cuentaService.crearCuentaPara(cliente1);
			cuentaService.crearCuentaPara(cliente2);
			cuentaService.crearCuentaPara(cliente1);
		}catch(ModelException m) {
			logger.info(m.toString());
		}finally {
			assertEquals(cuentaService.contarCuentas(),2);
			assertEquals(clienteService.contarClientes(),2);
		}
	}

	@Test
	@Disabled
	void eliminarCuenta(){
		cuentaService.crearCuentaPara(cliente1);
		cuentaService.crearCuentaPara(cliente2);
		assertEquals(cuentaService.contarCuentas(),2);
		
		cuentaService.eliminarCuentaDel(cliente2);
		
		assertEquals(cuentaService.contarCuentas(),1);
	}
}
