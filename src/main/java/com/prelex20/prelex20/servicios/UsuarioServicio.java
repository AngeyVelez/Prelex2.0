package com.prelex20.prelex20.servicios;

import com.prelex20.prelex20.entidades.Usuario;

public interface UsuarioServicio {
	 void save(Usuario usuario, String name);

	 Usuario findByUsername(String username);
}
