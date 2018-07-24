package com.prelex20.prelex20.controladoras;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prelex20.prelex20.entidades.Rol;
import com.prelex20.prelex20.entidades.Usuario;
import com.prelex20.prelex20.entidades.UsuarioFormulario;
import com.prelex20.prelex20.servicios.RolServicio;
import com.prelex20.prelex20.servicios.SeguridadServicio;
import com.prelex20.prelex20.servicios.UsuarioServicio;
import com.prelex20.prelex20.validator.ValidarUsuario;

@Controller
public class UsuarioControladora {
	 	@Autowired
	    private UsuarioServicio usuarioServicio;

	    @Autowired
	    private SeguridadServicio seguridadServicio;

	    @Autowired
	    private ValidarUsuario validarUsuario;
	    
	    @Autowired
	    private RolServicio roleService;

	    @RequestMapping(value = "/registration", method = RequestMethod.GET)
	    public String registration(Model model) {
	        model.addAttribute("userForm", new UsuarioFormulario());
	        
	        LinkedList<Rol> rols = roleService.listar();
	        System.out.println(rols);
	        model.addAttribute("roles", rols);

	        return "Login/registration";
	    }

	    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	    public String registration(@ModelAttribute("userForm") UsuarioFormulario usuarioFormulario, BindingResult bindingResult, Model model) {
	    	/*String username = valores.get("username");
	        String password = valores.get("password");
	        String passwordConfirm = valores.get("passwordConfirm");
	        String roles = valores.get("roles");
	        roles = roles.toUpperCase();
	    	Usuario userForm = new Usuario(username, password, passwordConfirm);
	    	*/
	    	validarUsuario.validate(usuarioFormulario, bindingResult);

	        if (bindingResult.hasErrors()) {
	        	LinkedList<Rol> rols = roleService.listar();
		        model.addAttribute("roles", rols);
	            return "Login/registration";
	        }
	        
	        Usuario usuario = new Usuario(usuarioFormulario.getUsername(), usuarioFormulario.getPassword(), usuarioFormulario.getPasswordConfirm());
	        usuarioServicio.save(usuario, usuarioFormulario.getRoles());

	        seguridadServicio.autologin(usuario.getUsername(), usuario.getPasswordConfirm());

	        return "redirect:/preinscripcion";
	    }

	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(Model model, String error, String logout) {
	        if (error != null)
	            model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");
	        
	        return "Login/login";
	    }

	    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	    public String welcome(Model model) {
	        return "welcome";
	    }
}
