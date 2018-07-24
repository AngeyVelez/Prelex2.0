package com.prelex20.prelex20.entidades;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import java.sql.Date;

/**Esta clase contiene todos los atributos y metodos para representar la tabla 'estudiante' de la base de datos.
 * @author AngeyVelez
 * @version 1.0
 */
@Entity
@Table(name = "estudiante")
public class Estudiante implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String numeroDocumento;	
	private String nombre1;	
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private Date fechaNacimiento;
	private String telefono;
	private String email;
	private String estado_actual;
	private Date fechaIngreso;
	private String eps;

	private Set<Matricula> matriculas = new HashSet<Matricula>();

	public Estudiante() {
		// TODO Auto-generated constructor stub
	}
	
	public Estudiante(String numeroDocumento, String nombre1, String apellido1, String estado_actual, Date fecha_ingreso){
		this.numeroDocumento = numeroDocumento;
		this.nombre1 = nombre1;
		this.apellido1 = apellido1;
		this.estado_actual = estado_actual;
		this.fechaIngreso = fecha_ingreso;
	}


	public Estudiante(String numeroDocumento, String nombre1,String nombre2, String apellido1, String apellido2,
					  Date fechaNacimiento, String telefono, String email,String estado_actual, Date fecha_ingreso, String eps){
		this.numeroDocumento = numeroDocumento;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.email = email;
		this.estado_actual = estado_actual;
		this.fechaIngreso = fecha_ingreso;
		this.eps = eps;
	}

	@Column(name = "apellido1", nullable = false)
	public String getApellido1() {
		return apellido1;
	}

	@Column(name = "apellido2")
	public String getApellido2() {
		return apellido2;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	
	@Column(name = "eps")
	public String getEps() {
		return eps;
	}
	
	@Column(name = "estado_actual", nullable = false)
	public String getEstado_actual() {
		return estado_actual;
	}
	
	@Column(name = "fecha_ingreso", nullable = false)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	@Column(name = "fecha_nacimiento")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	@Column(name = "nombre1", nullable = false)
	public String getNombre1() {
		return nombre1;
	}

	@Column(name = "nombre2")
	public String getNombre2() {
		return nombre2;
	}

	@Id
	@Column(name = "numero_documento", unique = true, nullable = false)	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	
	@Column(name = "telefono")
	public String getTelefono() {
		return telefono;
	}

	@OneToMany(mappedBy = "estudiante")
	public Set<Matricula> getMatriculas(){
		return this.matriculas;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setEps(String eps) {
		this.eps = eps;
	}
	
	public void setEstado_actual(String estado_actual) {
		this.estado_actual = estado_actual;
	}
	
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public void setMatriculas(Set<Matricula> matriculas){
		this.matriculas = matriculas;
	}

}
