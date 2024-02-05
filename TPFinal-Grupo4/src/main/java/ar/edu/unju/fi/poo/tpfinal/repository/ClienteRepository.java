package ar.edu.unju.fi.poo.tpfinal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.poo.tpfinal.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public Cliente findByDni(String dni);
}
