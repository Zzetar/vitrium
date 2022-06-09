<!DOCTYPE html>
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
    

    <title>Productos disponibles</title>

    <script>
    	function cambiarLinea (idArticulo, precio, cantidad, descripcion, path) {
    		document.getElementById("idArticulo").value=idArticulo;
    		document.getElementById("precio").value=precio;
    		document.getElementById("cantidad").value=cantidad;
    		document.getElementById("descripcion").value=descripcion;
    		document.getElementById("path").value=path;
    		document.getElementById("hiddenForm").submit();
    	}
        <%
        Cliente cliente=null;
        Carrito carrito=null;
        if (session != null) {
        	cliente= (Cliente) session.getAttribute("cliente");
        	carrito= (Carrito) session.getAttribute("carrito");
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
        <a href="index.jsp">Inicio</a>
        <a class="active" href="productosDisponibles.jsp">Productos disponibles</a>
        <a href="contacto.jsp">Contacto</a>
        <%if (cliente == null) { %>
        <a style="float:right" href="iniciarSesion.jsp">Iniciar sesion</a>
        <%} else { %>
        <a style="float:right" href="iniciarSesion.jsp">Hola <%=cliente.getNombre() %> ¿Quieres cerrar sesion?</a>
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
       
<table>
  <tr>
    <th>Imagen</th>
    <th>Categoria</th>
    <th>Precio</th>
    <th>Descripcion</th>
    <th>Numero de articulos</th>
    <th>Subtotal</th>
  </tr>
  <% for(int i=0;i<articulos.size();i++){ %>
  <tr>
    <td>  <img src="articulo/imagen?fichero=<%= articulos.get(i).getPath()  %>" height="100px" width="100px"> </td>
    <td><%=  articulos.get(i).getCategoria()  %></td>
    <td><%=  articulos.get(i).getPrecio()  %> &euro;</td>
    <% 
                LinPed linea= null;
                if (carrito != null) {
                	linea= carrito.getLinea(articulos.get(i).getIdArticulo());
                }
                %>
    <td><input type="number" min=0 max=99 value="<%= linea != null? carrito.getLinea(articulos.get(i).getIdArticulo()).getCantidad() : 0 %>"
        onchange="cambiarLinea(<%=  articulos.get(i).getIdArticulo()  %>,<%=  articulos.get(i).getPrecio()  %>, this.value,'<%=  articulos.get(i).getDescripcion()  %>' , '<%=  articulos.get(i).getPath()  %>')"></input></td>
    <td><span>Subtotal: </span>
        <span><%= linea != null? carrito.getLinea(articulos.get(i).getIdArticulo()).getPrecioFinal() : 0 %> &euro;</span></td>
  </tr>
  <% }  %>
 </table>      
 
 
       <div class="container-fluid">
       <% for(int i=0;i<articulos.size();i++){ %>
            <div class="row">
                <div class="col-sm-2">
                         <img src="articulo/imagen?fichero=<%= articulos.get(i).getPath()  %>" height="100px" width="100px"> 
                </div>
                <div class="col-sm-2">
                    	<%=  articulos.get(i).getCategoria()  %>
                </div>
                <div class="col-sm-2">
                         <%=  articulos.get(i).getPrecio()  %> &euro;
                </div>
                <div class="col-sm-2">
                         <%=  articulos.get(i).getDescripcion()  %>
                </div>
                <% 
                LinPed linea= null;
                if (carrito != null) {
                	linea= carrito.getLinea(articulos.get(i).getIdArticulo());
                }
                %>
                
                <div class="col-sm-2">
                	<input type="number" min=0 max=99 value="<%= linea != null? carrito.getLinea(articulos.get(i).getIdArticulo()).getCantidad() : 0 %>"
                		onchange="cambiarLinea(<%=  articulos.get(i).getIdArticulo()  %>,<%=  articulos.get(i).getPrecio()  %>, this.value,'<%=  articulos.get(i).getDescripcion()  %>' , '<%=  articulos.get(i).getPath()  %>')"></input> 
                </div>
                <div class="col-sm-2">
                	<span>Subtotal: </span>
                	<span><%= linea != null? carrito.getLinea(articulos.get(i).getIdArticulo()).getPrecioFinal() : 0 %> &euro;</span>
                </div>
            </div>
        <% }  %>
        </div>
        
        <h3>
        	<span>TOTAL: </span>
        	<b><%=carrito != null? carrito.precioTotal() : 0 %> &euro;</b>
       	</h3>
       <form action="carrito" method="post" id="hiddenForm">
       		<input type="hidden" name="idArticulo" id="idArticulo" >
       		<input type="hidden" name="precio" id="precio" >
       		<input type="hidden" name="cantidad" id="cantidad" >
       		<input type="hidden" name="descripcion" id="descripcion" >
       		<input type="hidden" name="path" id="path" >
       </form>
        <%if (cliente != null && carrito != null) { %>
        	<button onclick="location.href='validarTarjeta.html'" id="comprar">Comprar</button>
        <%} %>
</body>
</html>