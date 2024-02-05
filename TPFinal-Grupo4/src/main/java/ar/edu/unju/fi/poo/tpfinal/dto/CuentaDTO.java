package ar.edu.unju.fi.poo.tpfinal.dto;
import java.io.Serializable;

public class CuentaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long nroCuenta;
	private ClienteDTO cliente;
	private String fechaIng;
	private Double saldoActual;
	private String estado;
	private Double limiteExtraccion;
	
//	Getters y Setters
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
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
	public String getFechaIng() {
		return fechaIng;
	}
	public void setFechaIng(String fechaIng) {
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
}