<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="Css/Estilos.css">
    <script language="javascript" type="text/javascript" src="Script/Validar.js"></script>

    <!--<link rel="stylesheet" type="text/css" href="Css.css">-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <title>Iniciar sesion</title>

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


        function ver() {
                    var x = document.getElementById("Password");
                    if (x.type === "password") {
                        x.type = "text";
                    } else {
                        x.type = "password";
                    }
                }

        ////////////////////////////////////////

        function validar() {
            var estaTodoOK = true;
            var mensaje = " ";

            if (esBlanco(Usuario)) {
                mensaje += "Usuario es obligatorio \n";
                estaTodoOK = false;
            } else {
                if (!validarString(Usuario.value, 2, 15)) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "El Usuario debe de tener entre 2 y 15 caracteres \n";
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

            //////////////////////////////////////////

        if (!estaTodoOK) {
            alert(mensaje);
        } else {
            
            alert("FORMULARIO CORRECTO. ENVIADO. \nUsuario: "+ Usuario.value
            + ".\nContrase??a: " + Password.value);
            
        }
        return estaTodoOK;
        }
    </script>
</head>

<body id="body">

    <div class="topnav">
        <a href="Index.html">Inicio</a>
        <a href="ProductosDisponibles.html">Productos disponibles</a>
        <a href="ProductosEncargo.html">Productos por encargo</a>
        <a href="Contacto.html">Contacto</a>
        <a class="active" style="float:right" href="IniciarSesion.php">Iniciar sesion</a>
    </div> 

    <div>
        <p><form id="formulario" action="IniciarSesion.php">
            
            <div class="form-group" text align="center">
                <h2>Iniciar Sesion</h2><br>
                </div>
                <div class="form-group">
                    Inserte su nombre de usuario
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="Usuario" name="usuario" type="text" required class="form-control" placeholder="Usuario" onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,2,15,errorUsuario);" maxlength="15">
                    </div>
                    <p class="obligatorio" id="errorUsuario">El Usuario debe de ser obligatorio y tener entre 2 y 15 caracteres</p>
                </div>

                

                <div class="form-group">
                    Inserte su pasword
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="Password" name="password" type="password" required class="form-control" placeholder="Password" onfocus="entraFoco(this)" onblur="saleFoco(this);validarCodigoInside(this,2,12,errorPassword);" maxlength="12">
                    </div>
                    <p class="obligatorio" id="errorPassword">El Password debe de ser obligatorio y tener entre 2 y 12 caracteres</p>
                    <input type="checkbox" onclick="ver()">Show Password 
    
                </div>

                <div>
                <h3><button type="submit" id="submit" value="submit">Iniciar sesion</button></h3>
                </div>

            </div>

        </form></p><br>
        
        <form id="crear" action="Crear-cuenta.php">
            <div text align="center">
                <h2>Aun no tienes cuenta</h2>
                <h3><button type="submit" id="submit2" value="submit">Crear cuenta gratuita</button></h3>
                
            </div>
        </form>
    </div>
</body>
</html>