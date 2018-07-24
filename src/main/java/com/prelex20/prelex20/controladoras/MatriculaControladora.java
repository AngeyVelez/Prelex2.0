package com.prelex20.prelex20.controladoras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prelex20.prelex20.entidades.Estudiante;
import com.prelex20.prelex20.entidades.Grupo;
import com.prelex20.prelex20.entidades.Matricula;
import com.prelex20.prelex20.entidades.Nivel;
import com.prelex20.prelex20.servicios.EstudianteServicio;
import com.prelex20.prelex20.servicios.GrupoServicio;
import com.prelex20.prelex20.servicios.MatriculaServicio;
import com.prelex20.prelex20.servicios.NivelServicio;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Clase que funciona como controladora para la interaccion con la base de datos
 * @author AngeyVelez
 * @version 1.0
 */
@Controller
@RequestMapping("/matricula")
public class MatriculaControladora {
	@Autowired
	private MatriculaServicio matriculaServicio;
	
	@Autowired
	private GrupoServicio grupoServicio;
	
	@Autowired
	private EstudianteServicio estudianteServicio;
	
	@Autowired
	private NivelServicio nivelServicio;

	/**
	 * Lista todas las matriculas presentes en la base de datos a traves de
	 * una peticion GET; esta lista es desplegada en la ruta '/matriculas'
	 * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @return el nombre de la pagina donde se mostrara la lista de matriculas
	 */
	/*@RequestMapping(value = "/matriculas", method = RequestMethod.GET)
	public String lista(Model model) {
		LinkedList<Matricula> matriculas = matriculaServicio.listarMatriculas();
		for (Matricula matricula : matriculas) {
			System.out.println(matricula.getCodigoMatricula());
		}

		model.addAttribute("matriculas", matriculas);
		return "matriculas";
	}*/

	/**
	 * Dado un id en la ruta 'matricula/{id}', muestra toda la informacion de
	 * la matricula correspondiente
	 * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @param id es el codigo de la matricula que se desea visualizar
	 * @return el nombre de la pagina donde se mostraran los datos
	 */
	@RequestMapping("/{id}")
	public String mostrarMatricula(@PathVariable Long id, Model model) {
		LinkedList<Matricula> matriculas = new LinkedList<>();
		Matricula matricula = matriculaServicio.obtenerMatricula(id);

		if (matricula != null) {
			matriculas.add(matricula);
		}
		model.addAttribute("matriculas", matriculas);
		return "matriculas";
	}

	/**
	 * Dado un id en la ruta 'matricula/editar/{id}', muestra toda la
	 * informacion de la matricula correspondiente, pero con la opcion de poder
	 * modificar campos de la misma y guardarlos
	 * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @param id es el codigo de la matricula que se desea editar
	 * @return el nombre de la pagina donde se podra realizar la edicion
	 */
	@RequestMapping("/editar/{id}")
	public String editarMatricula(@PathVariable Long id, Model model) {
		model.addAttribute("matricula", matriculaServicio.obtenerMatricula(id));
		return "formularioMatricula";
	}

	/**
	 * Permite adicionar una nueva matricula ingresando la ruta:
	 * 'matricula/nuevo'
	 * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @return el nombre de la pagina donde se podra realizar la adicion
	 */
	@RequestMapping("/nuevo")
	public String nuevaMatricula(Model model) {
		return "formularioMatricula";
	}

	/**
	 * Recibe una matricula a traves de una peticion POST por la ruta
	 * '/matricula; y adiciona la matricula en la base de datos 
	 * @param matricula matricula que se recibe por la peticion POST
	 * @return el nombre de la pagina donde se redireccionara
	 */
	/*
	@PostMapping("/matricula")
	// Con @valid nos aseguramos que el contenido de la peticion sea valido
	public String guardarMatricula(@Valid @RequestBody Matricula matricula) {
		matriculaServicio.guardarMatricula(matricula);
		return "redirect:/matricula/" + matricula.getCodigoMatricula();
	}
	*/
	/**
	 * Permite eliminar una nueva matricula dado un id,ingresando a la ruta:
	 * 'matricula/eliminar/{id}'
	 * @param id corresponde al codigo de la matricula que se desea eliminar
	 * @return el nombre de la pagina donde se redireccionara al usuario despues de la eliminacion
	 */
	@RequestMapping("/eliminar/{id}")
	public String eliminarMatricula(@PathVariable Long id) {
		matriculaServicio.eliminarMatricula(id);
		return "redirect:/matriculas";
	}

