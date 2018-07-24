package com.prelex20.prelex20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.prelex20.prelex20.servicios.*;

@SpringBootApplication
public class PrelexApplication{

//public class PrelexApplication extends org.springframework.boot.web.support.SpringBootServletInitializer {

	/*
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PrelexApplication.class);
    }
    */
    
	@Autowired
	MatriculaServicioImpl servicio;
	//PreinscripcionServicioImpl servicioPre;
	//EstudianteServicioImpl servicioE;
	//GrupoServicioImpl servicioGrupo;
	
	public static void main(String[] args) {
		SpringApplication.run(PrelexApplication.class, args);
	}
/*
	@Override
	public void run(String... arg0) throws Exception {
		LinkedList<Object[]> lista = servicio.listarPorGrupo();
		for(Object[] o: lista) {
			System.out.println(o[0]);
		}
		System.out.println("holaaaaaaaaaaaa");
		
		//System.out.println(lista.get(0).);
		
		//LinkedList<Matricula> lista1 = servicio.listarMatriculas();
		//System.out.println(lista1);
		//LinkedList<Estudiante> lista1 = servicioE.listarEstudiantes();
		//LinkedList<Grupo> lista = servicioGrupo.listarGrupos();
		//for(Grupo g : lista) {
		//	System.out.println(g.getNivel().getCodigo());
		//}
	}
*/
}
