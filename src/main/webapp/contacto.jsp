<!DOCTYPE html>
<%@page import="domain.Carrito"%>
<%@page import="domain.Cliente"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="Css/Estilos.css">
    <script language="javascript" type="text/javascript" src="Script/Validar.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
    <script>
 <% Cliente cliente=null;
        Carrito carrito=null;
        if (session != null) {
        	cliente= (Cliente) session.getAttribute("cliente");
        	carrito= (Carrito) session.getAttribute("carrito");
        } %>
</script>

    <title>Contacto</title>
</head>
<body>

    <div class="topnav">
        <a href="index.jsp">Inicio</a>
        <a href="productosDisponibles.jsp">Productos disponibles</a>
        <a class="active"href="contacto.jsp">Contacto</a>
        <%if (cliente == null) { %>
        <a style="float:right" href="iniciarSesion.jsp">Iniciar sesion</a>
        <%} else { %>
        <a href="pedidos">Pedidos</a>
        <a style="float:right" href="iniciarSesion.jsp">Hola <%=cliente.getNombre() %> ¿Quieres cerrar sesion?</a>
        <%} %>
    </div> 
    

    <h1>Ubicacion:</h1>

    
    
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-4" >
                <div class="form-group">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1354.3934635418047!2d-3.8068694345478256!3d40.35321049835247!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd41893b6606f2fd%3A0x60bec09ac3565b08!2sMuseo%20Municipal%20de%20Arte%20en%20Vidrio%20Mava!5e0!3m2!1ses!2ses!4v1653249012494!5m2!1ses!2ses" width="400" height="250" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
            </div>
            <div class="col-sm-8" style="padding-left:0">
                <div class="form-group">
                    <h2>
                    Correo: Vitrium @hotmail.es<br>
                    Direccion: Av. los Castillos, 28925 Alcorcon, Madrid<br>
                        -Se encuentra en el parque de los castillos.<br>
                    Horario L-V 10:00 - 14:00<br> <br>
                    Siguenos en instagram: <a href="https://www.instagram.com/vitrium/" target="iframe_a"> <img src="img/instagram.jpg" height="100px" width="100px"></a> 
                    
                </h2>
                </div>
            </div>
        </div>
    </div>

</body>
</html>