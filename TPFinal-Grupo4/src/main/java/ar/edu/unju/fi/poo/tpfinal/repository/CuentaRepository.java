package ar.edu.unju.fi.poo.tpfinal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unju.fi.poo.tpfinal.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
	public Cuenta findByNroCuenta(Long nroCuenta);
}
