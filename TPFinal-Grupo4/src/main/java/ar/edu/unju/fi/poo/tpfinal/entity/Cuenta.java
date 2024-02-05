package ar.edu.unju.fi.poo.tpfinal.entity;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cuentas")
public class Cuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Num_Cuenta")
	private Long nroCuenta;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Cliente")
	private Cliente cliente;

	@Column(name = "Fecha_Ingreso")
	private LocalDate fechaIng;
	
	@Column(name = "Saldo_Actual")
	private Double saldoActual;
	
	@Column(name = "Estado")
	private String estado;
	
	@Column(name = "Limite_Extraccion")
	private Double limiteExtraccion;
	
	public static Long nextInt=99L;
	private static final Double LIMITE=15000d;
	private static final Double SALDO_INICIAL=0d;
	
	public Cuenta() {}
	
	public Cuenta(String estado) {
		nextInt++;
		this.nroCuenta = nextInt;
		this.fechaIng = LocalDate.now();
		this.saldoActual = SALDO_INICIAL;
		this.estado = estado;
		this.limiteExtraccion = LIMITE;
	}
	
	public Cuenta(Long nroCuenta) {
		this.nroCuenta = nroCuenta;
		this.saldoActual = 0d;
		this.estado = "HABILITADO";
		this.limiteExtraccion = LIMITE;
	}

//	Getter y Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(Long nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LocalDate getFechaIng() {
		return fechaIng;
	}
	public void setFechaIng(LocalDate fechaIng) {
		this.fechaIng = fechaIng;
	}
	public Double getSaldoActual() {
		return saldoActual;
	}
	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getLimiteExtraccion() {
		return limiteExtraccion;
	}
	public void setLimiteExtraccion(Double limiteExtraccion) {
		this.limiteExtraccion = limiteExtraccion;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", nroCuenta=" + nroCuenta + ", cliente=" + cliente + ", fechaIng=" + fechaIng
				+ ", saldoActual=" + saldoActual + ", estado=" + estado + ", limiteExtraccion=" + limiteExtraccion
				+ "]";
	}
}
