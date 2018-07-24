package com.prelex20.prelex20.entidades;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "grupo")
public class Grupo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Profesor profesor;
	private Nivel nivel;
	private String ciclo;
	private int numero;
	private String jornada;
	private Date fechaFin;
	private Date fechaInicio;

	private Set<Matricula> matriculas = new HashSet<Matricula>();

	public Grupo() {
		// TODO Auto-generated constructor stub
	}

	public Grupo(int id, Nivel nivel, String ciclo, int numero, String jornada, Date fechaFin, Date fechaInicio, Profesor profesor) {
		this.id = id;
		this.nivel = nivel;
		this.ciclo = ciclo;
		this.numero = numero;
		this.fechaFin = fechaFin;
		this.profesor = profesor;
		this.jornada = jornada;
		this.fechaInicio = fechaInicio;
	}


	/*@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "numero", column = @Column(name = "numero", nullable = false)),
			@AttributeOverride(name = "jornada", column = @Column(name = "jornada", nullable = false)),
			@AttributeOverride(name = "fechaInicio", column = @Column(name = "fechaInicio", nullable = false)),
			@AttributeOverride(name = "nivelCodigo", column = @Column(name = "nivelCodigo", nullable = false)),
	})*/
	@Id
	@Column(name = "id")
	public int getId() {
		return this.id;
	}


	@Column(name = "numero")
	public int getNumero() {
		return numero;
	}


	@Column(name = "ciclo")
	public String getCiclo() {
		return ciclo;
	}

	@Column(name = "fechaFin")
	public Date getFechaFin() {
		return fechaFin;
	}
	
	@Column(name = "fechaInicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	@Column(name = "jornada")
	public String getJornada() {
		return jornada;
	}


	//@MapsId("nivelCodigo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nivelCodigo", nullable = false, insertable = false, updatable = false)
	public Nivel getNivel() {
		return this.nivel;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesor_numero_documento", nullable = false, insertable = false, updatable = false)
	public Profesor getProfesor() {
		return profesor;
	}
	
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	@OneToMany(mappedBy = "grupo")
	public Set<Matricula> getMatriculas(){
		return this.matriculas;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}
	
	public void setMatriculas(Set<Matricula> matriculas){
		this.matriculas = matriculas;
	}
}
