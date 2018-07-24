package com.prelex20.prelex20.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prelex20.prelex20.entidades.Rol;
import com.prelex20.prelex20.entidades.Usuario;
import com.prelex20.prelex20.repositorios.UsuarioRepositorio;

import java.util.HashSet;
import java.util.Set;

@Service
public class DetallesUsuarioServicioImpl implements UserDetailsService {
	 @Autowired
	    private UsuarioRepositorio usuarioRepositorio;

	    @Override
	    @Transactional(readOnly = true)
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    	Usuario usuario = usuarioRepositorio.findByUsername(username);
	        System.out.println("Usuario: "+username+" retorna"+usuario);
	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        for (Rol rol : usuario.getRols()){
	        	System.out.println("rol "+rol.getName());
	            grantedAuthorities.add(new SimpleGrantedAuthority(rol.getName()));
	        }

	        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), grantedAuthorities);
	    }
}
