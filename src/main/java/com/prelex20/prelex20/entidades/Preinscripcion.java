/**
 * 
 */
package com.prelex20.prelex20.entidades;

import javax.persistence.*;
import java.sql.Date;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


/** Esta clase, es una entidad qu respresenta la tabla 
 * Preinscripcion de la base de datos
 * @author andrea
 * @version 1.0
 */
@Entity
public class Preinscripcion
{
    private int id;
    private String numeroDocumento;
	private String nombreCompleto;
	private String tipo;
	private String idioma;
	private String jornada;
	private String email;
	private Date fecha;
	private String telefono;

	
	public Preinscripcion() {
		// TODO Auto-generated constructor stub
	}
	
	public Preinscripcion(int id, String numeroDocumento, String nombreCompleto,String tipo, String idioma, String jornada, String email, Date fecha, String telefono) {
		this.id = id;
	    this.numeroDocumento = numeroDocumento;
	    this.nombreCompleto = nombreCompleto;
		this.tipo = tipo;
		this.idioma = idioma;
		this.jornada = jornada;
		this.email = email;
		this.fecha = fecha;
        this.telefono = telefono;

    }
	
	public Preinscripcion(String numeroDocumento, String nombreCompleto,String tipo, String idioma, String jornada, String email, String telefono) {
		this.numeroDocumento = numeroDocumento;
	    this.nombreCompleto = nombreCompleto;
		this.tipo = tipo;
		this.idioma = idioma;
		this.jornada = jornada;
		this.email = email;
        this.telefono = telefono;

    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    public int getId() { 
    	return id; 
    }

    public void setId(int id) { 
    	this.id = id;
    }

    @Size(min=2, max=40, message = "El tamaño del nombre debe estar entre 2 y 40")
    @Column(name= "nombreCompleto")
    public String getNombreCompleto() { 
    	return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) { 
    	this.nombreCompleto = nombreCompleto;
    }

    @Column(name= "jornada")
    public String getJornada() { 
    	return jornada;
    }

    public void setJornada(String jornada) { 
    	this.jornada = jornada;
    }

    @NotBlank(message = "Por favor ingrese el correo")
    @Email(message = "El correo es invalido")
    @Column(name= "email")
    public String getEmail() { 
    	return email;
    }

    public void setEmail(String email) { 
    	this.email = email; 
    }

    @Column(name= "fecha")
    public Date getFecha() { 
    	return fecha;
    }

    public void setFecha(Date fecha) { 
    	this.fecha = fecha;
    }

    @Column(name= "tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

    @NotBlank(message = "Por favor ingrese el numero de documento")
	@Column(name ="numeroDocumento")
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

    @NotBlank(message = "Por favor ingrese el telefono")
	@Column(name = "telefono")
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Column(name = "idioma")
	public String getIdioma() {
		return idioma;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
}
