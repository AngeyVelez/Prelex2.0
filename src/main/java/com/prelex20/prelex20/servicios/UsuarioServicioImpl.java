package com.prelex20.prelex20.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prelex20.prelex20.entidades.Rol;
import com.prelex20.prelex20.entidades.Usuario;
import com.prelex20.prelex20.repositorios.RolRepositorio;
import com.prelex20.prelex20.repositorios.UsuarioRepositorio;

import java.util.HashSet;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private RolRepositorio rolRepositorio;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(Usuario usuario, String name) {
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		HashSet<Rol> rols = new HashSet<>();
		Rol rol = rolRepositorio.findByName(name);
		rols.add(rol);
		usuario.setRoles(rols);
		usuarioRepositorio.save(usuario);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepositorio.findByUsername(username);
	}

}
