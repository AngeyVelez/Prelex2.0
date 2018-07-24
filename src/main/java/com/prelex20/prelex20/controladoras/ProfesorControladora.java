package com.prelex20.prelex20.controladoras;

import java.util.LinkedList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prelex20.prelex20.entidades.Pagina;
import com.prelex20.prelex20.entidades.Profesor;
import com.prelex20.prelex20.servicios.ProfesorServicio;

/** Clase que funciona como controladora para la interaccion con la base de datos
 * @author Deivid,Sebastian
 * @version 2.0
 */
@Controller
@RequestMapping("/profesor")
public class ProfesorControladora {

    private static final int BUTTONS_TO_SHOW = 7;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 7;
    
	@Autowired
    private ProfesorServicio profesorServicio;

    /**Lista todas los profesores presentes en la base de datos a traves de una
     * peticion GET; esta lista es desplegada en la ruta '/profesores'
     * @param model es el modelo que posteriormente se va a renderizar
     * con thymeleaf
     * @return el nombre de la pagina donde se mostrara la lista de profesores
     
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String lista(Model model) {
        LinkedList<Profesor> profesores = profesorServicio.listarProfesores();
        model.addAttribute("profesores", profesores);
        return "Profesor/crudProfesores";
    }
    */
	
	@GetMapping(value = {"/", "/page"})
    public String showPersonsPage(@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page, Model model) {

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Profesor> profesor = profesorServicio.findAllPageable(PageRequest.of(evalPage, evalPageSize));
        Pagina pagina = new Pagina(profesor.getTotalPages(), profesor.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute("profesores", profesor);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pagina", pagina);
        model.addAttribute("nuevoProfesor", new Profesor());
        return "Profesor/crudProfesores";
    }
	
    /**Dado un id en la ruta 'profesor/{id}', muestra toda la informacion
     * del profesor correspondiente
     * @param model es el modelo que posteriormente se va a renderizar
     * con thymeleaf
     * @param id es el id del profesor que se desea visualizar
     * @return el nombre de la pagina donde se mostraran los datos
     */
    @RequestMapping("/{id}")
    public String mostrarProfesor(@PathVariable String id, Model model) {
        LinkedList<Profesor> profesores = new LinkedList<>();
        Profesor profesor = profesorServicio.obtenerProfesor(id);
        System.out.println();

        if (profesor !=null) {
            profesores.add(profesor);
        }
        model.addAttribute("profesores", profesores);

        return "profesores";
    }

    /**Dado un id en la ruta 'profesor/editar/{id}', muestra toda la informacion
     * del profesor correspondiente, pero con la opcion de poder modificar campos de
     * la misma y guardarlos
     * @param model es el modelo que posteriormente se va a renderizar
     * con thymeleaf
     * @param id es el id del profesor que se desea editar
     * @return el nombre de la pagina donde se podra realizar la edicion
     */
    @RequestMapping(value = "profesores/editar/{id}", method = RequestMethod.GET)
    public String mostrarEditar(@PathVariable String id, Model model) {

        model.addAttribute("profesor", profesorServicio.obtenerProfesor(id));
        return "Profesor/editarProfesor";
    }

    @RequestMapping(value = "profesores/editar/{id}", method = RequestMethod.POST)
    public String actualizar(@PathVariable String id,@RequestBody Profesor p,Model model) {
    	profesorServicio.actualizar(id,p);
    	model.addAttribute("mensaje", "El profesor "+p.getNombre1()+" "+p.getNombre2()+" ha sido actualizado");
        return "Profesor/editarProfesor";
    }

    /**Permite adicionar un nuevo profesor ingresando la ruta: 'profesor/nuevo'
     * @param model es el modelo que posteriormente se va a renderizar
     * con thymeleaf
     * @return el nombre de la pagina donde se podra realizar la adicion
     */
    @RequestMapping("/nuevo")
    public String nuevaProfesor(Model model) {
        return "formularioProfesor";
    }

    /**Recibe un profesor a traves de una peticion POST por la ruta
     * '/profesor; y adiciona el profesor en la base de datos
     * @param profesor profesor que se recibe por la peticion POST
     * @return el nombre de la pagina donde se redireccionara
     */
    @PostMapping("/")
    // Con @valid nos aseguramos que el contenido de la peticion sea valido
    public String guardarProfesores(@Valid Profesor profesor, BindingResult bidingResult, Model model) {
    	System.out.println("Entro post");
    	if(!bidingResult.hasErrors()) {
            profesorServicio.guardarProfesor(profesor);
            model.addAttribute("exito", 1);

        	return "Profesor/crudProfesores";
    	}
    	return "redirect:/profesor/#addEmployeeModal";
    }

    /**Permite eliminar un profesor dado un id,ingresando a la ruta: 'profesor/eliminar/{id}'
     * @param id corresponde al id del profesor que se desea eliminar
     * @return el nombre de la pagina donde se redireccionara al usuario despues de la eliminacion
     */
    @RequestMapping(value = "profesores/eliminar/{id}", method = RequestMethod.GET)
    public String eliminar(@PathVariable String id,Model model) {
    	if(!profesorServicio.validarDaclase(id)){
    		 profesorServicio.eliminarProfesor(id);
    	}

        return "redirect:/profesores";
    }
}
