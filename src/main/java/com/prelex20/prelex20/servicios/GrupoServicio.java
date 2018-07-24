package com.prelex20.prelex20.servicios;

import java.sql.Date;
import java.util.LinkedList;

import com.prelex20.prelex20.entidades.Grupo;
import com.prelex20.prelex20.entidades.Nivel;

/**Interface para nombrar los metodos los cuales seran implementados
 * @author deivid, sebastian 
 * @version 2.0
 */
public interface GrupoServicio {
	
	/**Obtiene un grupo
	 * @param numero el cual es uno de los identificadores de un grupo
	 * @param jornada el cual esta asociado a un grupo
	 * @param fechaInicio el cual esta asociado a un grupo
	 * @param nivelCodigo el cual esta asociado a un grupo
	 * @return un grupo al cual corresponda los parametros ingresados
	 */	
	Grupo obtenerGrupoPorNivelCodigoAndFechaInicioAndJornadaAndNumero(String nivelCodigo, Date fechaInicio, String jornada, int numero);
	
	/**Lista los grupos
	 * @return los grupos existentes en la base de datos
	 */	
	LinkedList<Grupo> listarGrupos();
	
	/**obtiene un grupo
	 * @param id es el identificador de la entidad grupo
	 */	
	Grupo obtenerGrupoPorId(int id);
	
	/**Guarda un grupo
	 * @param g grupo que se desea guardar
	 * @return el grupo que se adiciono
	 */	
	Grupo guardarGrupo(Grupo g);
	
	/**elimina un grupo al cual corresponden los identificadores
	 * @param numero el cual es uno de los identificadores de un grupo
	 * @param jornada el cual es uno de los identificadores de un grupo
	 * @param fechaInicio el cual es uno de los identificadores de un grupo
	 * @param nivelCodigo el cual es uno de los identificadores de un grupo
	 */	
	void eliminarGrupo(int numero, String jornada, Date fechaInicio, Nivel nivelCodigo);
	
	/**  elimina el grupo por su identificador unico
	 * @param id identificador unico del grupo
	 */
	void eliminarGrupo(int id);
	
	/** valida que no haya estudiantes en ese grupo matriculados para que no elimine el grupo
	 *  con sus estudiantes matriculados.
	 * @param id identificador unico del grupo.
	 * @return boolean que en caso de ser true es que hay estudiantes matriculados y al contrario. 
	 */
	boolean validarEstMatriculadosxGrupo(int id);
	
	/** valida que el grupo que desean ingresar a la bd no exista
	 * @return boolean que si es true es por que existe ya ese grupo y false si es al contrario.
	 */
	boolean validarGrupo();
	/** obtiene el ultimo Id de los grupos ya que es un incremental
	 * @return el ultimo valor del grupo para incrementarlo en la controladora.
	 */
	int obtenerMaximoGrupoId();
	/** metodo para agregar grupo 
	 *  donde manda los parametros y el incremento del valor
	 * @param incrementalIdGrupo nuevo id con referencia al ultimo id registrado + 1 
	 * @param grupo Objecto con los demas datos que se deben ingresar
	 */
	void guardarGrupo(int incrementalIdGrupo, Object grupo);

	/** actualiza el grupo
	 * @param id identificador unico del grupo
	 * @param g nueva informacion del grupo
	 */
	void actualizarGrupo(int id, Grupo g);
	
	/* ¡ATENCION!: Recuerde que yo no documento los metodos en la clase que la 
	implementa ya que seria duplicar documentacion. ATT: Administador de Calidad SOFTWARE Inc. */
	
}