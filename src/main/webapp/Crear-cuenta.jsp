<!DOCTYPE html>
<%@page import="domain.Cliente"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="Css/Estilos.css">
    <script language="javascript" type="text/javascript" src="Script/Validar.js"></script>

    <!-- <script language="javascript" type="text/javascript" src="validar.js"></script>
    <link rel="stylesheet" type="text/css" href="Css.css"> -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <title>Crear cuenta</title>
    <script>

        function mostrar(elemento){
        elemento.style.display = "block";
        }

        function noMostrar(elemento){
            elemento.style.display = "none";
        }

        function entraFoco(elemento){
            elemento.style.backgroundColor = "lightblue";
        }

        function saleFoco(elemento){
            elemento.style.backgroundColor = "";
        }

        function entroEnFoco(elemento){
            elemento.className="enfoco";
        }

        function salioDeFoco(elemento){
            elemento.className=";"
        }

        function revOb(elemento){     //para lo obligatorio
            if (esBlanco(elemento)) {
                elemento.className="error";
            }else{
                elemento.className="";
            }
        }

        //necesarios
        function esBlanco(elemento) {
            if (elemento.value=="") {
                return true;
            } else {
                return false;	
            }
        }

        function esNumero(elemento) {
            if (elemento.value!="") {
                var dato = elemento.value;
                if (isNaN(dato)) {
                    return false;
                } else {
                    return true;
                }
            }
        }

        function longitud(elemento, min, max) {
            if (elemento.value!="") {
                var dato = elemento.value;
                if (dato.length>=min && dato.length<=max) {
                    return true;	
                } else {
                    return false;	
                }
            }
        }

        function validarStringInside(elemento,min,max,capaerror){ //para los campos tipo string de este formulario
            if(!validarString(elemento.value,min,max)){
                elemento.style.backgroundColor = "lightyellow";
                elemento.style.borderColor = "red";
                mostrar(capaerror);
            }else{
                elemento.style.backgroundColor = "";
                elemento.style.borderColor = "";
                noMostrar(capaerror);
            }
        }

        function validarEmailInside(elemento, capaerror) {
            if (!validarEmail(elemento)) {
                elemento.style.backgroundColor = "lightyellow";
                elemento.style.borderColor = "red";
                mostrar(capaerror);
            } else {
                elemento.style.backgroundColor = "";
                elemento.style.borderColor = "";
                noMostrar(capaerror);
            }
        }

        function validarCodigoInside(elemento, capaerror) {
            if (!validarCodigo(elemento)) {
                elemento.style.backgroundColor = "lightyellow";
                elemento.style.borderColor = "red";
                mostrar(capaerror);
            } else {
                elemento.style.backgroundColor = "";
                elemento.style.borderColor = "";
                noMostrar(capaerror);
            }
        }

        function validarPasswordInside(){ //para los campos tipo string de este formulario
			if (Password.value != Password2.value) {
			alert("Las passwords deben de coincidir");
			return false;
			} else {
			alert("Las password coinciden");
			return true; 
			}
		}

        function validarCPInside(elemento, min, max, capaerror) {

            if (!validarNumero(elemento.value, min, max)) {
                elemento.style.backgroundColor = "lightyellow";
                elemento.style.borderColor = "red";
                mostrar(capaerror);
                document.getElementById("provincia").innerHTML = "...";

            } else {
                elemento.style.backgroundColor = "";
                elemento.style.borderColor = "";
                noMostrar(capaerror);
                var cp = elemento.value.substring(0, 2);
                if (cp > 52) {
                    mostrar(capaerror);
                } else {
                    codigoProvincia(cp);
                }
            }
        }

