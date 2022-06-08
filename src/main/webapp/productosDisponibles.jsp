<!DOCTYPE html>
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
    	
    	ServicioArticulo servicioArticulo= new ServicioArticulo();
    	List<Articulo> articulos= servicioArticulo.recuperarTodosArticulo();
        %>

    </script>
</head>
<body>

    <div class="topnav">
        <a href="index.html">Inicio</a>
        <a class="active" href="productosDisponibles.jsp">Productos disponibles</a>
        <a href="contacto.html">Contacto</a>
        <%if (cliente == null) { %>
        <a style="float:right" href="iniciarSesion.jsp">Iniciar sesion</a>
        <%} else { %>
        <a style="float:right">Hola <%=cliente.getNombre() %></a>
        <%} %>
    </div> 
     
     
    
        
     <% if (cliente != null && cliente.getClase()>0) {  %>
        	<form id="crear" action="añadirProducto.jsp">
            <div text align="center">
                <h2>Insertar productos</h2>
                <h3><button type="submit" id="submit2" value="submit">Inserta un producto nuevo</button></h3>
                
            </div>
        </form>
      <%  }  %>
       
       
       <% for(int i=0;i<articulos.size();i++){ %>
    	   
       
       <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3">
                         <img src="articulo/imagen?fichero=<%= articulos.get(i).getPath()  %>" height="100px" width="100px"> 
                </div>
                <div class="col-sm-3">
                    	<%=  articulos.get(i).getCategoria()  %>
                </div>
                <div class="col-sm-3">
                         <%=  articulos.get(i).getPrecio()  %>
                </div>
                <div class="col-sm-3">
                         <%=  articulos.get(i).getDescripcion()  %>
                </div>
            </div>
        </div>
       
        <% }  %>
       
</body>
</html>