package com.prelex20.prelex20.controladoras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.prelex20.prelex20.entidades.Estudiante;
import com.prelex20.prelex20.servicios.EstudianteServicio;

import javax.validation.Valid;
import java.util.LinkedList;

/** Controladora para la comunicacion con la base de datos
 * @author Angey
 * @version 1.0
 */
@Controller
@RequestMapping("/estudiantes")
public class EstudianteControladora {
	
	@Autowired
    private EstudianteServicio estudianteServicio;

    /**Listar todas los estudiantes de la base de datos a traves de una
     * peticion GET; esta lista es desplegada en la ruta '/estudiantes'
     * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
     * @return el nombre de la pagina donde se mostrara la lista de estudiantes
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String lista(Model model) {
        LinkedList<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante.getNumeroDocumento());
        }

        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes";
    }

    /**Dado un id en la ruta 'estudiante/{id}', muestra toda la informacion del estudiante correspondiente
     * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
     * @param id es el id del estudiante que se desea visualizar
     * @return el nombre de la pagina donde se mostraran los datos
     */
    @RequestMapping("/{id}")
    public String mostrarEstudiante(@PathVariable String id, Model model) {
        LinkedList<Estudiante> estudiantes = new LinkedList<>();
        Estudiante estudiante = estudianteServicio.obtenerEstudiante(id);

        if (estudiante !=null) {
            estudiantes.add(estudiante);
        }
        model.addAttribute("estudiantes", estudiantes);

        return "estudiantes";
    }

    /**Dado un id en la ruta 'estudiante/editar/{id}', muestra toda la informacion
     * del estudiante correspondiente, pero con la opcion de modificar campos de la misma y guardarlos
     * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
     * @param id es el id del estudiante que se desea editar
     * @return el nombre de la pagina donde se podra realizar la edicion
     */
    @RequestMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        model.addAttribute("estudiante", estudianteServicio.obtenerEstudiante(id));
        return "formularioEstudiante";
    }

    /**Permite adicionar un estudiante nuevo ingresando la ruta: 'estudiante/nuevo'
     * @param model es el modelo que posteriormente se va a renderizar con thymeleaf
     * @return el nombre de la pagina donde se podra realizar la adicion
     */
    @RequestMapping("/nuevo")
    public String nuevoEstudiante(Model model) {
        return "formularioEstudiante";
    }

    /**Recibe un estudiante a traves de una peticion POST por la ruta
     * '/estudiante; y adiciona e estudiante en la base de datos
     * @param estudiante estudiante que se recibe por la peticion POST
     * @return el nombre de la pagina donde se redireccionara
     */
    @PostMapping("/")
    // Con @valid nos aseguramos que el contenido de la peticion sea valido
    public String guardarEstudiante(@Valid @RequestBody Estudiante estudiante) {
        estudianteServicio.guardarEstudiante(estudiante);
        return "redirect:/estudiante/"+ estudiante.getNumeroDocumento();
    }

    /**Permite eliminar un estudiante dado un id,ingresando a la ruta: 'estudiante/eliminar/{id}'
     * @param id corresponde al id del estudiante que se desea eliminar
     * @return el nombre de la pagina donde se redireccionara al usuario despues de la eliminacion
     */
    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
    	estudianteServicio.eliminarEstudiante(id);
        return "redirect:/estudiantes";
    }
}
