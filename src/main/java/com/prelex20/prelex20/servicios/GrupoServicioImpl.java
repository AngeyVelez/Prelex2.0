package com.prelex20.prelex20.servicios;

import java.sql.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prelex20.prelex20.entidades.Grupo;
import com.prelex20.prelex20.entidades.Nivel;
import com.prelex20.prelex20.repositorios.GrupoRepositorio;

/**Clase que implementa la interfaz GrupoServicio  para luego usar los metodos
 * de esta en la controladora
 * @author deivid
 * @version 1.0
 */
@Service
public class GrupoServicioImpl implements GrupoServicio{

	@Autowired
	private GrupoRepositorio grupoRepositorio;
	
	/**Lista los grupos
	 * @return los grupos existentes en la base de datos
	 */
	@Override
	public LinkedList<Grupo> listarGrupos() {
		LinkedList<Grupo> grupos = new LinkedList<>();
		grupoRepositorio.findAll().iterator().forEachRemaining(grupos::add);
		return grupos;
	}

	@Override
	public Grupo guardarGrupo(Grupo g) {
		return grupoRepositorio.save(g);
	}

	/**obtiene un grupo
	 * @param id es el identificador de la entidad grupo
	 */	
	@Override
	public Grupo obtenerGrupoPorId(int id) {
		
		return grupoRepositorio.getOne(id);
	}

	/**Guarda un grupo
	 * @param g grupo que se desea guardar
	 * @return el grupo que se adiciono
	 */	
	@Override
	public void eliminarGrupo(int numero, String jornada, Date fechaInicio, Nivel nivelCodigo) {

	}

	/**obtiene un grupo
	 * @param numero el cual es uno de los identificadores de un grupo
	 * @param jornada el cual esta asociado a un grupo
	 * @param fechaInicio el cual esta asociado a un grupo
	 * @param nivelCodigo el cual esta asociado a un grupo
	 * @return un grupo al cual corresponda los parametros ingresados
	 */	
	@Override
	public Grupo obtenerGrupoPorNivelCodigoAndFechaInicioAndJornadaAndNumero(String nivelCodigo, Date fechaInicio,String jornada, int numero) {
		Grupo grupo = grupoRepositorio.findByNivelCodigoAndFechaInicioAndJornadaAndNumero(nivelCodigo, fechaInicio, jornada, numero);
		return grupo;
	}

	@Override
	public void eliminarGrupo(int id) {
		grupoRepositorio.deleteById(id);
	}

	@Override
	public boolean validarEstMatriculadosxGrupo(int id) {
		if(!grupoRepositorio.matriculadosxGrupo(id).isEmpty())
		{
			return true;
		}
		else {
			return false;
		}		
	}

	@Override
	public boolean validarGrupo() {
	
		return false;
	}
	@Override
	public void guardarGrupo(int incrementalIdGrupo, Object grupo) {
		
	}

	@Override
	public int obtenerMaximoGrupoId() {
		return grupoRepositorio.MaximoId();
	}

	@Override
	public void actualizarGrupo(int id, Grupo g) {
		grupoRepositorio.actualizar(id,g);
	}
}
