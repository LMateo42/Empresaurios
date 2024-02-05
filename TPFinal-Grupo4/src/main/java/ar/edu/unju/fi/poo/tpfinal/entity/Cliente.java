package ar.edu.unju.fi.poo.tpfinal.entity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Clientes")
public class Cliente {

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DNI")
	private String dni;
	
	@Column(name="Nombre")
	private String nombre;
	
	@Column(name="Correo")
	private String correo;
	
	@Column(name="Domicilio")
	private String domicilio;
	
	@Column(name = "Estado")
	private String estado;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	@JoinTable(name = "Adherentes",
		joinColumns = @JoinColumn(name = "ID_Cliente"), 
		inverseJoinColumns = @JoinColumn(name = "ID_Adherente")
	)
	private List<Cliente> adherentes;
	
	public Cliente() {
	}

	//Getters y Setters
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
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<Cliente> getAdherentes() {
		return adherentes;
	}
	public void setAdherentes(List<Cliente> adherentes) {
		this.adherentes = adherentes;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", correo=" + correo + ", domicilio="
				+ domicilio + ", estado=" + estado + ", adherentes=" + adherentes + "]";
	}
}
