<!DOCTYPE html>
<%@page import="domain.Articulo"%>
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
        <a href="index.html">Inicio</a>
        <a class="active" href="productosDisponibles.jsp">Productos disponibles</a>
        <a href="contacto.html">Contacto</a>
        <%if (cliente == null) { %>
        <a style="float:right" href="iniciarSesion.html">Iniciar sesion</a>
        <%} else { %>
        <a style="float:right">Hola <%=cliente.getNombre() %></a>
        <%} %>
    </div> 
     
     
    
        
     <% if (cliente != null && cliente.getClase()>0) {  %>
        	<form id="crear" action="añadirProducto.html">
            <div text align="center">
                <h2>Insertar productos</h2>
                <h3><button type="submit" id="submit2" value="submit">Inserta un producto nuevo</button></h3>
                
            </div>
        </form>
      <%  }  %>
       
       
       
</body>
</html>