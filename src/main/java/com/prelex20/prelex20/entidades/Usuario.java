package com.prelex20.prelex20.entidades;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<Rol> rols;
    
    public Usuario() {
	}
    
    public Usuario(String username, String password, String passwordConfirm) {
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    public Set<Rol> getRols() {
        return rols;
    }

    public void setRols(Set<Rol> rols) {
        this.rols = rols;
    }
}
