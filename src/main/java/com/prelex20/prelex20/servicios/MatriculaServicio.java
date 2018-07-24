package com.prelex20.prelex20.servicios;

import java.sql.Date;
import java.util.LinkedList;

import com.prelex20.prelex20.entidades.Matricula;

/**Interface donde se nombran los metodos a implementar
 * @author AngeyVelez
 * @version 1.0
 */
public interface MatriculaServicio {
	
	/**Lista todas matriculas
	 * @return una lista con todas las matriculas registradas.
	 */
	LinkedList<Matricula> listarMatriculas();
	
	/**Permite obtener una matricula dado un id
	 * @param id codigo de la matricula a consultar
	 * @return la matricula buscada
	 */	
	Matricula obtenerMatricula(Long id);
	
	/**Guarda una matricula
	 * @param matricula matricula que se desea guardar
	 * @return la matricula que se adiciono
	 */	
	Matricula guardarMatricula(Matricula matricula);
	
	/**Elimina una matricula dado un id
	 * @param id codigo de la matricula que se desea eliminar
	 * @return void
	 */	
	void eliminarMatricula(Long id);
	
	/**Consulta los ingresos por grupos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return una lista de objetos que me contiene toda la info requerida.
	 */	
	LinkedList<Object[]> listarPorGrupo(Date fechaInicio, Date fechaFin);

	/**Consulta los ingresos por niveles dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return una lista de objetos que me contiene toda la info requerida.
	 */	
	LinkedList<Object[]> listarPorNivel(Date fechaInicio, Date fechaFin);

	/**Consulta los ingresos por ciclos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return una lista de objetos que me contiene toda la info requerida.
	 */	
	LinkedList<Object[]> listarPorCiclo(Date fechaInicio, Date fechaFin);

	/**Consulta los ingresos por anos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return una lista de objetos que me contiene toda la info requerida.
	 */	
	LinkedList<Object[]> listarPorAno(Date fechaInicio, Date fechaFin);
  
	/**Busca en la base de datos una matricula que tenga los parametros
	 * que se ingresan
	 * @param estudianteNumeroDocumento es el id de la entidad estudiante
	 * @param grupoId es el id de la entidad grupo
	 * @return la matricula que se adiciono
	 */	
	Matricula buscarPorEstudianteNumeroDocumentoAndGrupoId(String estudianteNumeroDocumento, int grupoId);
	/**
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 */
	LinkedList<Object[]> reporteEstudiantes(Date fechaInicial, Date fechaFinal);
}