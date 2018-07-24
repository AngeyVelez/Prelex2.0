package com.prelex20.prelex20.controladoras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prelex20.prelex20.entidades.Grupo;
import com.prelex20.prelex20.servicios.GrupoServicio;

/** Controladora encargada de todas las iteraciones de los grupos en prelex
 * @author Sebastian
 * @version 2.0
 */

@Controller
public class GrupoControladora {
	@Autowired
	private GrupoServicio grupoServicio;
	
	/** Lista los grupos y los muestra si no hay muestra un mensaje
	 * @param model modelo logico de la vista para mandar los objetos que necesita
	 * @return la ruta del archivo HTML con la interfaz.
	 */
	
	@RequestMapping(value = "/grupos", method = RequestMethod.GET)
	public String listarGrupos(Model model)
	{
		
		if(grupoServicio.listarGrupos() != null){
			model.addAttribute("grupos", grupoServicio.listarGrupos());
		}
		else {
			model.addAttribute("Lo sentimos no hay grupos registrados");
		}
		return "Grupo/mostrarGrupos";
	}
	
	/** obtiene los grupos para agregarlos en la base de datos 
	 * @param grupos Lista de grupos que obtiene de la interfaz que debe agregar.
	 */
	
	/**
	@RequestMapping(value = "/grupos", method = RequestMethod.POST)
	public void guardarGrupos(@RequestBody LinkedList<Object> grupos,Model model){
		 LinkedList<Grupo> gruposperdidos = new LinkedList<>(); 	
	 
		 for (Object grupo : grupos) {
			 if(!grupoServicio.validarGrupo()) {
				 int incrementalIdGrupo	= grupoServicio.obtenerMaximoGrupoId() + 1;
				 grupoServicio.guardarGrupo(incrementalIdGrupo,grupo);
			 }
			 else {
				model.addAttribute("mensaje","Los siguientes grupos no fueron agregados a PRELEX por que ya existen"); 
				model.addAttribute("gruposperdidos",gruposperdidos);
			 }
		}	
	}
	*/
	
	/** Elimina los grupo por su id si ese grupo no tiene gente matriculada actualmente.
	 * @param id es el identificador unico del grupo para referenciarlo.
	 * @return una orden de redireccionamiento automatico a el mostrar grupos.
	 */
	@RequestMapping(value = "/grupos/eliminar/{id}", method = RequestMethod.GET)
	public String eliminarGrupo(@PathVariable() int id) 
	{
		
		if(!grupoServicio.validarEstMatriculadosxGrupo(id))
		{
			grupoServicio.eliminarGrupo(id);
		}
		//Falta el mensaje de que hizo o no los cambios 
		return "redirect:/grupos"; 	
	}
	/** Metodo encargado de mostrar el grupo seleccionado por el cliente
	 * @param model modelo logico para enviar el grupo que selecciono el usuario.
	 * @param id identificador unico del grupo.
	 * @return El PATH del archivo HTML que va mostrar
	 */
	@RequestMapping(value = "/grupos/editar/{id}", method = RequestMethod.GET)
	public String mostrarGrupo(Model model, @PathVariable() int id)
	{
		Grupo g = grupoServicio.obtenerGrupoPorId(id);
		model.addAttribute("grupo", g);
		return "Grupo/editarGrupo";
	}

	
	/** Metodo encargado de editar el grupo seleccionado por el cliente
	 * @param g parametros del grupo que va a editar
	 * @return una orden de redireccionamiento automatico a el mostrar grupos
	 */
	/*
	@RequestMapping(value = "/grupos/editar/{id}", method = RequestMethod.POST)
	public void editarGrupo(@PathVariable() int id, @Valid @RequestBody Grupo g,Model model) {
		grupoServicio.actualizarGrupo(id,g);
		
		model.addAttribute("mensaje","El grupo ha sido actualizado correctamente");
	}
	
	*/
}
