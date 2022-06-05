<!DOCTYPE html>
<%@page import="domain.Cliente"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="Css/Estilos.css">
    <script language="javascript" type="text/javascript" src="Script/Validar.js"></script>
    

    <title>Productos disponibles</title>

    <script>
        <%
        Cliente cliente=null;
        if (session != null) {
        	cliente= (Cliente) session.getAttribute("cliente");
        }
        String mensajeInfo= (String) request.getAttribute("info");
    	if (mensajeInfo != null) {
    		%>alert("<%=mensajeInfo%>");<%
    	}
        %>

    </script>
</head>
<body>

    <div class="topnav">
        <a href="Index.html">Inicio</a>
        <a class="active" href="ProductosDisponibles.html">Productos disponibles</a>
        <a href="ProductosEncargo.html">Productos por encargo</a>
        <a href="Contacto.html">Contacto</a>
        <%if (cliente == null) { %>
        <a style="float:right" href="IniciarSesion.html">Iniciar sesion</a>
        <%} else { %>
        <a style="float:right">Hola <%=cliente.getNombre() %></a>
        <%} %>
    </div> 
    
</body>
</html>