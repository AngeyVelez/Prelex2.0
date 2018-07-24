package com.prelex20.prelex20.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.prelex20.prelex20.entidades.Preinscripcion;

import java.util.LinkedList;

/*Interface que extiende de la interface JpaRepository, la cual ofrece metodos como:
 * findOne (para mostrar todos los datos de una preinscripcion dado un id), findAll
 * (para consultar todas las preinscripciones), delete (para eliminar de la base de datos una
 * preinscripcion dado un id), y save (para adicionar una nueva preinscripcion a la base de datos
 * o actualizar una ya existente)
 *@author andrea, Sebas
 *@version 1.3
 */

@Transactional
public interface PreinscripcionRepositorio extends JpaRepository<Preinscripcion, Integer> {    
    /**
     * Permite obtener la cantidad de preinscripciones por idioma
     * @return una lista de objetos, donde cada objeto representa una fila de lo que
     * devuelve la consulta.
     */
    @Query(value = "select idioma, count(*) from preinscripcion group by idioma order by idioma asc;", nativeQuery = true)
    LinkedList<Object[]> preinscritosPorIdioma();

    /**
     * Permite obtener la cantidad de preinscripciones totales
     * @return un valor entero que corresponde al número total de preinscripciones que
     * devuelve la consulta.
     */
    @Query(value = "select count(*) from preinscripcion;", nativeQuery = true)
    int totalPreinscritos();
    
    /**
     * Permite obtener la cantidad de preinscripciones por idioma y tipo de preinscripción
     * @return una lista de objetos, donde cada objeto representa una fila de lo que
     * devuelve la consulta.
     */
    @Query(value = "select COUNT(*) from preinscripcion where idioma=?1 and tipo=?2 group by idioma, tipo order by idioma asc;", nativeQuery = true)
    String preinscritosPorIdiomaTipo(String idioma, String tipo);

    /** Permite obtener la cantidad de Idiomas preinscritos
     * @return Lista con los idiomas
     */
    @Query(value= "SELECT DISTINCT idioma FROM preinscripcion ORDER BY idioma ASC;", nativeQuery = true)
    LinkedList<String> preinscripcionesIdiomas();
    /** Permite obtener la cantidad de Tipos existentes
     * @return Lista con los tipos existentes.
     */
    @Query(value = "SELECT DISTINCT tipo FROM preinscripcion ORDER BY tipo ASC;", nativeQuery = true)
	LinkedList<String> preinscripcionesTipos();
	/** Permite obtener el total de preinscritos por su Idioma
	 * @return Lista de tipo Object[ ] que contiene su idioma y su cantidad
	 */
	@Query(value = "SELECT idioma, count(idioma) FROM preinscripcion GROUP BY idioma ORDER BY idioma ASC;",nativeQuery = true)
	LinkedList<Object[]> preincripcionesTotalxIdioma();
}