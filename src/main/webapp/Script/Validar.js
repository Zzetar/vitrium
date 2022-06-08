function validarString(cadena, min, max) {
	var res = false;

	if (min == 0 && max != null) {
		if ((isNaN(cadena) && cadena.length <= max) || cadena == "")
			res = true;
	}

	if (min > 0 && max == null) {
		if (isNaN(cadena) && cadena.length >= min)
			res = true;
	}

	if (min == 0 && max == null) {
		if (isNaN(cadena) || cadena == "")
			res = true;
	}

	if (min > 0 && max != null) {
		if (isNaN(cadena) && cadena.length >= min && cadena.length <= max)
			res = true;
	}

	return res;
}

function validarNumero(cadena,min,max){
	if(!isNaN(cadena) && cadena.length >= min && cadena.length <= max){
		return true;
	}else{
		
		return false;
	}
}

function validarEmail(elemento) {
	
	var dato = elemento.value;
	var expresion = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	if (!expresion.test(dato)) {
		return false;
	} else {
		return true;	
	}

}

function validarCodigo(elemento) {
	
	var dato = elemento.value;
	var expresion = /^([a-zA-Z0-9_.-])+$/;
	if (!expresion.test(dato)) {
		return false;
	} else {
		return true;	
	}

}

function validarUsuarioInside(elemento,min,max,capaError,errorUsuario) {
    //para los campos tipo string del formulario
    if(!validarString(elemento.value,min,max)){
        elemento.style.backgroundColor="lightyellow";
        elemento.style.borderColor="red";
        elemento.classList.add("is-invalid");
        elemento.classList.remove("is-valid");
        mostrar(capaError);
    }else{
        elemento.style.backgroundColor="";
        elemento.style.borderColor="";
        elemento.classList.add("is-valid");
        elemento.classList.remove("is-invalid");
        noMostrar(capaError);
        
        var usuario;
        const xhttp = new XMLHttpRequest();
        xhttp.open("GET", "getUsuario.php?usuario="+elemento.value);
        xhttp.send();
        xhttp.onload = function() {
            usuario = this.responseText;
            if(usuario!=null&&usuario!=""){
                mostrar(errorUsuario);
            }else{
                console.log("no existe");
                noMostrar(errorUsuario);

            }
        }

    }      
    if (elemento.value==""){
        elemento.style.backgroundColor="";
        elemento.style.borderColor="";
        elemento.classList.remove("is-invalid");
        noMostrar(capaError);
    } 
  
}


function validarISBNInside(elemento,min,max,capaError,errorISBN) {
    //para los campos tipo string del formulario
    if(!validarString(elemento.value,min,max)){
        elemento.style.backgroundColor="lightyellow";
        elemento.style.borderColor="red";
        elemento.classList.add("is-invalid");
        elemento.classList.remove("is-valid");
        mostrar(capaError);
    }else{
        elemento.style.backgroundColor="";
        elemento.style.borderColor="";
        elemento.classList.add("is-valid");
        elemento.classList.remove("is-invalid");
        noMostrar(capaError);
        
        var usuario;
        const xhttp = new XMLHttpRequest();
        xhttp.open("GET", "getIsbn.php?usuario="+elemento.value);
        xhttp.send();
        xhttp.onload = function() {
            usuario = this.responseText;
            if(usuario!=null&&usuario!=""){
                mostrar(errorISBN);
            }else{
                console.log("no existe");
                noMostrar(errorISBN);

            }
        }

    }      
    if (elemento.value==""){
        elemento.style.backgroundColor="";
        elemento.style.borderColor="";
        elemento.classList.remove("is-invalid");
        noMostrar(capaError);
    } 
  
}

