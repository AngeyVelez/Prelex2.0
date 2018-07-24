package com.prelex20.prelex20.repositorios;

import java.sql.Date;
import java.util.LinkedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.prelex20.prelex20.entidades.Matricula;

/**
 * Interface que extiende de la interface JpaRepository, la cual ofrece metodos como los que incluye un crud y
 * permite realizar consultas sql.
 * @author AngeyVelez
 * @version 1.0
 */

@Transactional
public interface MatriculaRepositorio extends JpaRepository<Matricula, Long>{
	/**Consulta en la base de datos los ingresos por grupos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return la lista de objetos que me contiene toda la info requerida (...).
	 */	
	@Query(value = "select year(grupo.fecha_inicio), nivel.nombre, grupo.ciclo, grupo.numero, sum(matricula.tarifa) from matricula, grupo, nivel where matricula.grupo_id = grupo.id and grupo.nivel_codigo = nivel.codigo and grupo.fecha_inicio >= ?1 and grupo.fecha_fin <= ?2 group by year(grupo.fecha_inicio), nivel.nombre, grupo.ciclo, grupo.numero order by 1, 2, 3, 4;", nativeQuery = true)
	LinkedList<Object[]> listarPorGrupo(Date fechaInicio, Date fechaFin);
	
	/**Consulta en la base de datos los ingresos por niveles dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return la lista de objetos que me contiene toda la info requerida (...).
	 */	
	@Query(value = "select year(grupo.fecha_inicio), nivel.nombre, sum(matricula.tarifa) from matricula, grupo, nivel where matricula.grupo_id = grupo.id and grupo.nivel_codigo = nivel.codigo and grupo.fecha_inicio >= ?1 and grupo.fecha_fin <= ?2 group by year(grupo.fecha_inicio), nivel.nombre order by 1, 2;", nativeQuery = true)
	LinkedList<Object[]> listarPorNivel(Date fechaInicio, Date fechaFin);
	
	/**Consulta en la base de datos los ingresos por ciclos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return la lista de objetos que me contiene toda la info requerida (...).
	 */	
	@Query(value = "select year(grupo.fecha_inicio), grupo.ciclo, sum(matricula.tarifa) from matricula, grupo where matricula.grupo_id = grupo.id and grupo.fecha_inicio >= ?1 and grupo.fecha_fin <= ?2 group by year(grupo.fecha_inicio), grupo.ciclo order by 1, 2;", nativeQuery = true)
	LinkedList<Object[]> listarPorCiclo(Date fechaInicio, Date fechaFin);
	
	/**Consulta en la base de datos los ingresos por anos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return la lista de objetos que me contiene toda la info requerida (...).
	 */	
	@Query(value = "select year(grupo.fecha_inicio), sum(matricula.tarifa) from matricula, grupo where matricula.grupo_id = grupo.id and grupo.fecha_inicio >= ?1 and grupo.fecha_fin <= ?2 group by year(grupo.fecha_inicio) order by 1;", nativeQuery = true)
	LinkedList<Object[]> listarPorAno(Date fechaInicio, Date fechaFin);

	Matricula findByEstudianteNumeroDocumentoAndGrupoId(String estudianteNumeroDocumento, int grupoId);

	@Query(value = "Select grupo.ciclo, nivel.nombre, grupo.jornada, CONCAT(estudiante.nombre1,' ',estudiante.nombre2),CONCAT(estudiante.apellido1,' ',estudiante.apellido2),estudiante.numero_documento,estudiante.eps,estudiante.telefono from estudiante, matricula, grupo, nivel where estudiante.numero_documento = matricula.estudiante_numero_documento AND grupo.id = matricula.grupo_id AND nivel.codigo = grupo.nivel_codigo AND grupo.fecha_inicio >= ?1 AND grupo.fecha_fin <= ?2 ORDER BY grupo.ciclo, grupo.jornada ASC;",nativeQuery = true)
	LinkedList<Object[]> reporteEstudiantes(Date fechaInicial, Date fechaFinal);
}