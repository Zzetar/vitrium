<!DOCTYPE html>
<%@page import="domain.Carrito"%>
<%@page import="domain.Cliente"%>
<html>
<head>
<meta charset="ISO-8859-1">

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

<title>Vitrium</title>

</head>
<body>
    
    
    <div class="topnav">
        <a class="active" href="index.jsp">Inicio</a>
        <a href="productosDisponibles.jsp">Productos disponibles</a>
        <a href="contacto.jsp">Contacto</a>
        <%if (cliente == null) { %>
        <a style="float:right" href="iniciarSesion.jsp">Iniciar sesion</a>
        <%} else { %>
        <a href="pedidos">Pedidos</a>
        <a style="float:right" href="iniciarSesion.jsp">Hola <%=cliente.getNombre() %> �Quieres cerrar sesion?</a>
        <%} %>
    </div> 

  
    <div class="row">
        <div class="col-sm-6">
          <br><br><br><br>
          <h1 text align="center" >Vitrium</h1>
          <h3 text align="center" >Somos un colectivo de artesanos del vidrio.</h3>
        </div>
        <div class="col-sm-6" style="padding-left:0">
          <br>
          <img src="img/logoVitrium.jpg" height="250px" width="250px">
        </div>
    </div>

    Somos un pequeño colectivo de artesanos del vidrio sin animo de lucro.<br>

    Hacemos estas piezas de artesania, las cuales vendemos para reinvertir el beneficio en compra de mas material, vidrio, estaño, pulidoras, hornos de fundicion, etc...<br>

    Todas nuestras piezas estan echas a mano, ya sea una pieza de tiffanys, una pieza estañada, una pieza fundida, echa de fritas y fundida o pegado por ultravioleta.<br>

    <h2 text align="center">Los distinto metodos que utilizamos para trabajar el vidrio son:</h2>
    <ul class="a">
      <li>El metodo tiffanys es un metodo de soldadura el cual las piezas de cristal una vez cortadas y pulidas en la forma requerida, se recubren con un patina de cobre y se funden entre si con un soldador y estaño.</li>
      <li>El metodo de estañado con plomo, se cortan y pulen las piezas, y se unen mediante unas barras en forma de H de plomo y se unen en las intersecciones de estas con mas plomo, este metodo suele utilizarse en vidrieras.</li>
      <li>El metodo de fusion, es cortar piezas  medida y meterlas a un horno de fundicion donde podremos dar forma a las piezas, fusionar piezas y fusionar piezas con materiales distintos como cobre, bronce...</li>
      <li>El metodo de luz UV, se cortan y pulen las piezas de cristal y se unene mediante una resina transparente, la cual al estar sometida a la luz UV se solidifica haciendo una union muy fuerte entre ambas piezas.</li>
      <li>El metodo de fritas es, cuando restos de vidrio de un mismo material que ya no se pueden utilizar, se machacan y se hacen polvo o polvo grueso, y este se fusiona en el horno mediante un molde de la pieza a realizar, dando lugar a una pieza echa de firtas, suelen ser muy bellas, pero extremadamente fragiles.</li>
      <li>El metodo del chorro de arena, es cuando sobre un vidrio normalmente transparente se somete al chorro de arena creando con este una imagen ya que alli donde folpea se convierte en translucido.</li>
  </ul>
   

</body>
</html>
