package com.prelex20.prelex20.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prelex20.prelex20.entidades.Matricula;
import com.prelex20.prelex20.repositorios.MatriculaRepositorio;

import java.sql.Date;
import java.util.LinkedList;

/**Clase que implementa la interfaz ProfesorServicio para luego usar los metodos
 * de esta en la controladora
 * @author AngeyVelez
 * @version 1.0
 */
@Service
public class MatriculaServicioImpl implements MatriculaServicio{

    @Autowired
    private MatriculaRepositorio matriculaRepositorio;

    /**Implementa el metodo necesario para listar todas matriculas
	 * @return una lista con todas las matriculas registradas.
	 */
    @Override
    public LinkedList<Matricula> listarMatriculas()
    {
        LinkedList<Matricula> matriculas = new LinkedList<>();
        matriculaRepositorio.findAll().iterator().forEachRemaining(matriculas::add);
        return matriculas;
    }

    /**Implementa el metodo necesario para obtener una matricula dado un id
	 * @param id codigo de la matricula a consultar
	 * @return la matricula buscada
	 */
    @Override
    public Matricula obtenerMatricula(Long id) {
        return matriculaRepositorio.findOne(id);
    }

	/**Implementa el metodo necesario para guardar una matricula
	 * @param matricula matricula que se desea guardar
	 * @return la matricula que se adiciono
	 */	
    @Override
    public Matricula guardarMatricula(Matricula matricula) {
        return matriculaRepositorio.save(matricula);
    }

	/**Implementa el metodo necesario para eliminar una matricula dado un id
	 * @param id codigo de la matricula que se desea eliminar
	 * @return void
	 */	
    @Override
    public void eliminarMatricula(Long id) {
    	matriculaRepositorio.delete(id);

    }
    
	/**Implementacion de la consulta de los ingresos por grupos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return una lista de objetos que me contiene toda la info requerida.
	 */	
    @Override
    public LinkedList<Object[]> listarPorGrupo(Date fechaInicio, Date fechaFin) {
    	LinkedList<Object[]> resultado = new LinkedList<>();
        matriculaRepositorio.listarPorGrupo(fechaInicio, fechaFin).iterator().forEachRemaining(resultado::add);
        return resultado;
    } 

	/**Implementacion de la consulta los ingresos por niveles dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return una lista de objetos que me contiene toda la info requerida.
	 */	
    @Override
    public LinkedList<Object[]> listarPorNivel(Date fechaInicio, Date fechaFin) {
    	LinkedList<Object[]> resultado = new LinkedList<>();
        matriculaRepositorio.listarPorNivel(fechaInicio, fechaFin).iterator().forEachRemaining(resultado::add);
        return resultado;
    }  
    
    /**Implementacion de la consulta los ingresos por ciclos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return una lista de objetos que me contiene toda la info requerida.
	 */
    @Override
    public LinkedList<Object[]> listarPorCiclo(Date fechaInicio, Date fechaFin) {
    	LinkedList<Object[]> resultado = new LinkedList<>();
        matriculaRepositorio.listarPorCiclo(fechaInicio, fechaFin).iterator().forEachRemaining(resultado::add);
        return resultado;
    } 
    
    /**Implementacion de la consulta los ingresos por anos dado un rango de fechas.
	 * @param fechaInicio la fecha desde la que se empieza a consultar los registros.
	 * @param fechaFin la fecha hasta donde se realiza la consulta de los ingresos.
	 * @return una lista de objetos que me contiene toda la info requerida.
	 */
    @Override
    public LinkedList<Object[]> listarPorAno(Date fechaInicio, Date fechaFin) {
    	LinkedList<Object[]> resultado = new LinkedList<>();
        matriculaRepositorio.listarPorAno(fechaInicio, fechaFin).iterator().forEachRemaining(resultado::add);
        return resultado;
    } 
  
  /**Busca en la base de datos una matricula que tenga los parametros
	 * que se ingresan
	 * @param estudianteNumeroDocumento es el id de la entidad estudiante
	 * @param grupoId es el id de la entidad grupo
	 * @return la matricula que se adiciono
	 */
	@Override
	public Matricula buscarPorEstudianteNumeroDocumentoAndGrupoId(String estudianteNumeroDocumento, int grupoId) {
		// TODO Auto-generated method stub
		return matriculaRepositorio.findByEstudianteNumeroDocumentoAndGrupoId(estudianteNumeroDocumento, grupoId);
	}

@Override
public LinkedList<Object[]> reporteEstudiantes(Date fechaInicial, Date fechaFinal) {
	LinkedList<Object[]> reporteEstudiantes = new LinkedList<>();
	reporteEstudiantes = matriculaRepositorio.reporteEstudiantes(fechaInicial,fechaFinal);
	return reporteEstudiantes;
}
 
}
