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
    
            function validarNumeroInside(elemento,min,max,capaerror){ //comprueba si es numerico el campo enviado 
        if(!validarNumero(elemento.value,min,max)){
            elemento.style.backgroundColor = "lightyellow";
            elemento.style.borderColor = "red";	
            mostrar(capaerror);
        }else{
            elemento.style.backgroundColor = "";
            elemento.style.borderColor = "";
            noMostrar(capaerror);				
        }
    }
    
           
    
    ////////////////////////////////////////
    
            function validar() {
                var estaTodoOK = true;
                var mensaje = " ";
    
                if (esBlanco(Categoria)) {
                mensaje += "Categoria es obligatoria \n";
                estaTodoOK = false;
            } else {
                if (!validarString(Categoria.value, 2, 100)) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "La categoria debe de tener entre 2 y 100 caracteres \n";
                    estaTodoOK = false;
                }
            }

            if (esBlanco(Descripcion)) {
                mensaje += "Descripcion es obligatoria \n";
                estaTodoOK = false;
            } else {
                if (!validarString(Descripcion.value, 2, 1000)) {    /////si queremos poner longitud sera sin ".value"/////
                    mensaje += "La descripcion debe de tener entre 2 y 1000 caracteres \n";
                    estaTodoOK = false;
                }
            }

           
            if (esBlanco(Precio)) {
                mensaje += "Debe añadir un precio \n";
                estaTodoOK = false;
            } else {
                if (!validarNumero(precio.value, 1, 5)) {
                    mensaje += "Formato del precio incorrecto, no debe tener decimales \n";
                    estaTodoOK = false;
                }
            }
    
            
                //////////////////////////////////////////
    
                if (!estaTodoOK) {
                    alert(mensaje);
                } else {
                    document.getElementById("provinciaHidden").value= document.getElementById("provincia").innerHTML;
    
                    alert("FORMULARIO CORRECTO. ENVIADO. \nCategoria: "+ Categoria.value 
                    + ".\nPrecio: "+ Precio.value
                    + ".\nDescripcion: "+ Descripcion.value);
                    
                }
                return estaTodoOK;
            }
            <%
            Cliente cliente=null;
            if (session != null) {
            	cliente= (Cliente) session.getAttribute("cliente");
            }
            
            if (cliente == null || cliente.getClase() != 1) {
    			request.setAttribute("mensaje", "No tiene permisos para acceder");
            	getServletContext().getRequestDispatcher("/Fin").forward(request, response);
            }
            %>
        </script>
        
    </head>

<body>
    <form id="formulario2" onsubmit="return validar()" action="articulo" method="post" enctype="multipart/form-data" >
        <div text align="center">
            <h1>Inserte producto</h1><br>
        </div>

        <div class="form-group">
            Inserte la categoria
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt"></i></span>
                <input id="Categoria" name="categoria" type="text" required class="form-control" placeholder="Categoria" onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,2,100,errorCategoria);" maxlength="100">
            </div>
            <p class="obligatorio" id="errorCategoria">La categoria debe de ser obligatoria y tener entre 2 y 100 caracteres</p>
        </div>

        
        <div class="form-group">
        Inserte el precio del producto
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-euro"></i></span>
                <input id="Precio" name="precio" type="text" required class="form-control" placeholder="Precio" onfocus="entraFoco(this)" onblur="saleFoco(this);validarNumeroInside(this,1,5,errorPrecio);" maxlength="5">
            </div>
            <p class="obligatorio" id="errorPrecio">El precio debe de ser obligatorio y tener entre 2 y 5 caracteres</p>
        </div>
                

        <div class="form-group">
            Inserte una descripcion
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-comment"></i></span>
                <input id="Descripcion" name="descripcion" type="text" required class="form-control" placeholder="Descripcion" onfocus="entraFoco(this)" onblur="saleFoco(this);validarStringInside(this,2,1000,errorDescripcion);" maxlength="1000">
            </div>
            <p class="obligatorio" id="errorDescripcion">La descripcion debe de ser obligatoria y tener entre 2 y 1000 caracteres</p>
        </div>

        <div class="form-group">
            Inserte una Imagen
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
                <input id="imagen" name="imagen" type="file" required class="form-control" >
            </div>
        </div>

        

        <div text align="center">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6">
                        <h3><button type="submit" id="submit" value="submit">Añadir</button></h3>
                    </div>
                    <div class="col-sm-6">
                        <button id="submit2" onclick="location.href='productosDisponibles.html'">Volver atras</button>
                    </div>
                </div>
            </div>
        </div>

    </form>
    
</body>
</html>