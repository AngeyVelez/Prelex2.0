package com.prelex20.prelex20.controladoras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.prelex20.prelex20.entidades.Pagina;
import com.prelex20.prelex20.entidades.Preinscripcion;
import com.prelex20.prelex20.entidades.Profesor;
import com.prelex20.prelex20.servicios.PreinscripcionServicio;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Clase que funciona como controladora para la interacción con la base de datos
 * de preinscripciones
 * 
 * @author andrea,sebas
 * @version 1.2
 */
@Controller
@RequestMapping("/preinscripcion")
public class PreinscripcionControladora {

	private static final int BUTTONS_TO_SHOW = 7;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 7;
	private String mensaje = "";

	@Autowired
	private PreinscripcionServicio preinscripcionServicio;

	/**
	 * Lista todas las preinscripciones presentes en la base de datos a traves de
	 * una peticion GET; esta lista es desplegada en la ruta '/preinscripciones'
	 * 
	 * @param model
	 *            es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @return el nombre de la pagina donde se mostrara la lista de preinscripciones
	 */
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String lista(Model model) {
		LinkedList<Preinscripcion> preinscripciones = preinscripcionServicio.listarPreinscripciones();
		for (Preinscripcion preins : preinscripciones) {
			System.out.println(preins.getNumeroDocumento());
		}

		model.addAttribute("preinscripciones", preinscripciones);
		return "Preinscripcion/preinscripciones";

	}

	/**
	 * metodo encargado de redireccionar al template de preinscripcion
	 * 
	 * @param model
	 *            es el modelo el cual posteriormente se va a renderizar
	 * @return el nombre de la pagina la cual se mostrara
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("preinscripcion", new Preinscripcion());
		return "Preinscripcion/realizarPreinscripcion";
	}

	/**
	 * muestra toda la vista HTML de administrarPreinscripciones y envia con un
	 * metodo GET los objetos, Listas y variables agregadas al modelo que se
	 * solicitan
	 * 
	 * @param model
	 *            es el modelo logico de la interfaz con la que se relaciona la
	 *            controladora
	 * @return String con la ruta del archivo HTTP que va a mostrar en la web
	 */
	@RequestMapping(value = "/AdministrarPreinscripciones", method = RequestMethod.GET)
	public String mostrarAdministrarPreinscripcion(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Model model) {

		// Evaluate page size. If requested parameter is null, return initial
		// page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		// Evaluate page. If requested parameter is null or less than 0 (to
		// prevent exception), return initial size. Otherwise, return value of
		// param. decreased by 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Preinscripcion> paginas = preinscripcionServicio.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		Pagina pagina = new Pagina(paginas.getTotalPages(), paginas.getNumber(), BUTTONS_TO_SHOW);

		LinkedList<String> idiomas = preinscripcionServicio.obtenerIdiomas();
		LinkedList<String> tipos = preinscripcionServicio.obtenerTipos();
		LinkedList<Object[]> totalesxIdioma = preinscripcionServicio.obtenerTotalesxIdioma();
		int totalPreinscritos = preinscripcionServicio.totalPreinscripciones();
		LinkedList<LinkedList<String>> reporteIdiomaTipos = new LinkedList<>();

		for (String tipo : tipos) {
			LinkedList<String> fila = new LinkedList<>();
			fila.add(tipo);
			for (String idioma : idiomas) {
				String cantidad = preinscripcionServicio.obtenerReporteIdiomaTipo(idioma, tipo);
				System.out.println(cantidad + " " + idioma + " " + tipo);
				if (cantidad == null) {

					cantidad = "0";

				}
				fila.add(cantidad);
			}
			reporteIdiomaTipos.add(fila);
		}

		model.addAttribute("totalPreinscripciones", totalPreinscritos);
		model.addAttribute("idiomas", idiomas);
		model.addAttribute("tipos", tipos);
		model.addAttribute("totalesxIdioma", totalesxIdioma);
		model.addAttribute("reporteIdiomaTipos", reporteIdiomaTipos);
		model.addAttribute("mensaje", mensaje);
        model.addAttribute("paginas", paginas);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pagina", pagina);
        mensaje = "";

		return "Preinscripcion/administrarPreinscripcion";
	}

