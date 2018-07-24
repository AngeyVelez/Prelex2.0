package com.prelex20.prelex20.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prelex20.prelex20.entidades.Rol;

public interface RolRepositorio  extends JpaRepository<Rol, Long>{
	Rol findByName(String name);
}
