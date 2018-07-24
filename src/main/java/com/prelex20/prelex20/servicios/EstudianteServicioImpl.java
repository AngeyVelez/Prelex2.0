package com.prelex20.prelex20.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prelex20.prelex20.entidades.Estudiante;
import com.prelex20.prelex20.repositorios.EstudianteRepositorio;

import java.util.LinkedList;

/**Clase que implementa la interfaz EstudianteServicio para luego usar sus metodos en la controladora.
 * @author Angey
 * @version 1.0
 */

@Service
public class EstudianteServicioImpl implements EstudianteServicio {
	
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    /**Permite listar todos los estudiantes.
	 * @return la lista de estudiantes.
	 */	
    @Override
    public LinkedList<Estudiante> listarEstudiantes()
    {
        LinkedList<Estudiante> estudiantes = new LinkedList<>();
        estudianteRepositorio.findAll().iterator().forEachRemaining(estudiantes::add);
        return estudiantes;
    }

    /**Permite obtener un estudiante dado la identificacion del estudiante.
	 * @param id idetificacion del estudiante a buscar.
	 * @return el estudiante correspondiente a la identificacion ingresada.
	 */	
    @Override
    public Estudiante obtenerEstudiante(String id) {
        return estudianteRepositorio.findOne(id);
    }

    /**Permite guardar un estudiante.
	 * @param estudiante recibe toda la informacion del estudiante a guardar.
	 * @return el estudiante que se guardo.
	 */	
    @Override
    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepositorio.save(estudiante);
    }

    /**Permite eliminar un estudiante dado la identificacion del estudiante.
	 * @param id idetificacion del estudiante a eliminar.
	 */	
    @Override
    public void eliminarEstudiante(String id) {
        estudianteRepositorio.delete(id);

    }
}