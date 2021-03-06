package com.prelex20.prelex20.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "profesor")
public class Profesor implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroDocumento;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String telefono;
	private String direccion;
	private String eps;
	private String especialidad;
	private String email;
	
	private Set<Grupo> grupos = new HashSet<Grupo>();
	
	public Profesor() {
		// TODO Auto-generated constructor stub
	}
	
	public Profesor(String numeroDocumento, String nombre1, String nombre2, String apellido1, String apellido2, String telefono, String direccion, String especialidad, String email, String eps) {
		this.numeroDocumento = numeroDocumento;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.telefono = telefono;
		this.direccion = direccion;
		this.eps = eps;
		this.especialidad = especialidad;
		this.email = email;
	}
	
	@NotNull
	@Size(min = 3, message = "el apellido debe contener más de 3 caracteres")
	@Column(name = "apellido1", nullable = false)
	public String getApellido1() {
		return apellido1;
	}

	@Column(name = "apellido2")
	public String getApellido2() {
		return apellido2;
	}

	@Column(name = "direccion")
	public String getDireccion() {
		return direccion;
	}

	@Column(name = "eps")
	public String getEps() {
		return eps;
	}

	@Column(name = "especialidad")
	public String getEspecialidad() {
		return especialidad;
	}

	@NotNull
	@Size(min = 3, message = "el nombre debe contener más de 3 dígitos")
	@Column(name = "nombre1", nullable = false)
	public String getNombre1() {
		return nombre1;
	}
	
	@Column(name = "nombre2")
	public String getNombre2() {
		return nombre2;
	}

	@Id
	@NotNull
	@Size(min = 5, message = "el documento debe contener más de 5 dígitos")
	@Column(name = "numero_documento", unique = true, nullable = false)
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	@NotNull
	@Size(min = 7, message = "el telefono debe contener más de 7 dígitos")
	@Column(name = "telefono")
	public String getTelefono() {
		return telefono;
	}
	
	@NotNull
	@Email
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor")
	public Set<Grupo> getGrupos() {
		return grupos;
	}
	
	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setEps(String eps) {
		this.eps = eps;
	}
	
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String nombreCompleto() {
		String nombre = "";
		nombre = nombre+this.nombre1 +" ";
		if (!this.nombre2.isEmpty()) {
			nombre = nombre+this.nombre2+" ";
		}
		nombre = nombre+this.apellido1;
		if (!this.apellido2.isEmpty()) {
			nombre = nombre+" "+this.apellido2;
		}
		return nombre;
	}
}
