package com.prelex20.prelex20.servicios;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prelex20.prelex20.entidades.Nivel;
import com.prelex20.prelex20.entidades.Rol;
import com.prelex20.prelex20.repositorios.RolRepositorio;

@Service
public class RolServicioImpl implements RolServicio{

	@Autowired
	RolRepositorio rolRepositorio;
	
	@Override
	public LinkedList<Rol> listar() {
		LinkedList<Rol> rols = new LinkedList<>();
		rolRepositorio.findAll().iterator().forEachRemaining(rols::add);
		return rols;
	}

}
