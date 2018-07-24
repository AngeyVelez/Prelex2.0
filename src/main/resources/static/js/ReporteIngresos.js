var formulario = document.getElementById('formulario');
formulario.addEventListener('submit', function(e){
	e.preventDefault();
});

$(function(){
    $("#btnGenerar").click(enviarDatos);
});

function enviarDatos(){
	var fechaInicio = $("#fechaInicio").val();
	console.log(fechaInicio);
	var fechaFin = $("#fechaFin").val();
	console.log(typeof fechaFin);
	var periodo = $("#periodo").val();
	console.log(periodo);
	var valores = { "fechaInicio" : fechaInicio,
					"fechaFin" : fechaFin,
					"periodo" : periodo
				  };
    $.ajax({
        type: 'POST',
        url: '/matricula/ingresos',
        contentType: 'application/json',
        data: JSON.stringify(valores),
        success: function(data){
            // your code from above
        	console.log(data);
        	$("#contenido form").remove();
        	/*$.each(data["nombres"], function(){
        		var nombre = "";
        		var i = $(this);
        		$.each(i, function(){
        			//console.log($(this));
        			nombre += $(this)[0];
        		});
        		//console.log(nombre);
        		$("#thead tr").append('<th scope="col">'+nombre+'</th>');
        	});*/
        	
        	if (data["datos"].length > 0) {
        		
        		$("#contenido").append('<div class = "mt-5"><h4> Ingresos por '+periodo+' desde el: '+fechaInicio+' hasta el: '+fechaFin+'</h4></div>')
        		$("#contenido").append('<table class="mt-5 table table-hover"><thead id = "thead"><tr></tr></thead><tbody id ="tbody"></tbody></table>');

				var total = 0;
        		for(var i=0; i<data["nombres"].length; i++){
        			//console.log(data["nombres"][i]);
            		$("#thead tr").append('<th scope="col">'+data["nombres"][i]+'</th>');
        		}
        		$.each(data["datos"], function() {
        			// console.log($(this)["0"]);
					var algo = '';
					for (var i = 0; i < $(this).length; i++) {
						// console.log($(this)[i]);
						algo += '<td>' + $(this)[i] + '</td>'
						if(i == $(this).length-1){
							total += $(this)[i];
							//console.log($(this)[i]);
						}
					}
					$("#tbody").append('<tr>' + algo + '</tr>')
				});
        		$("#contenido").append('Total de ingresos: '+total);
			} else {
				$("#contenido").append('<div class = "mt-5 "><h3> No existen matriculas en el rango de fechas seleccionado </h3></div>')
			}
            console.log("Success");
            //window.location.href= '/matricula/ReporteIngresos';
        },
        error: function(xhr, textStatus, error){
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
        }
    });
}