	/**
	 * Consulta los ingresos registrados por grupos en un determinado rango de fechas por medio de
	 * una peticion GET; esta lista es desplegada en la ruta '/matriculas'
	 * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @return el nombre de la pagina donde se mostrara la lista de matriculas
	 */
	 @RequestMapping(value = "/ingresos", method = RequestMethod.GET)
	    public String reporteIngresos(Model model) {
	    	return "ReporteIngresos/GenerarReporte";
	    }
	 
	 /**
	  * Metodo que me verifica por cual periodo se desea realizar la consulta y me llama el metodo correspondiente del servicio.
	  * @param valores map que recibe del js con la informacion ingresada por el usuario
	  * @return un map con los valores que me retorna el metodo con la consulta realizada y se le envia al js. 
	  */
	 @PostMapping("/ingresos")
		// Con @valid nos aseguramos que el contenido de la peticion sea valido
		public @ResponseBody Map<String, Object> generarReporteIngresos(@RequestBody Map<String, String> valores) {
		 
			System.out.println(valores);
			String periodo = valores.get("periodo");
			Date fechaInicio = Date.valueOf(valores.get("fechaInicio"));
			Date fechaFin = Date.valueOf(valores.get("fechaFin"));
			LinkedList<Object[]> datos = new LinkedList<>();
			Map<String, Object> resultado = new HashMap<String, Object>();
			LinkedList<String> nombres = new LinkedList<>();
			
			switch (periodo) {
			
			case "ano":
				nombres.add("Año");
				nombres.add("Ingresos");
				System.out.println(fechaInicio);
				System.out.println(fechaFin);
				datos = matriculaServicio.listarPorAno(fechaInicio, fechaFin);
				break;
				
			case "ciclo":
				nombres.add("Año");
				nombres.add("Ciclo");
				nombres.add("Ingresos");
				datos = matriculaServicio.listarPorCiclo(fechaInicio, fechaFin);
				break;
				
			case "nivel":
				nombres.add("Año");
				nombres.add("Nivel");
				nombres.add("Ingresos");
				datos = matriculaServicio.listarPorNivel(fechaInicio, fechaFin);
				break;
				
			case "grupo":
				nombres.add("Año");
				nombres.add("Nivel");
				nombres.add("Ciclo");
				nombres.add("Grupo");
				nombres.add("Ingresos");
				datos = matriculaServicio.listarPorGrupo(fechaInicio, fechaFin);
				break;
				
			default:
				break;
			}	
			resultado.put("nombres", nombres);
			resultado.put("datos", datos);
			return resultado;
		}
	
	/**Lista todas los niveles presentes en la base de datos a traves de una
     * peticion GET; esta lista es desplegada en la ruta '/matricula'
     * @param model es el modelo que posteriormente se va a renderizar
     * con thymeleaf
     * @return el nombre de la pagina donde se mostrara la lista de preinscripciones
     */
    @GetMapping()
    public String matricula(Model model) {
    	LinkedList<Nivel> niveles = nivelServicio.listarNiveles();
    	model.addAttribute("niveles", niveles);
    	return "SubirMatriculas/SubirMatriculas";
    }
    
