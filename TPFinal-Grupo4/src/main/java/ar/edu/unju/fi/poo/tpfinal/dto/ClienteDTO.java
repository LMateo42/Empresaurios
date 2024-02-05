package ar.edu.unju.fi.poo.tpfinal.dto;
import java.io.Serializable;
import java.util.List;

public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String dni;
	private String nombre;
	private String correo;
	private String domicilio;
	private String Estado;
	private List<ClienteDTO> adherentesDTO;
	
//	Getters y Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public List<ClienteDTO> getAdherentesDTO() {
		return adherentesDTO;
	}
	public void setAdherentesDTO(List<ClienteDTO> adherentesDTO) {
		this.adherentesDTO = adherentesDTO;
	}
	
}