////////////////////////////////////////

        function validar() {
            var estaTodoOK = true;
            var mensaje = " ";

            if (esBlanco(Nombre)) {
                mensaje += "Nombre es obligatorio \n";
                estaTodoOK = false;
            } else {
                if (!validarString(Nombre.value, 2, 100)) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "El nombre debe de tener entre 2 y 100 caracteres \n";
                    estaTodoOK = false;
                }
            }

            if (esBlanco(Apellido1)) {
                mensaje += "Primer apellido es obligatorio \n";
                estaTodoOK = false;
            } else {
                if (!validarString(Apellido1.value, 2, 100)) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "El primer apellido debe de tener entre 2 y 100 caracteres \n";
                    estaTodoOK = false;
                }
            }

            
            if (!validarString(Apellido2.value, 0, 100)) {    /////si queremos poner longitud sera sin ".value"/////
                mensaje += "El Segundo apellido debe de tener entre 0 y 100 caracteres \n";
                estaTodoOK = false;
            }

            if (esBlanco(email)) {
                mensaje += "El email es obligatorio \n";
                estaTodoOK = false;
            }
            else {
                if (!validarEmail(email)) {
                    mensaje += "Formato de email incorrecto \n";
                    estaTodoOK = false;
                }
            }

            if (esBlanco(Password)) {
                mensaje += "Password es obligatorio \n";
                estaTodoOK = false;
            } else {
                if (!validarString(Usuario.value, 2, 15)) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "La password debe de tener entre 2 y 15 caracteres y al menos una letra \n";
                    estaTodoOK = false;
                }
            }

            if (esBlanco(Password2)) {
                mensaje += "Repetir Password es obligatorio \n";
                estaTodoOK = false;
            } else {
                if (Password.value != Password2.value) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "Las Password no coinciden \n";
                    estaTodoOK = false;
                }
            }

            if (esBlanco(cp)) {
                mensaje += "Debe seleccionar un codigo postal \n";
                estaTodoOK = false;
            } else {
                if (!validarNumero(cp.value, 5, 5)) {
                    mensaje += "Formato del codigo postal incorrecto \n";
                    estaTodoOK = false;
                }
            }

            if (esBlanco(localidad1)) {
                mensaje += "La direccion complementaria es obligatoria \n";
                estaTodoOK = false;
            } else {
                if (!validarString(localidad1.value, 0, 20)) {
                    mensaje += "La puerta debe de tener entre 1 y 20 caracteres \n";
                    estaTodoOK = false;
                }
            }

            if (esBlanco(Direccion)) {
                mensaje += "Direccion es obligatoria \n";
                estaTodoOK = false;
            } else {
                if (!validarString(Direccion.value, 2, 100)) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "La direccion debe de tener entre 2 y 100 caracteres \n";
                    estaTodoOK = false;
                }
            }

        
            //////////////////////////////////////////

            if (!estaTodoOK) {
                alert(mensaje);
            } else {
                document.getElementById("provinciaHidden").value= document.getElementById("provincia").innerHTML;

                alert("FORMULARIO CORRECTO. ENVIADO. \nNombre: "+ Nombre.value 
                + ".\nPrimer apellido: "+ Apellido1.value
                + ".\nSegundo apellido: "+ Apellido2.value
                + ".\nCodigo Postal: " + cp.value
                + ".\nlocalidad1: " + localidad1.value 
                + ".\nProvincia: " +provincia.innerText
                + ".\nDireccion: "+ Direccion.value
                + ".\nEmail: " + email.value 
                + ".\nContraseña: " + Password.value);
                
            }
            return estaTodoOK;
        }
        
        <%
        Cliente cliente=null;
        String mensajeInfo=null;
        if (session != null) {
        	cliente= (Cliente) session.getAttribute("cliente");
        	mensajeInfo= (String) session.getAttribute("info");
        }
        %>

    </script>
    
</head>

