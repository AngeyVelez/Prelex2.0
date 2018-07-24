package com.prelex20.prelex20.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.prelex20.prelex20.entidades.Estudiante;

/*Interface que extiende de la interface CrudRepository, la cual ofrece metodos como:
 * findOne (para mostrar todos los datos de una preinscripcion dado un id), findAll
 * (para consultar todas las preinscripciones), delete (para eliminar de la base de datos una
 * preinscripcion dado un id), y save (para adicionar una nueva preinscripcion a la base de datos
 * o actualizar una ya existente)
 *@author andrea
 *@version 1.0
 */

@Transactional
public interface EstudianteRepositorio extends JpaRepository<Estudiante, String>{

}