	/**
	 * Dado un id en la ruta 'preinscripcion/{id}', muestra toda la informacion de
	 * la preinscripcion correspondiente
	 * 
	 * @param model
	 *            es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @param id
	 *            es el id de la preinscripcion que se desea visualizar
	 * @return el nombre de la pagina donde se mostraran los datos
	 */
	@RequestMapping("/{id}")
	public String mostrarPreinscripcion(@PathVariable int id, Model model) {
		LinkedList<Preinscripcion> preinscripciones = new LinkedList<>();
		Preinscripcion preinscripcion = preinscripcionServicio.obtenerPreinscripcion(id);

		if (preinscripcion != null) {
			preinscripciones.add(preinscripcion);
		}
		model.addAttribute("preinscripciones", preinscripciones);

		return "Preinscripcion/preinscripciones";
	}

	/**
	 * Dado un id en la ruta 'preinscripcion/editar/{id}', muestra toda la
	 * informacion de la preinscripcion correspondiente, pero con la opcion de poder
	 * modificar campos de la misma y guardarlos
	 * 
	 * @param model
	 *            es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @param id
	 *            es el id de la preinscripcion que se desea editar
	 * @return el nombre de la pagina donde se podra realizar la edicion
	 */
	@RequestMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		model.addAttribute("preinscripcion", preinscripcionServicio.obtenerPreinscripcion(id));
		return "Preinscripcion/formularioPreinscripcion";
	}

	/**
	 * Permite adicionar una nueva preinscripcion ingresando la ruta:
	 * 'preinscripcion/nuevo'
	 * 
	 * @param model
	 *            es el modelo que posteriormente se va a renderizar con thymeleaf
	 * @return el nombre de la pagina donde se podra realizar la adicion
	 */
	@RequestMapping("/nuevo")
	public String nuevaPreinscripcion(Model model) {
		return "Preinscripcion/formularioPreinscripcion";
	}

	/**
	 * Recibe una preinscripcion a traves de una peticion POST por la ruta
	 * '/preinscripcion; y adiciona la preinscripcion en la base de datos
	 * 
	 * @param p
	 *            preinscripcion que se recibe por la peticion POST
	 * @return el nombre de la pagina donde se redireccionara
	 */
	@PostMapping()
	// Con @valid nos aseguramos que el contenido de la peticion sea valido
	public String guardarPreinscripcion(@Valid Preinscripcion preinscripcion, BindingResult bindingResult,
			Model model) {
		// System.out.println("siiiii");
		if (bindingResult.hasErrors()) {
			return "Preinscripcion/realizarPreinscripcion";
		}
		System.out.println(preinscripcion.getIdioma());
		LocalDate fechaActual = LocalDate.now();
		Date fecha = Date.valueOf(fechaActual.toString());
		preinscripcion.setFecha(fecha);
		preinscripcionServicio.guardarPreinscripcion(preinscripcion);
		model.addAttribute("exito", "La preinscripción se ha hecho correctamente");
		model.addAttribute("preinscripcion", new Preinscripcion());
		return "Preinscripcion/realizarPreinscripcion";
	}

	/**
	 * Permite eliminar una nueva preinscripcion dado un id
	 * 
	 * @param id
	 *            corresponde al id de la preinscripcion que se desea eliminar
	 * @return el nombre de la pagina donde se redireccionara al usuario después de
	 *         la eliminacion
	 */
	@GetMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
    	mensaje = "La preinscripcion se  ha eliminado correctamente";
		preinscripcionServicio.eliminarPreinscripcion(id);
		return "redirect:/preinscripcion/AdministrarPreinscripciones";
	}

}
