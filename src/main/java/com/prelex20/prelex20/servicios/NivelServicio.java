package com.prelex20.prelex20.servicios;

import java.util.LinkedList;

import com.prelex20.prelex20.entidades.Nivel;

public interface NivelServicio {
	
	/**Lista los niveles
	 * @return los niveles existentes en la base de datos
	 */	
	LinkedList<Nivel> listarNiveles();
	
	/**obtiene un nivel
	 * @param id el cual es el identificador de nivel
	 * @return el nivel el cual se esta buscando
	 */	
	Nivel obtenerNivel(String id);
	
	/**Guarda un nivel
	 * @param n nivel que se desea guardar
	 * @return el nivel que se adiciono
	 */	
	Nivel guardarNivel(Nivel n);
	
	/**elimina un nivel
	 * @param id el cual es el identificador de nivel
	 */
	void eliminarNivel(String id);

}