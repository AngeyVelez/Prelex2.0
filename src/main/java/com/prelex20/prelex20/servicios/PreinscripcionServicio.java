package com.prelex20.prelex20.servicios;

import java.util.LinkedList;

import com.prelex20.prelex20.entidades.Preinscripcion;

/**Interface para definir los métodos relacionados con el manejo
 * de preinscripciones
 * @author Andrea, Sebastian
 * @version 1.2
 */
public interface PreinscripcionServicio {
	
	LinkedList<Preinscripcion> listarPreinscripciones();

	/**Permite obtener una preinscripcion dado un id
	 * @param id id de la preinscipcion Lia consultar
	 * @return la preinscripcion buscada
	 */	
	Preinscripcion obtenerPreinscripcion(int id);
	
	/**Guarda una preinscripcion
	 * @param p preinscripcion que se desea guardar
	 * @return la preinscripcion que se adiciono
	 */	
	Preinscripcion guardarPreinscripcion(Preinscripcion preinscripcion);
	
	/**Elimina una preincripcion dado un id
	 * @param id id de la preinscipcion que se desea eliminar
	 * @return void
	 */	
	void eliminarPreinscripcion(int id);

	/**
	 * Permite obtener la cantidad de preinscripciones totales
	 *@return un valor entero que corresponde al número total de preinscripciones
	 */
	int totalPreinscripciones();
	
    /**Permite obtener un reporte de las preinscripciones por
     * idioma y tipo
     * @return un LinkedList donde se almacenan la lista de objetos
     * retornados por el método preinscritosPorIdiomaTipo y el
     * método preinscritosPorIdioma
     */
	String obtenerReporteIdiomaTipo(String idioma, String tipo);
	
	/** metodo que obtiene los idiomas solicitado en el la preinscripcion
	 * @return Lista de tipo String con todos los idiomas.
	 */
	LinkedList<String> obtenerIdiomas();
	
	/** metodo que obtiene los idiomas solicitado en el la preinscripcion
	 * @return Lista de tipo String con todos los tipos.
	 */
	LinkedList<String> obtenerTipos();
  
	/** metodo que obtiene los idiomas solicitado en el la preinscripcion
	 * @return Lista de tipo Object[ ] con todos los idiomas y su respectiva cantidad total de preinscripciones.
	 */
	LinkedList<Object[]> obtenerTotalesxIdioma();
}
