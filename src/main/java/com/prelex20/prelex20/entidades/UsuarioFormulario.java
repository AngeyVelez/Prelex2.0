package com.prelex20.prelex20.entidades;

public class UsuarioFormulario {
		
	private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private String roles;
    
    public UsuarioFormulario() {
	}
    
    public UsuarioFormulario(String username, String password, String passwordConfirm, String roles) {
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.roles = roles;
	}

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public String getRoles() {
		return roles;
	}
    
    public void setRoles(String roles) {
		this.roles = roles;
	}
}
