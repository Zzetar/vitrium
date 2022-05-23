<!DOCTYPE html>
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

        unction validarPasswordInside(){ //para los campos tipo string de este formulario
			if (Password.value != Password2.value) {
			alert("Las passwords deben de coincidir");
			return false;
			} else {
			alert("Las password coinciden");
			return true; 
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

            if (esBlanco(Usuario)) {
                mensaje += "Usuario es obligatorio \n";
                estaTodoOK = false;
            } else {
                if (!validarString(Usuario.value, 2, 15)) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "El usuario debe de tener entre 2 y 15 caracteres \n";
                    estaTodoOK = false;
                }
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
                    mensaje += "La password debe de tener entre 2 y 15 caracteres \n";
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

        
        //////////////////////////////////////////

        if (!estaTodoOK) {
            alert(mensaje);
        } else {
            
            alert("FORMULARIO CORRECTO. ENVIADO. \nNombre: "+ Nombre.value 
            + ".\nUsuario: "+ Usuario.value
            + ".\nEmail: " + email.value 
            + ".\nContraseña: " + Password.value);
            
        }
        return estaTodoOK;
        }

    </script>
    
</head>

<body>
    <div class="topnav">
        <a href="Index.html">Inicio</a>
        <a href="ProductosDisponibles.html">Productos disponibles</a>
        <a href="ProductosEncargo.html">Productos por encargo</a>
        <a href="Contacto.html">Contacto</a>
        <a class="active" style="float:right" href="IniciarSesion.php">Iniciar sesion</a>
    </div> 

    <form id="formulario2" onsubmit="return validar()" action="grabar.php" >
        <div text align="center">
            <h1>Crear cuenta</h1><br>
        </div>

        <div class="form-group">
            Añada su nombre completo
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input id="Nombre" name="nombre" type="text" required class="form-control" placeholder="Nombre" onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,2,100,errorNombre);" maxlength="100">
            </div>
            <p class="obligatorio" id="errorNombre">El Nombre debe de ser obligatorio y tener entre 2 y 100 caracteres</p>
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
            Añada su nombre de usuario
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                <input id="Usuario" name="usuario" type="text" required class="form-control" placeholder="Usuario" onfocus="entraFoco(this)" onblur="saleFoco(this);" onkeyup=" validarUsuarioInside(this,2,15,errorUsuario,existeUsuario);" maxlength="15">
            </div>
            <p class="obligatorio" id="errorUsuario">El Usuario debe de ser obligatorio y tener entre 2 y 15 caracteres</p>
            <p class="obligatorio" id="existeUsuario">El usuario ya existe </p>
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