/**
 * Created by andrea
 */
$(function(){
    $("#btnIncribir").click(enviarPreinscripcion)
});

function enviarPreinscripcion(){
    var tipoId = $('#inputTipoId').val();
    var numeroDocumento = $('#inputDocumento').val();
    var nombre1 = $('#inputNombre1').val();
    var nombre2 = $('#inputNombre2').val();
    var apellido1 = $('#inputApellido1').val();
    var apellido2 = $('#inputApellido2').val();
    var telefono = $('#inputTelefono').val();
    var datos = {
        "tipoId" : tipoId,
        "numeroDocumento" : numeroDocumento,
        "nombre1" : nombre1,
        "nombre2" : nombre2,
        "apellido1" : apellido1,
        "apellido2" : apellido2,
        "telefono" : telefono
    };


    $.ajax({
        type: 'POST',
        url: '/preinscripcion',
        contentType: 'application/json',
        data: JSON.stringify(datos),
        success: function(data){
            // your code from above
            console.log("Success")
            window.location.href= '/preinscripciones';
        },
        error: function(xhr, textStatus, error){
            console.log(xhr.statusText);
            console.log(textStatus);
            console.log(error);
        }
    });

}