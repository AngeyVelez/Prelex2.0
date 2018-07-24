package com.prelex20.prelex20.servicios;

import java.util.LinkedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prelex20.prelex20.entidades.Profesor;

/**Interface para implementar los metodos del CRUD
 * @author Deivid
 * @version 1.0
 */
public interface ProfesorServicio {
	
	/**Permite obtener la lista de profesores existentes
	 * @return lista de profesores
	 */	
	LinkedList<Profesor> listarProfesores();
	
	/**Permite obtener un profesor dado un id
	 * @param id id del profesor a consultar
	 * @return profesor buscado
	 */	
	Profesor obtenerProfesor(String id);
	
	/**Guarda uu profesor
	 * @param profesor Profesor que se desea guardar
	 * @return el profesor que se adiciono
	 */	
	Profesor guardarProfesor(Profesor profesor);
	
	/**Elimina un profesor dado un id
	 * @param id id del profesor que se desea eliminar
	 */	
	void eliminarProfesor(String id);
	/** validar que el profesor da clase en algun grupo
	 * @param id Documento del profesor
	 * @return boolean que si es true da en algun grupo y si es false no.
	 */
	boolean validarDaclase(String id);
	
	/** actualiza el profesor 
	 *  con la nueva informacion
	 * @param id del antiguo profesor que estaba registrado 
	 * @param p con la nueva informacion del profesor.
	 */
	void actualizar(String id, Profesor p);
	
	Page<Profesor> findAllPageable(Pageable pageable);
	
	/*
	 * ATENCION NO COMENTO EN LA IMPLEMENTACION POR QUE SERIA COPIAR DOCUMENTACION ADMINISTRADOR DE CALIDAD
	 */
}