<body>
    <div class="topnav">
        <a href="index.html">Inicio</a>
        <a href="productosDisponibles.html">Productos disponibles</a>
        <a href="contacto.html">Contacto</a>
        <a class="active" style="float:right" href="iniciarSesion.html">Iniciar sesion</a>
    </div> 

    <form id="formulario2" onsubmit="return validar()" action="cliente" method="post" >
        <div text align="center">
            <h1>Crea tu cuenta en Vitrium</h1><br>
        </div>

        <div class="form-group">
            Añada su nombre
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input id="Nombre" name="nombre" type="text" required class="form-control" placeholder="Nombre" onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,2,100,errorNombre);" maxlength="100">
            </div>
            <p class="obligatorio" id="errorNombre">El Nombre debe de ser obligatorio y tener entre 2 y 100 caracteres</p>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-6" style="padding-left:0">
                    <div class="form-group">
                        Añada su primer apellido
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="Apellido1" name="apellido1" type="text" required class="form-control" placeholder="Primer apellido" onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,2,100,errorApellido1);" maxlength="100">
                        </div>
                        <p class="obligatorio" id="errorApellido1">El apellido debe de ser obligatorio y tener entre 2 y 100 caracteres</p>
                    </div>
                </div>
                <div class="col-sm-6" style="padding-right:0">
                    <div class="form-group">
                        Añada su segundo apellido
                        <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="Apellido2" name="apellido2" type="text" class="form-control" placeholder="Segundo apellido" onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,0,100,errorApellido2);" maxlength="100">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-4" style="padding-left:0">
                    <div class="form-group">
                        Codigo Postal
                        <div class="input-group">
                            <span class="input-group-addon">Codigo Postal: </span>
                            <input type="text" id="cp" name="cp" class="form-control" required placeholder="Codigo postal" onblur="validarCPInside(this,5,5,errorCP)">
                        </div>
                        <p class="obligatorio" id="errorCP">El codigo postal debe tener 5 digitos</p>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        Localidad
                        <div class="input-group">
                            <span class="input-group-addon">Localidad: </span>
                            <input id="localidad1" name="localidad1" type="text" required class="form-control" placeholder="Localidad" onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,1,20,errorLocalidad1);" maxlength="20">
                        </div>
                        <p class="obligatorio" id="errorLocalidad1">La localidad es obligatoria debe tener 1-20 caracteres</p>
                    </div>
                </div>
                <div class="col-sm-4" style="padding-right:0">
                    <div class="form-group">
                        Provincia
                        <div class="input-group">
                            <span class="input-group-addon">Provincia: </span>
                            <span id="provincia" class="form-control" disabled="disabled"></span>
                            <input type="hidden" id="provinciaHidden" name="provincia"></input>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            Añada su direccion
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                <input id="Direccion" name="direccion" type="text" required class="form-control" placeholder="calle... , numero... , portal... , piso... , letra..." onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,2,100,errorDireccion);" maxlength="100">
            </div>
            <p class="obligatorio" id="errorDireccion">La direccion debe de ser obligatoria y tener entre 2 y 100 caracteres</p>
        </div>


        <div class="form-group">
            Añada su Email
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                <input type="text" name="email" id="email" class="form-control" required placeholder="Email" onblur="validarEmailInside(this,errorEmail)">
            </div>
            <p class="obligatorio" id="errorEmail">El formato del email no es correcto.</p>
        </div>

        <div class="form-group">
            Añada su password
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input id="Password" name="password" type="password" required class="form-control" placeholder="Password" onfocus="entraFoco(this)" onblur="saleFoco(this);validarCodigoInside(this,2,12,errorPassword);" maxlength="12">
            </div>
            <p class="obligatorio" id="errorPassword">La pasword es obligatoria y debe tener entre 2 y 15 caracteres</p>
        </div>

        <div class="form-group">
            repita su password
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                <input id="Password2" type="password" required class="form-control" placeholder="Repita Password" onfocus="entraFoco(this)" onblur="saleFoco(this);validarPasswordInside(this,2,20,errorPassword2);" maxlength="20">
            </div>
            <p class="obligatorio" id="errorPassword2">El Password debe coincidir y tener entre 2 y 15 caracteres</p>
        </div>

        

        <div text align="center">
        <h3><button type="submit" id="submit" value="submit">Enviar</button></h3>
        </div>

    </form>

</body>
</html>