    /**Encargado de recibir las peticiones del frontend y procesarlas
     * @param informacion con los datos necesarios para realizar las matriculas y 
     * creacion de los estudiantes 
     * @return un map de objetos el retorno varia de acuerdo a diversos aspectos ya que este metodo procesa varias peticiones
     */
    @PostMapping()
    //public String guardarPreinscripcion(@RequestParam(value = "nivel", required=false) String nivel, @RequestParam(value = "fechaInicio", required=false) String fechaInicio, @RequestParam(value = "jornada", required=false) String jornada, @RequestParam(value = "grupo", required=false) String grupo) {
    public @ResponseBody Map<String, Object> guardarMatricula(@RequestBody Map<String, Object> informacion) {
    	String funcion = (String) informacion.get("funcion");
    	System.out.println(funcion);
    	if (funcion.equals("1")) {
    		String nivelCodigo = (String) informacion.get("nivel");
	    	Date fechaInicio = Date.valueOf((String) informacion.get("fechaInicio"));
	    	String jornada = (String) informacion.get("jornada");
	    	String numeroCadena = (String) informacion.get("numero");
	    	int numero = Integer.parseInt(numeroCadena);
	    	Grupo grupo = grupoServicio.obtenerGrupoPorNivelCodigoAndFechaInicioAndJornadaAndNumero(nivelCodigo, fechaInicio, jornada, numero);
			Map<String, Object> datos = new HashMap<String, Object>();
	    	if (grupo != null) {
				datos.put("idGrupo", grupo.getId());
				List<String> ids = (List<String>) informacion.get("ids");
				List<String> tarifas = (List<String>) informacion.get("tarifas");
				List<String> cedulaEstudiantesNoInscritos = new LinkedList<>();
				List<String> tarifaEstudiantesNoInscritos = new LinkedList<>();
				
				for(int i = 0; i< ids.size(); i++) {
					Estudiante estudiante = estudianteServicio.obtenerEstudiante(ids.get(i));
					if(estudiante == null) {
						cedulaEstudiantesNoInscritos.add(ids.get(i));
						tarifaEstudiantesNoInscritos.add(tarifas.get(i));
					}else {
						Matricula matricula = matriculaServicio.buscarPorEstudianteNumeroDocumentoAndGrupoId(estudiante.getNumeroDocumento(), grupo.getId());
						if(matricula == null) {
							matricula = new Matricula(Integer.parseInt(tarifas.get(i)) , estudiante, grupo);
						}else {
							matricula.setTarifa(Integer.parseInt(tarifas.get(i)));
						}
						matriculaServicio.guardarMatricula(matricula);
					}
				}
				datos.put("cedulaEstudiantesNoInscritos", cedulaEstudiantesNoInscritos);
				datos.put("tarifaEstudiantesNoInscritos", tarifaEstudiantesNoInscritos);
				datos.put("mensaje", "Si");
			}else {
				datos.put("mensaje", "No");
			}
	        return datos;
	    }else { 
	    	Map<String, Object> datos = new HashMap<String, Object>();
	    	if(funcion.equals("2")) {
	    		int idGrupo = Integer.parseInt((String)informacion.get("idGrupo"));
	    		List<Map<String, Object>> estudiantes = (List<Map<String, Object>>) informacion.get("estudiantes");
	    		for (Map<String, Object> map : estudiantes) {
	    			String numeroDocumento = (String) map.get("numeroDocumento");	
	    			String nombre1 = (String) map.get("nombre1");	
	    			String nombre2 = (String) map.get("nombre2");
	    			String apellido1 = (String) map.get("apellido1");
	    			String apellido2 = (String) map.get("apellido");
	    			Date fechaNacimiento = Date.valueOf((String) map.get("fechaNacimiento"));
	    			String telefono = (String) map.get("telefono");
	    			String email = (String) map.get("email");
	    			String estado_actual = "";
	    			LocalDate fecha = LocalDate.now();
	    			Date fechaIngreso = Date.valueOf( fecha.toString());
	    			String eps = (String) map.get("eps");
	    			Estudiante estudiante = new Estudiante(numeroDocumento, nombre1, nombre2, apellido1, apellido2, fechaNacimiento, telefono, email, estado_actual, fechaIngreso, eps);
	    			int tarifa = Integer.parseInt((String) map.get("tarifa"));
	    			Grupo grupo = grupoServicio.obtenerGrupoPorId(idGrupo);
	    			Matricula matricula = new Matricula(tarifa, estudiante, grupo);
	    			matriculaServicio.guardarMatricula(matricula);
				}
	    	}
	    	return datos;
	    }
    }
    
    @RequestMapping(value = "/Reporte/Estudiantes", method = RequestMethod.GET)
    public String generarReporte() {
    	return "ReporteEstudiante/reporteEstudiante";
    }
    @RequestMapping(value = "/Reporte/Estudiantes", method = RequestMethod.POST)
    public String reporteEstudiantes(Model model,@RequestParam("inputFechaI") Date fechaInicial,@RequestParam("inputFechaF") Date fechaFinal)
    {
    	LinkedList<Object[]> reporteEstudiantes = new LinkedList<>(); 
    	LinkedList<Object[]> consolidado = new LinkedList<>(); 
    	
    	reporteEstudiantes = matriculaServicio.reporteEstudiantes(fechaInicial,fechaFinal);
    	
    	if(reporteEstudiantes != null && consolidado != null)
    	{
    		model.addAttribute("reporteEstudiantes",reporteEstudiantes);
    		model.addAttribute("reporteEstudiantes",reporteEstudiantes);
    		model.addAttribute("reporteEstudiantes",reporteEstudiantes);
    	}
    	
    	return "redirect:/Reporte/Estudiantes";
    }
    
}
