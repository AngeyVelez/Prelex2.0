package com.prelex20.prelex20.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prelex20.prelex20.entidades.Preinscripcion;
import com.prelex20.prelex20.repositorios.PreinscripcionRepositorio;

import java.util.LinkedList;

/**Clase que implementa la interfaz PreinscripcionServicio  para luego usar los metodos
 * de esta en la controladora
 * @author andrea, Sebas
 * @version 1.2
 */
@Service
public class PreinscripcionServicioImpl implements PreinscripcionServicio {

    @Autowired
    private PreinscripcionRepositorio preinscripcionRepositorio;

    @Override
    public LinkedList<Preinscripcion> listarPreinscripciones()
    {
        LinkedList<Preinscripcion> preinscripciones = new LinkedList<>();
        preinscripcionRepositorio.findAll().iterator().forEachRemaining(preinscripciones::add);
        return preinscripciones;
    }

    @Override
    public Preinscripcion obtenerPreinscripcion(int id) {
        return preinscripcionRepositorio.findOne(id);
    }

    /**Guarda una preinscripcion
	 * @param p preinscripcion que se desea guardar
	 * @return la preinscripcion que se adiciono
	 */	
    @Override
    public Preinscripcion guardarPreinscripcion(Preinscripcion preinscripcion) {
        return preinscripcionRepositorio.save(preinscripcion);
    }



    @Override
    public void eliminarPreinscripcion(int id) {
        preinscripcionRepositorio.delete(id);
    }

    @Override
    public int totalPreinscripciones(){
        int preinscripciones = 0;
        preinscripciones = preinscripcionRepositorio.totalPreinscritos();
        return preinscripciones;
    }
    
    @Override
    public String obtenerReporteIdiomaTipo(String idioma, String tipo){
    	String reporteIdiomaTipo = "";
         reporteIdiomaTipo = preinscripcionRepositorio.preinscritosPorIdiomaTipo(idioma, tipo);
         return reporteIdiomaTipo;
    }

	@Override
	public LinkedList<String> obtenerIdiomas() {
		LinkedList<String> idiomas = new LinkedList<>();
		idiomas = preinscripcionRepositorio.preinscripcionesIdiomas();
		return idiomas;
	}

	@Override
	public LinkedList<String> obtenerTipos() {
		LinkedList<String> tipos = new LinkedList<>();
		tipos = preinscripcionRepositorio.preinscripcionesTipos();
		return tipos;
	}

	@Override
	public LinkedList<Object[]> obtenerTotalesxIdioma() {
		LinkedList<Object[]> TotalesxIdioma = new LinkedList<>();
		TotalesxIdioma = preinscripcionRepositorio.preincripcionesTotalxIdioma();
		return TotalesxIdioma;
	}
}