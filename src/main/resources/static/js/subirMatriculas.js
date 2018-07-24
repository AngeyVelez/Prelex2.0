/**clase la cual se encarga de la comunicacion entre las vistas y el servidor
 * @author deivid
 * @version 1.0
 */
$(function(){
    $("#btnRegistrar").click(enviarMatriculas);
    $("#btnContinuar").click(continuarMatriculas);
    $("#btnAdicionar").click(agregarCampo);
    $("#btnQuitar").click(quitarCampo);
});

function agregarCampo(){

	var ids = $("#divIds ul");
	ids.append('<li class="list-group-item"><label for="id" class="col-md-1"> ID: </label> <input type="text" class="col-md-4" name="id"/><label for="id" class="col-md-2"> Tarifa: </label><input type="text" class="col-md-4" name="tarifa"/> </li>');
}

function quitarCampo(){

	var ids = $("#divIds ul li:last");
	ids.remove();
}

function enviarMatriculas(){
    var nivel = $('#nivel').val();
    var fechaInicio = $('#fechaInicio').val();
	//console.log(nivel);
	//console.log(typeof fechaInicio);
    var jornada = $('#jornada').val();
    var numero = $('#numero').val();
    var ids = [];
    var tarifas = [];
    $('#divIds ul li input[name="id"]').each(function (){
    	ids.push($(this).val());
    	//console.log($(this).val());
    	//return value;
    });
    $('#divIds ul li input[name="tarifa"]').each(function (){
    	tarifas.push($(this).val());
    	//console.log($(this).val());
    	//return value;
    });
    var datos = {
    	"funcion" : "1",
        "nivel" : nivel,
        "fechaInicio" : fechaInicio,
        "jornada" : jornada,
        "numero" : numero,
        "ids" : ids,
        "tarifas" : tarifas
    };

    $.ajax({
        type: 'POST',
        url: '/matricula',
        contentType: 'application/json',
        data: JSON.stringify(datos),
        success: function(data){
            // your code from above
        	var estado = data['mensaje'];
            console.log(data)
            if (estado == 'Si') {
            	if(data["cedulaEstudiantesNoInscritos"].length > 0){
            		var idGrupo = data["idGrupo"];
            		$('#grupo').append('<div class="col-md-4 mt-3"><label> Id grupo </label><input type="number" class="form-control" id="idGrupo" value = "'+idGrupo+'" disabled = ""/></div>')
	            	$("#divIds ul").remove();
	            	$("#divIds li").remove();
	            	$("#divIds").attr('class', 'row mt-5')
	            	$("#divIds").append('<table class="table table-striped col-md-12"> <thead> <tr> <th scope="col" class = "col-md-1">Numero de documento</th> <th scope="col" class = "col-md-1">Primer nombre</th> <th scope="col" class = "col-md-1">Segundo nombre</th> <th scope="col" class = "col-md-1">Primer apellido</th> <th scope="col" class = "col-md-1">Segundo apellido</th> <th scope="col" class = "col-md-1">Fecha de nacimiento</th> <th scope="col" class = "col-md-2">Email</th> <th scope="col" class = "col-md-1">Telefono</th><th scope="col" class = "col-md-1">Eps</th><th scope="col" class = "col-md-1">tarifa</th> </tr> </thead> <tbody id ="tbody"> </tbody> </table>')
	                $("#btns a").remove();
	                $("#btns").append('<a onclick="continuarMatriculas()" class="btn btn-primary btn-lg active mt-5 col-md-5 row justify-content-center" role="button" aria-pressed="true" id="btnContinuar"> Continuar Registro </a>');
	                $("#grupo div select").prop('disabled', true);
	                $("#grupo div input").prop('disabled', true);
	                for (var i = 0; i < data["cedulaEstudiantesNoInscritos"].length; i++) {
						var cedulas = data["cedulaEstudiantesNoInscritos"][i];
						var tarifas = data["tarifaEstudiantesNoInscritos"][i];
	                	$("#tbody").append('<tr><td><input type = "text" class = "col-md-12" value = "'+cedulas+'" name = "numeroDocumento" disabled></input></td> <td><input type = "text" class = "col-md-12" name = "nombre1"></input></td> <td><input type = "text" class = "col-md-12" name ="nombre2"></input></td> <td><input type = "text" class = "col-md-12" name ="apellido1"></input></td> <td><input type = "text" class = "col-md-12" name = "apellido2"></input></td> <td><input type = "date" class = "form-control col-md-12" name = "fechaNacimiento"></input></td> <td><input type = "text" class = "col-md-12" name = "email"></input></td> <td><input type = "text" class = "col-md-12" name = "telefono"></input></td><td><input type = "text" class = "col-md-12" name = "eps"></input></td><td><input type = "text" class = "col-md-12" value = "'+tarifas+'" name = "tarifa"></input></td> </tr>')
					}
            	}else{
            		alert("Estudiantes Matriculados");
    	            window.location.href= '/matricula';
            	}
			}else{
				console.log("Grupo inexistente");
				alert("Grupo inexistente");
			}                        
        },
        error: function(xhr, textStatus, error){
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
        }
    });
}

function continuarMatriculas(){
    var idGrupo = $('#idGrupo').val();
    var estudiantes = [];
    console.log("sii");
	var filas = $('#tbody tr');
    $.each(filas, function(index){
        var dato = {};
    	//console.log($(this).find("input"));
    	var columnas = $(this).find("input");
    	$.each(columnas, function(){
    		dato[String($(this).attr("name"))] = $(this).val();
    		//console.log($(this).val());
    	});
    	//console.log(index);
    	estudiantes.push(dato);
    });
    var datos = {
    		"idGrupo" : idGrupo,
    		"estudiantes" : estudiantes,
    		"funcion" : "2"
    };
    console.log(datos);
    $.ajax({
    	type : 'POST',
    	url : '/matricula',
        contentType: 'application/json',
    	data : JSON.stringify(datos),
    	success : function(data){
    		console.log("exito");
    		alert("Estudiantes Matriculados");
            window.location.href= '/matricula';
    	},
    	error : function(xhr, textStatus, error){
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
        }
    });
}