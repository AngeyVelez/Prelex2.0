package com.prelex20.prelex20.entidades;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Esta clase contiene todos los atributos y metodos para representar la tabla 'matricula' de la base de datos.
 * @author AngeyVelez
 * @version 1.0
 */
@Entity
@Table(name = "matricula", schema = "prelexbd")
public class Matricula implements Serializable{

	private static final long serialVersionUID = 1L;

	private long codigoMatricula;
	private int tarifa;
	private float nota;
	private String estadoFinal;
	private Estudiante estudiante;
	private Grupo grupo;
	
	public Matricula() {
		// TODO Auto-generated constructor stub
	}
	
	public Matricula(int tarifa, Estudiante estudiante, Grupo grupo) {
		
		this.tarifa = tarifa;
		this.estudiante = estudiante;
		this.grupo = grupo;
		this.estadoFinal = "";
	}
  
	public Matricula(Long codigoMatricula, String estadoFinal, Estudiante estudiante, int tarifa, Grupo grupo, float nota) {
		this.estadoFinal = estadoFinal;
		this.codigoMatricula = codigoMatricula;
		this.tarifa = tarifa;
		this.nota = nota;
		this.estudiante = estudiante;
		this.grupo = grupo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public long getCodigoMatricula() {
		return codigoMatricula;
	}

	@Column(name = "estado_final")
	public String getEstadoFinal() {
		return estadoFinal;
	}

	@Column(name = "nota")
	public float getNota() {
		return nota;
	}

	@Column(name = "tarifa", nullable = false)
	public int getTarifa() {
		return tarifa;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estudiante_numero_Documento")
	public Estudiante getEstudiante() {
		return this.estudiante;
	}


	/*
		@JoinColumns({
			@JoinColumn(name = "grupoNumero", referencedColumnName = "numero"),
			@JoinColumn(name = "grupoJornada", referencedColumnName = "jornada"),
			@JoinColumn(name = "grupo_fecha_inicio", referencedColumnName = "fechaInicio"),
			@JoinColumn(name = "grupo_nivel_codigo", referencedColumnName = "nivelCodigo")
		})
	*/

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "grupoId")
	public Grupo getGrupo(){
		return this.grupo;
	}

	public void setEstadoFinal(String estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public void setCodigoMatricula(Long codigoMatricula) {
		this.codigoMatricula = codigoMatricula;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}
