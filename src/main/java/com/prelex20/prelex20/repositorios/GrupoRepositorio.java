package com.prelex20.prelex20.repositorios;

import java.sql.Date;
import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.prelex20.prelex20.entidades.Grupo;


/*Interface que extiende de la interface JpaRepository, la cual ofrece metodos como:
d * findOne (para mostrar todos los datos de una preinscripcion dado un id), findAll
 * (para consultar todas las preinscripciones), delete (para eliminar de la base de datos una
 * preinscripcion dado un id), y save (para adicionar una nueva preinscripcion a la base de datos
 * o actualizar una ya existente) ademas de permitirnos crear consultas
 *@author andrea, sebastian
 *@version 2.0
 */

@Transactional
public interface GrupoRepositorio extends JpaRepository<Grupo, Integer>{
	/** ESTE METODO NO ES MIO ATT: ADMINISTRADOR DE CALIDAD 
	 *  asi que no se comentarlo jeje :3
	 * @param nivelCodigo
	 * @param fechaInicio
	 * @param jornada
	 * @param numero
	 * @return
	 */
	Grupo findByNivelCodigoAndFechaInicioAndJornadaAndNumero(String nivelCodigo, Date fechaInicio, String jornada, int numero);
	
	/** metodo encargado de obtener los estudiantes matriculados en un grupo
	 * @param id unico del grupo.
	 * @return LinkedList de objetos con los estudiantes matriculados
	 */
	@Query(value = "SELECT * FROM matricula WHERE grupo_id = ?1 ;", nativeQuery = true)
	LinkedList<Object[]> matriculadosxGrupo(int id);
	
	/** Obtiene de la base de datos el ultimo id registrado
	 * @return int con el valor del ultimo id registrado en la base de datos
	 */
	@Query(value = "SELECT MAX(id) FROM Grupo ;", nativeQuery = true)
	int MaximoId();
	
	/** metodo encargado de actualizar el grupo en el repositorio.
	 * @param id identificador unico del grupo
	 * @param g datos del grupo que se cambiaran
	 */
	@Query(value = "SELECT MAX(id) FROM Grupo ;", nativeQuery = true)
	void actualizar(int id, Grupo g);
}