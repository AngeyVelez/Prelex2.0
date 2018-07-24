package com.prelex20.prelex20.entidades;

import java.util.Set;

import java.util.HashSet;
import javax.persistence.*;

@Entity
@Table(name = "nivel")
public class Nivel implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nombre;
	private String idioma;
	private String tipo;
	private Set<Grupo> grupos = new HashSet<Grupo>(0);
	
	public Nivel() {
		// TODO Auto-generated constructor stub
	}


	public Nivel(String codigo, String nombre, String idioma, String tipo){
	    this.codigo = codigo;
	    this.nombre = nombre;
	    this.idioma = idioma;
	    this.tipo = tipo;
    }

    @Id
    @Column(name = "codigo", unique = true, nullable = false)
    public String getCodigo() {
		return this.codigo;
	}

    @Column(name = "idioma", nullable = false)
    public String getIdioma() {
		return idioma;
	}

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
		return nombre;
	}
    
    @Column(name = "tipo")
    public String getTipo() {
		return tipo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nivel")
	public Set<Grupo> getGrupos() {
		return this.grupos;
	}
	
	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
