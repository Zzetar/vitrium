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
        if (pedido == null || lineas == null || lineas.isEmpty()) {
        	request.setAttribute("mensaje", "No se puede acceder a la compra directamente");
        	getServletContext().getRequestDispatcher("/Fin").forward(request, response);
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
        <a href="productosDisponibles.jsp">Productos disponibles</a>
        <a href="contacto.jsp">Contacto</a>
        <%if (cliente == null) { %>
        <a style="float:right" href="iniciarSesion.jsp">Iniciar sesion</a>
        <%} else { %>
        <a href="pedidos">Pedidos</a>
        <a style="float:right">Hola <%=cliente.getNombre() %></a>
        <%} %>
    </div>
    
    	<h2>Resumen pedido <%=pedido.getIdPedido() %></h2>
       
       
        <table>
            <tr>
              <th>Imagen</th>
              <th>Descripcion</th>
              <th>Estado</th>
              <th>Cantidad</th>
              <th>Precio</th>
            </tr>
            <% for(LinPed linea: lineas){ %>
            <tr>
              <td>   <img src="articulo/imagen?fichero=<%= linea.getPath()  %>" height="100px" width="100px"> </td>
              <td><%=  linea.getDescripcion()  %></td>
              <td><%=  pedido.getEstadoPedido() %> </td>
              <td><span><%=linea.getCantidad()%></span></td>
              <td> <span>Subtotal: </span> <%=linea.getPrecioFinal()%> &euro; </td>
            </tr>
            <% }  %>
       </table>    
        
        <h3>
        	<span>TOTAL: </span>
        	<b><%=pedido.getImporte() %> &euro;</b>
       	</h3>
</body>
</html>