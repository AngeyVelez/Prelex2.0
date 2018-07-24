
function crearFila()
{
	var tbody = document.getElementById("tbody");
	
	var tr = document.createElement('tr');
	var td = document.createElement('td');	
	var input = document.createElement('input');
	
	input.setAttribute('pattern','Ciclo [1-4]');
	input.setAttribute('class','form-control');
	input.setAttribute('placeholder','Ciclo');
	input.setAttribute('aria-label','Ciclo');
	input.setAttribute('aria-describedby','basic-addon1');
	
	td.appendChild(input);
	tr.appendChild(td);
	
	var td2 = document.createElement('td');	
	var input2 = document.createElement('input');
	
	input.setAttribute('pattern','FIN DE SEMANA|NOCTURNA');
	input2.setAttribute('class','form-control');
	input2.setAttribute('placeholder','Jornada');
	input2.setAttribute('aria-label','Jornada');
	input2.setAttribute('aria-describedby','basic-addon1');
	
	td2.appendChild(input2);
	tr.appendChild(td2);
	

	var td3 = document.createElement('td');	
	var input3 = document.createElement('input');
	
	input.setAttribute('pattern','[1-9]');
	input3.setAttribute('class','form-control');
	input3.setAttribute('placeholder','Numero');
	input3.setAttribute('aria-label','Numero');
	input3.setAttribute('aria-describedby','basic-addon1');
	
	td3.appendChild(input3);
	tr.appendChild(td3);
	
	var td4 = document.createElement('td');	
	var input4 = document.createElement('input');
	
	input.setAttribute('pattern','([1-9]|[A-Z])+');
	input4.setAttribute('class','form-control');
	input4.setAttribute('placeholder','Nivel');
	input4.setAttribute('aria-label','Nivel');
	input4.setAttribute('aria-describedby','basic-addon1');
	
	td4.appendChild(input4);
	tr.appendChild(td4);
	
	var td5 = document.createElement('td');	
	var input5 = document.createElement('input');
	
	input.setAttribute('pattern','[A-za-z]');
	input5.setAttribute('class','form-control');
	input5.setAttribute('placeholder','Profesor');
	input5.setAttribute('aria-label','Profesor');
	input5.setAttribute('aria-describedby','basic-addon1');
	
	td5.appendChild(input5);
	tr.appendChild(td5);
	
	var td6 = document.createElement('td');	
	var input6 = document.createElement('input');
	
	input.setAttribute('pattern','.{1,}');
	input6.setAttribute('type','Date');
	input6.setAttribute('class','form-control');
	input6.setAttribute('placeholder','Fecha de inicio');
	input6.setAttribute('aria-label','Fecha de inicio');
	input6.setAttribute('aria-describedby','basic-addon1');
	
	td6.appendChild(input6);
	tr.appendChild(td6);
	
	var td7 = document.createElement('td');	
	var input7 = document.createElement('input');
	
	input.setAttribute('pattern','.{1,}');
	input7.setAttribute('type','Date');
	input7.setAttribute('class','form-control');
	input7.setAttribute('placeholder','Fecha de fin');
	input7.setAttribute('aria-label','Fecha de fin');
	input7.setAttribute('aria-describedby','basic-addon1');
	
	td7.appendChild(input7);
	tr.appendChild(td7);
	
	tbody.appendChild(tr);
	
	 	
}

function eliminarFila()
{
	var tbody = document.getElementById("tbody");
	
	if(tbody.lastChild != null)
	{
		tbody.deleteRow(tbody.lastChild);
	}
}