package com.prelex20.prelex20.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prelex20.prelex20.entidades.Profesor;
import com.prelex20.prelex20.repositorios.ProfesorRepositorio;

import java.util.LinkedList;

/**Clase que implementa la interfaz ProfesorServicio para luego usar los metodos
 * de esta en la controladora
 * @author Deivid, Sebastian
 * @version 2.0
 */
@Service
public class ProfesorServicioImpl implements ProfesorServicio{

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    @Override
    public LinkedList<Profesor> listarProfesores()
    {
        LinkedList<Profesor> profesores = new LinkedList<>();
        profesorRepositorio.findAll().iterator().forEachRemaining(profesores::add);
        return profesores;
    }

    @Override
    public Profesor obtenerProfesor(String id) {
        return profesorRepositorio.findOne(id);
    }

    @Override
    public Profesor guardarProfesor(Profesor profesor) {
        return profesorRepositorio.save(profesor);
    }

    @Override
    public void eliminarProfesor(String id) {
        profesorRepositorio.delete(id);

    }

	@Override
	public boolean validarDaclase(String id) {
		if(!profesorRepositorio.validarProfesorEnGrupo(id).isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
			
	}

	@Override
	public void actualizar(String id, Profesor p) {
		profesorRepositorio.actualizar(id,p);
		
	}
	
	@Override
    public Page<Profesor> findAllPageable(Pageable pageable) {
        return profesorRepositorio.findAll(pageable);
	}


}
