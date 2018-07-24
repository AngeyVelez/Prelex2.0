package com.prelex20.prelex20.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.prelex20.prelex20.entidades.UsuarioFormulario;
import com.prelex20.prelex20.servicios.UsuarioServicio;

@Component
public class ValidarUsuario implements Validator{
	@Autowired
    private UsuarioServicio usuarioServicio;

    @Override
    public boolean supports(Class<?> aClass) {
        return UsuarioFormulario.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UsuarioFormulario usuarioFormulario = (UsuarioFormulario) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (usuarioFormulario.getUsername().length() < 6 || usuarioFormulario.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        
        if (usuarioServicio.findByUsername(usuarioFormulario.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (usuarioFormulario.getPassword().length() < 8 || usuarioFormulario.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!usuarioFormulario.getPasswordConfirm().equals(usuarioFormulario.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
