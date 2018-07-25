package com.prelex20.prelex20.controladoras;

import java.sql.Date;
import java.util.LinkedList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.prelex20.prelex20.entidades.Grupo;
import com.prelex20.prelex20.entidades.Nivel;
import com.prelex20.prelex20.entidades.Pagina;
import com.prelex20.prelex20.entidades.Profesor;
import com.prelex20.prelex20.servicios.GrupoServicio;

/**
 * Controladora encargada de todas las iteraciones de los grupos en prelex
 * 
 * @author Sebastian
 * @version 2.0
 */

@Controller
@RequestMapping("/grupo")
public class GrupoControladora {

	private static final int BUTTONS_TO_SHOW = 7;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 7;
	private String mensaje = "";

	@Autowired
	private GrupoServicio grupoServicio;

	//
	@GetMapping(value = { "/", "/page" })
	public String showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Model model) {

		// Evaluate page size. If requested parameter is null, return initial
		// page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		// Evaluate page. If requested parameter is null or less than 0 (to
		// prevent exception), return initial size. Otherwise, return value of
		// param. decreased by 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Grupo> grupo = grupoServicio.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		Pagina pagina = new Pagina(grupo.getTotalPages(), grupo.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("mensaje", mensaje);
		model.addAttribute("grupos", grupo);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pagina", pagina);
		mensaje = "";
		return "Grupo/CrudGrupos";
	}

	/**
	 * Metodo encargado de mostrar el grupo seleccionado por el cliente
	 * 
	 * @param model modelo logico para enviar el grupo que selecciono el usuario.
	 * @param id    identificador unico del grupo.
	 * @return El PATH del archivo HTML que va mostrar
	 */

	@RequestMapping("/{id}")
	public String mostrarGrupo(@PathVariable int id, Model model) {
		LinkedList<Grupo> grupos = new LinkedList<>();
		Grupo grupo = grupoServicio.obtenerGrupoPorId(id);

		if (grupo != null) {
			grupos.add(grupo);
		}
		model.addAttribute("grupos", grupos);

		return "grupos";
	}

	/**
	 * Metodo encargado de editar el grupo seleccionado por el cliente
	 * 
	 * @param g parametros del grupo que va a editar
	 * @return una orden de redireccionamiento automatico a el mostrar grupos
	 */

	@GetMapping(value = "editarGrupo/{id}")
	public String mostrarEditar(@PathVariable int id, Model model) {
		Grupo grupo = grupoServicio.obtenerGrupoPorId(id);
		model.addAttribute("grupo", grupo);
		return "Grupo/formularioGrupo";
	}

	@RequestMapping("/nuevoGrupo/")
	public String nuevoGrupo(Model model) {
		model.addAttribute("grupo", new Grupo());
		return "Grupo/formularioGrupo";
	}

	@PostMapping(value = { "/nuevoGrupo/", "/editarGrupo/{id}" })
	// Con @valid nos aseguramos que el contenido de la peticion sea valido
	public String guardarGrupo(@Valid Grupo grupo, BindingResult bidingResult, Model model) {

		if (bidingResult.hasErrors()) {
			return "Grupo/formularioGrupo";
		}

		grupoServicio.guardarGrupo(grupo);
		mensaje = "El grupo se guardó correctamente";
		return "redirect:/grupo/";
	}

	/**
	 * Elimina los grupo por su id si ese grupo no tiene gente matriculada
	 * actualmente.
	 * 
	 * @param id es el identificador unico del grupo para referenciarlo.
	 * @return una orden de redireccionamiento automatico a el mostrar grupos.
	 */

	@GetMapping(value = "/eliminarGrupo/{id}")
	public String eliminarGrupo(@PathVariable int id, Model model) {
		mensaje = "El grupo no se pudo eliminar debido a que hay estudiantes matriculados";
		if (grupoServicio.validarEstMatriculadosxGrupo(id)) {
			grupoServicio.eliminarGrupo(id);
			mensaje = "El grupo se ha eliminado correctamente";
		}

		return "redirect:/grupo/";
	}
}
