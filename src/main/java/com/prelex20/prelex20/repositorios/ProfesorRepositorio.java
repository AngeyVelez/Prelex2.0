package com.prelex20.prelex20.repositorios;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.prelex20.prelex20.entidades.Profesor;

/*Interface que extiende de la interface CrudRepository, la cual ofrece metodos como:
 * findOne (para mostrar todos los datos de una preinscripcion dado un id), findAll
 * (para consultar todas las preinscripciones), delete (para eliminar de la base de datos una
 * preinscripcion dado un id), y save (para adicionar una nueva preinscripcion a la base de datos
 * o actualizar una ya existente)
 *@author andrea,sebastian
 *@version 2.0
 */

@Transactional
public interface ProfesorRepositorio extends JpaRepository<Profesor, String>{
	
	/** obtiene una lista de grupos en el que profesor da clase
	 * @param id identificador unico del profesor
	 * @return Linkedlist de objetos con los grupos que da el profesor.
	 */
	@Query(value = "select profesor_numero_documento from grupo where profesor_numero_documento= ?1 ;", nativeQuery = true)
	LinkedList<Object[]> validarProfesorEnGrupo(String id);
	
	/**
	 * @param p la nueva informacion del profesor
	 * @param id 
	 * 
	 */
	@Query(value = "select profesor_numero_documento from grupo where profesor_numero_documento= ?1 ;", nativeQuery = true)
	void actualizar(String id, Profesor p);

}