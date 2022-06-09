<!DOCTYPE html>
<%@page import="java.util.Collection"%>
<%@page import="domain.Pedido"%>
<%@page import="domain.LinPed"%>
<%@page import="domain.Carrito"%>
<%@page import="java.util.List"%>
<%@page import="servicios.ServicioArticulo"%>
<%@page import="domain.Articulo"%>
<%@page import="domain.Cliente"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="Css/Estilos.css">
    <script language="javascript" type="text/javascript" src="Script/Validar.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    

    <title>Compra finalizada</title>

    <script>
        <%
        Cliente cliente=null;
        Pedido pedido= (Pedido) request.getAttribute("pedido");
        Collection<LinPed> lineas= (Collection<LinPed>) request.getAttribute("lineas");
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
        <a href="index.jsp">Inicio</a>
        <a class="active" href="productosDisponibles.jsp">Productos disponibles</a>
        <a href="contacto.jsp">Contacto</a>
        <%if (cliente == null) { %>
        <a style="float:right" href="iniciarSesion.jsp">Iniciar sesion</a>
        <%} else { %>
        <a style="float:right">Hola <%=cliente.getNombre() %></a>
        <%} %>
    </div>
    
    	<h2>Pedido <%=pedido.getIdPedido() %></h2>
       
       <div class="container-fluid">
       <% for(LinPed linea: lineas){ %>
            <div class="row">
                <div class="col-sm-2">
                         <img src="articulo/imagen?fichero=<%= linea.getPath()  %>" height="100px" width="100px"> 
                </div>
                <div class="col-sm-2">
                         <%=  linea.getDescripcion()  %>
                </div>
                
                <div class="col-sm-2">
                	<span><%=linea.getCantidad()%></span>
                </div>
                <div class="col-sm-2">
                	<span>Subtotal: </span>
                	<span><%=linea.getPrecioFinal()%></span>
                </div>
            </div>
        <% }  %>
        </div>
        
        <h3>
        	<span>TOTAL: </span>
        	<b><%=pedido.getImporte() %> &euro;</b>
       	</h3>
</body>
</html>