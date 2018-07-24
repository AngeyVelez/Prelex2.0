package com.prelex20.prelex20.servicios;

import java.util.LinkedList;

import com.prelex20.prelex20.entidades.Estudiante;
/**Clase interfaz donde se nombran los metodos del CRUD.
 * @author Angey
 * @version 1.0
 */
public interface EstudianteServicio {
	/**Permite listar todos los estudiantes.
	 * @return la lista de estudiantes.
	 */	
	LinkedList<Estudiante> listarEstudiantes();
	
	/**Permite obtener un estudiante dado la identificacion del estudiante.
	 * @param id idetificacion del estudiante a buscar.
	 * @return el estudiante correspondiente a la identificacion ingresada.
	 */	
	Estudiante obtenerEstudiante(String id);
	
	/**Permite guardar un estudiante.
	 * @param estudiante recibe toda la informacion del estudiante a guardar.
	 * @return el estudiante que se guardo.
	 */	
	Estudiante guardarEstudiante(Estudiante estudiante);
	
	/**Permite eliminar un estudiante dado la identificacion del estudiante.
	 * @param id idetificacion del estudiante a eliminar.
	 */	
	void eliminarEstudiante(String id);
}