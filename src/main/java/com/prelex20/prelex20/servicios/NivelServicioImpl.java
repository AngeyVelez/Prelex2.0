package com.prelex20.prelex20.servicios;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prelex20.prelex20.entidades.Nivel;
import com.prelex20.prelex20.repositorios.NivelRepositorio;

@Service
public class NivelServicioImpl implements NivelServicio{

	@Autowired
	private NivelRepositorio nivelRepositorio;
	
	/**Lista los niveles
	 * @return los niveles existentes en la base de datos
	 */	
	@Override
	public LinkedList<Nivel> listarNiveles() {
		LinkedList<Nivel> niveles = new LinkedList<>();
		nivelRepositorio.findAll().iterator().forEachRemaining(niveles::add);
		return niveles;
	}

	/**obtiene un nivel
	 * @param id el cual es el identificador de nivel
	 * @return el nivel el cual se esta buscando
	 */
	@Override
	public Nivel obtenerNivel(String id) {
		return nivelRepositorio.getOne(id);
	}

	/**Guarda un nivel
	 * @param n nivel que se desea guardar
	 * @return el nivel que se adiciono
	 */	
	@Override
	public Nivel guardarNivel(Nivel n) {
		return nivelRepositorio.save(n);
	}

	/**elimina un nivel
	 * @param id el cual es el identificador de nivel
	 */
	@Override
	public void eliminarNivel(String id) {
		nivelRepositorio.deleteById(id);		
	}

}
