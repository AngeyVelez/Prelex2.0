package com.prelex20.prelex20.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prelex20.prelex20.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	Usuario findByUsername(String username);
}