function codigoProvincia(cp) {
	if (cp == 01) {
		document.getElementById("provincia").innerHTML = "Álava";
	}

	if (cp == 02) {
		document.getElementById("provincia").innerHTML = "Albacete";
	}

	if (cp == 03) {
		document.getElementById("provincia").innerHTML = "Alicante";
	}

	if (cp == 04) {
		document.getElementById("provincia").innerHTML = "Almeria";
	}

	if (cp == 05) {
		document.getElementById("provincia").innerHTML = "Ávila";
	}

	if (cp == 06) {
		document.getElementById("provincia").innerHTML = "Badajoz";
	}

	if (cp == 07) {
		document.getElementById("provincia").innerHTML = "Islas Baleares";
	}

	if (cp == 08) {
		document.getElementById("provincia").innerHTML = "Barcelona";
	}

	if (cp == 09) {
		document.getElementById("provincia").innerHTML = "Burgos";
	}

	if (cp == 10) {
		document.getElementById("provincia").innerHTML = "Cáceres";
	}

	if (cp == 11) {
		document.getElementById("provincia").innerHTML = "Cádiz";
	}

	if (cp == 12) {
		document.getElementById("provincia").innerHTML = "Castellón";
	}

	if (cp == 13) {
		document.getElementById("provincia").innerHTML = "Ciudad Real";
	}

	if (cp == 14) {
		document.getElementById("provincia").innerHTML = "Córdoba";
	}

	if (cp == 15) {
		document.getElementById("provincia").innerHTML = "La Coruña";
	}

	if (cp == 16) {
		document.getElementById("provincia").innerHTML = "Cuenca";
	}

	if (cp == 17) {
		document.getElementById("provincia").innerHTML = "Gerona";
	}

	if (cp == 18) {
		document.getElementById("provincia").innerHTML = "Granada";
	}

	if (cp == 19) {
		document.getElementById("provincia").innerHTML = "Guadalajara";
	}

	if (cp == 20) {
		document.getElementById("provincia").innerHTML = "Guipúzcoa";
	}

	if (cp == 21) {
		document.getElementById("provincia").innerHTML = "Huelva";
	}

	if (cp == 22) {
		document.getElementById("provincia").innerHTML = "Huesca";
	}

	if (cp == 23) {
		document.getElementById("provincia").innerHTML = "Jaén";
	}

	if (cp == 24) {
		document.getElementById("provincia").innerHTML = "León";
	}

	if (cp == 25) {
		document.getElementById("provincia").innerHTML = "Lérida";
	}

	if (cp == 26) {
		document.getElementById("provincia").innerHTML = "La Rioja";
	}

	if (cp == 27) {
		document.getElementById("provincia").innerHTML = "Lugo";
	}

	if (cp == 28) {
		document.getElementById("provincia").innerHTML = "Madrid";
	}

	if (cp == 29) {
		document.getElementById("provincia").innerHTML = "Málaga";
	}

	if (cp == 30) {
		document.getElementById("provincia").innerHTML = "Murcia";
	}

	if (cp == 31) {
		document.getElementById("provincia").innerHTML = "Navarra";
	}

	if (cp == 32) {
		document.getElementById("provincia").innerHTML = "Orense";
	}

	if (cp == 33) {
		document.getElementById("provincia").innerHTML = "Asturias";
	}

	if (cp == 34) {
		document.getElementById("provincia").innerHTML = "Palencia";
	}

	if (cp == 35) {
		document.getElementById("provincia").innerHTML = "Las Palmas";
	}

	if (cp == 36) {
		document.getElementById("provincia").innerHTML = "Pontevedra";
	}

	if (cp == 37) {
		document.getElementById("provincia").innerHTML = "Salamanca";
	}

	if (cp == 38) {
		document.getElementById("provincia").innerHTML = "Santa Cruz de Tenerife";
	}

	if (cp == 39) {
		document.getElementById("provincia").innerHTML = "Cantabria";
	}

	if (cp == 40) {
		document.getElementById("provincia").innerHTML = "Segovia";
	}

	if (cp == 41) {
		document.getElementById("provincia").innerHTML = "Sevilla";
	}

	if (cp == 42) {
		document.getElementById("provincia").innerHTML = "Soria";
	}

	if (cp == 43) {
		document.getElementById("provincia").innerHTML = "Tarragona";
	}

	if (cp == 44) {
		document.getElementById("provincia").innerHTML = "Teruel";
	}

	if (cp == 45) {
		document.getElementById("provincia").innerHTML = "Toledo";
	}

	if (cp == 46) {
		document.getElementById("provincia").innerHTML = "Valencia";
	}

	if (cp == 47) {
		document.getElementById("provincia").innerHTML = "Valladolid";
	}

	if (cp == 48) {
		document.getElementById("provincia").innerHTML = "Vizcaya";
	}

	if (cp == 49) {
		document.getElementById("provincia").innerHTML = "Zamora";
	}

	if (cp == 50) {
		document.getElementById("provincia").innerHTML = "Zaragoza";
	}

	if (cp == 51) {
		document.getElementById("provincia").innerHTML = "Ceuta";
	}

	if (cp == 52) {
		document.getElementById("provincia").innerHTML = "Melilla";
	}
}