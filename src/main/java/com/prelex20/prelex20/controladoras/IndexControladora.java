package com.prelex20.prelex20.controladoras;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**Clase que funciona como controladora para la pagina index
 * @author andrea
 * @version 1.0
 */
@Controller
public class IndexControladora {

	/**Permite redireccionar a la pagina index dada la ruta '/'
	 * @param model es el modelo que posteriormente se va a renderizar
	 * con thymeleaf
	 * @return el nombre de la pagina que se desea abrir
	 */
	@RequestMapping("/")
	public String index(Model model)
	{
		model.addAttribute("mensaje", "Bienvenido al sistema de informacion de PRELEX");
		return "index";
	}

}