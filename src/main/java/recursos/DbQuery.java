package recursos;

public class DbQuery {
	
	// ivas	
		private static final String RecuperarIva = "select cod_iva, tipo_iva from ivas where cod_iva = ?";
		private static final String RecuperarTodosIva = "select cod_iva, tipo_iva from ivas order by tipo_iva";
		private static final String InsertarIva = "Insert into ivas(cod_iva, tipo_iva) values(?,?)";
		private static final String ModificarIva = "update ivas set tipo_iva=? where cod_iva=?";
		private static final String BorrarIva = "delete ivas where cod_iva=?";

	// formas pago 	
		private static final String RecuperarFormaPago = "select codigo, numero_vtos, dias from formas_pago where codigo = ?";
		private static final String RecuperarTodosFormaPago = "select codigo, numero_vtos, dias from formas_pago order by 2,3";
		private static final String InsertarFormaPago = "insert into formas_pago(codigo, numero_vtos, dias) values(?,?,?)";
		private static final String ModificarFormaPago = "Update formas_pago set numero_vtos=?, dias=? where codigo=?";
		
	// tarifas	
		private static final String RecuperarTarifa = "select cod_tarifa, descripcion  from tarifas where cod_tarifa=?";
		private static final String RecuperarTodosTarifa = "select cod_tarifa, descripcion  from tarifas order by 1";
		private static final String InsertarTarifa = "Insert into tarifas (cod_tarifa,descripcion) values(?,?)";
		private static final String ModificarTarifa = "Update tarifas set descripcion=? where cod_tarifa=?";
			

		// clientes
	private static final String InsertarCliente = "insert into clientes(nombre, apellido1, apellido2, provincia, localidad, direccion, codigoPostal, email, contraseña, clase) values (?,?,?,?,?,?,?,?,?,?)";
	private static final String ModificarCliente = "update clientes set razon_social=?,telf=?,direccion=?,oferta=?,"
	+ "alb_fact=?,cod_iva=?,cod_tarifa=?,forma_pago=? where cod_cli=?";

	private static final String BorrarCliente = "delete from clientes where cod_cli =?";
	private static final String RecuperarCliente = "select cod_cli,razon_social,nvl(telf,''),direccion,"
				+ "oferta,alb_fact,cod_iva,cod_tarifa,forma_pago from clientes where cod_cli=?";

	private static final String RecuperarTodosCliente = "select cod_cli,razon_social,telf,direccion,"
				+ "oferta,alb_fact,cod_iva,cod_tarifa,forma_pago from clientes order by razon_social";
	//private static final String InsertarClienteProcedure = " call  insertarCliente (?,?,?,?,?,?,?,?,?)";
	private static final String InsertarClienteProcedure = "insert into clientes(cod_cli,razon_social,telf,direccion,oferta,"
			+ "alb_fact,cod_iva,cod_tarifa,forma_pago) values (?,?,?,?,?,?,?,?,?)";
	private static final String ModificarClienteConcurrente = "update clientes "+
    " set RAZON_SOCIAL=?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
        " TELF=?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
        " DIRECCION =?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
        " OFERTA =?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
        " ALB_FACT =?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
        " COD_IVA =?,"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        " COD_TARIFA =?, "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
        " FORMA_PAGO =? "+
   " where  cod_cli= ? and "+
       " RAZON_SOCIAL=? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
       " nvl(TELF,'null') =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
       " DIRECCION =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
       " OFERTA =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
       " ALB_FACT =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
       " COD_IVA =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
       " COD_TARIFA =? and "+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
       " FORMA_PAGO =? " ;
	// proveedor
	
	private static final String InsertarProveedor = "insert into proveedores (COD_PRO,RAZON_SOCIAL, TELF, DIRECCION, ALB_FACT, COD_POS, COD_IVA, FORMA_PAGO )"
                              +" values(?,?,?,?,?,?,?,?)";
	private static final String ModificarProveedor = " update proveedores set RAZON_SOCIAL=?, "+
			           " TELF=?,"+
                       " DIRECCION=?,"+
                       " ALB_FACT=?,"+
                       " COD_POS=?,"+
                       " COD_IVA=?,"+
                       " FORMA_PAGO=?"+
                "where cod_pro=?";
	private static final String BorrarProveedor = "delete from proveedores where cod_pro=?";
	private static final String RecuperarProveedor = "select cod_pro,razon_social,telf,direccion,alb_fact,cod_pos,cod_iva,forma_pago  from proveedores where cod_pro=?" ;                      
	private static final String TodosProveedor = "select cod_pro,razon_social,telf,direccion,alb_fact,cod_pos,cod_iva,forma_pago  from proveedores order by razon_social";
// familias
private static final String RecuperarFamilia = "select cod_familia, descripcion  from familias where cod_familia=?";
private static final String RecuperarTodosFamilia = "select cod_familia, descripcion  from familias  order by descripcion";
//almacenes
private static final String RecuperarAlmacen = "select cod_alm, descripcion,direccion,telf  from almacenes where cod_alm=?";
private static final String RecuperarTodosAlmacen = "select cod_alm, descripcion,direccion,telf  from almacenes order by descripcion";
//articulos
private static final String InsertarArticulo = "insert into articulos(categoria, precio, descripcion, path)values (?,?,?,?)";
private static final String getModificarArticulo = "update  articulos set descripcion=?,precio_mer=?,cod_fam=? where cod_art=?";
private static final String BorrarArticulo ="delete from articulos where cod_art=?";
private static final String RecuperarArticulo = "select cod_art,descripcion,precio_mer,cod_fam from articulos where cod_art=?";
private static final String RecuperarTodosArticulo = "select categoria,precio,descripcion,path,idArticulo from articulos order by idArticulo";
// existencias
private static final String InsertarExistencia = "insert into existencias ( cod_alm,cod_art,f_caducidad,stock_ini,Stock_teorico,pcmp) values(?,?,?,?,?,?)";
private static final String ModificarExistencia = "update existencias set stock_ini=?,Stock_teorico=?,pcmp=? where cod_alm=? and cod_art=? and f_caducidad=?"  ;             
private static final String BorrarExistencia = "delete from existencias where cod_alm=? and cod_art=? and f_caducidad=?";
private static final String RecuperarExistencia = "select cod_alm,cod_art,f_caducidad,stock_ini,Stock_teorico,pcmp from existencias where cod_alm=? and cod_art=? and f_caducidad=?" ;               
private static final String RecuperarTodosExistencia = "select cod_alm,cod_art,f_caducidad,stock_ini,Stock_teorico,pcmp from existencias order by 1,2,3 ";
// contador fact 
private static final String ModificarContadorFact = "update contador_fact set numero=numero+1";
private static final String RecuperarContadorFact = " select numero from contador_fact";
// factura
private static final String InsertarFactura ="insert into facturas ( idPedido, idCliente, idArticulo) values(?,?,?)";
private static final String BorrarFactura = null;
private static final String RecuperarFactura = "select num_factura,fecha_fact, importe,cod_pro from facturas where num_factura=? " ;
private static final String RecuperarTodosFactura = "select num_factura,fecha_fact, importe,cod_pro from facturas " ;
private static final String RecuperarTodosFacturaNoRecibo = "select num_factura,fecha_fact, importe,cod_pro from facturas " +
		                                                 " where num_factura not in (select distinct num_factura from recibos)";
// recibos
private static final String InsertarRecibo = "insert into recibos (num_factura,numero,fecha_vto,importe) values(?,?,?,?)";
private static final String BorrarRecibo = null;
private static final String RecuperarRecibo = null;
private static final String RecuperarTodosRecibo = null;
private static final String RecuperarTodosReciboFactura = null;
private static final String RecuperarTodosReciboProvedor = "select num_factura,numero,fecha_vto,importe from recibos where num_factura in (select num_factura from facturas where cod_pro=?)";
// lin PED
private static final String InsertarLinPed = "insert into  lineapedidos(idPedido, idArticulo, cantidad, gastosEnvio, precioFinal)values(?,?,?,?,?)";
private static final String ModificarLinPed = null;  
private static final String BorrarLinPed = null;
private static final String BorrarTodosLinPedPedido = "delete from lin_ped where n_ped=?";
private static final String RecuperarLinPed = null;
private static final String RecuperarTodosLinPed = null;
private static final String RecuperarTodosLinPedPedido = "select n_ped,cod_art,cantidad,cantidad_serv  from lin_ped where n_ped=? order by cod_art";
private static final String RecuperarLinPedBloqueo ="select n_ped,cod_art,cantidad,cantidad_serv  from lin_ped where n_ped=?  order by cod_art for update";
// pedido
private static final String RecuperarPedido = "select idCliente,estadoPedido, fechaPed from pedidos  where idPedido= ?";
private static final String InsertarPedido = "insert into  pedidos (idCliente, estadoPedido ,fechaPed, importe) values(?,?,?,?)";
private static final String ModificarPedido = "update pedidos set Fecha_ped= ? where n_ped= ?";
private static final String BorrarPedido = " delete from pedidos where n_ped=?";
private static final String RecuperarTodosPedido = "select n_ped,fecha_ped from pedidos ";
private static final String RecuperarTodosPedidoPro = "select n_ped,fecha_ped from pedidos where cod_pro=? ";
// albaranes 
private static final String InsertarAlbaran = "insert into albaranes ( cod_albaran,f_albaran,F_entrada,cod_pro,num_factura) values(?,?,?,?,?)";
private static final String BorrarAlbaran = null;
private static final String RecuperarAlbaran ="select  cod_albaran,f_albaran,F_entrada,cod_pro,num_factura from albaranes where cod_albaran=?"; 
private static final String RecuperarTodosAlbaran = "select  cod_albaran,f_albaran,F_entrada,cod_pro,num_factura from albaranes ";
private static final String RecuperarTodosAlbaranProveedor = "select  cod_albaran,f_albaran,F_entrada,cod_pro,num_factura from albaranes where cod_pro=?";
private static final String RecuperarTodosAlbaranProveedorS = "SELECT cod_albaran,f_albaran,F_entrada,cod_pro,num_factura " +
								" FROM ALBARANES " +
								" WHERE NUM_FACTURA IS NULL AND " +
								" F_ENTRADA >= ? AND " +
								" F_ENTRADA <= ? AND " +
								" COD_PRO IN (SELECT COD_PRO " +
											"FROM PROVEEDORES " +
											"WHERE ALB_FACT = 'S') ";
private static final String RecuperarTodosAlbaranProveedorS1 = "SELECT cod_albaran,f_albaran,F_entrada,cod_pro,num_factura " +
		" FROM ALBARANES " +
		" WHERE NUM_FACTURA IS NULL AND " +
		" F_ENTRADA >= ? AND " +
		" F_ENTRADA <= ? AND " +
		" COD_PRO IN (SELECT COD_PRO " +
					"FROM PROVEEDORES " +
					"WHERE ALB_FACT = 'S' and cod_pro = ?) ";
private static final String ModificarAlbaranNumFactura = "UPDATE ALBARANES SET NUM_FACTURA = ? WHERE COD_ALBARAN = ?  and NUM_FACTURA IS NULL";


public static final String RecuperarTodosAlbaranProveedorN = "SELECT cod_albaran,f_albaran,F_entrada,cod_pro,num_factura " +
		" FROM ALBARANES " +
		" WHERE NUM_FACTURA IS NULL AND " +
		" F_ENTRADA >= ? AND " +
		" F_ENTRADA <= ? AND " +
		" COD_PRO IN (SELECT COD_PRO " +
					"FROM PROVEEDORES " +
					"WHERE ALB_FACT = 'N')" +
		"order by cod_pro ";

private static final String RecuperarTodosAlbaranProveedorN1 = "SELECT cod_albaran,f_albaran,F_entrada,cod_pro,num_factura " +
		" FROM ALBARANES " +
		" WHERE NUM_FACTURA IS NULL AND " +
		" F_ENTRADA >= ? AND " +
		" F_ENTRADA <= ? AND " +
		" COD_PRO IN (SELECT COD_PRO " +
					"FROM PROVEEDORES " +
					"WHERE ALB_FACT = 'N' and cod_pro = ?) ";
;

private static final String RECUPERAR_CLIENTE_BY_EMAIL = "SELECT nombre, apellido1, apellido2, provincia, localidad, direccion, codigoPostal, contraseña, clase FROM clientes WHERE email= ?";

// LIN_ALB
private static final String InsertarLinAlb = "insert into lin_alb( cod_albaran,cod_alm,cod_art,F_caducidad,cant_ent,precio_ent)values (?,?,?,?,?,?)";
private static final String ModificarLinAlb = null;
private static final String BorrarLinAlb = null;
private static final String RecuperarLinAlb = null;
private static final String RecuperarTodosLinAlbAlbaran ="select cod_albaran,cod_alm,cod_art,F_caducidad,cant_ent,precio_ent from lin_alb where cod_albaran=?";
private static final String RecuperarTotalImporteAlbaran = "SELECT round(SUM(CANT_ENT * PRECIO_ENT),3) TOTAL  FROM LIN_ALB WHERE COD_ALBARAN = ?";













 


	
	public static String getInsertarCliente() {
		
		return InsertarCliente;
	}
	
	public static String getModificarCliente() {
	
		return ModificarCliente;
	}
	
	
	
	public static String getBorrarCliente() {
	
		return BorrarCliente;
	}
	
	
	public static String getRecuperarClienteByEmail() {
	
		return RECUPERAR_CLIENTE_BY_EMAIL;
	}
	
	
	public static String getRecuperarCliente() {
	
		return RecuperarCliente;
	}
	
	
	public static String getRecuperarTodosCliente() {
		
		return RecuperarTodosCliente;
	}
	
	public static String getRecuperarIva() {
	
		return RecuperarIva;
	}
	
	public static String getRecuperarTodosIva() {
		
		return RecuperarTodosIva;
	}
	
	public static String getRecuperarFormaPago() {
		
		return RecuperarFormaPago;
	}
	
	public static String getRecuperarTodosFormaPago() {
		
		return RecuperarTodosFormaPago;
	}
	
	public static String getRecuperarTarifa() {
	
		return RecuperarTarifa;
	}
	
	public static String getRecuperarTodosTarifa() {
	
		return RecuperarTodosTarifa;
	}

	public static String getInsertarProveedor() {
		
		return InsertarProveedor;
	}

	public static String getModificarProveedor() {
	
		return ModificarProveedor;
	}

	public static String getBorrarProveedor() {
		
		return BorrarProveedor;
	}

	public static String getRecuperarProveedor() {
		
		return RecuperarProveedor;
	}

	public static String getTodosProveedor() {
		
		return TodosProveedor;
	}

	public static String getRecuperarFamilia() {
	
		return RecuperarFamilia;
	}

	public static String getRecuperarTodosFamilia() {
		
		return RecuperarTodosFamilia;
	}
// almacenes
	public static String getRecuperarAlmacen() {
		
		return RecuperarAlmacen;
	}

	public static String getRecuperarTodosAlmacen() {
		
		return RecuperarTodosAlmacen;
	}
// articulos
	public static String getInsertarArticulo() {
	
		return InsertarArticulo;
	}

	public static String getModificarArticulo() {
		
		return getModificarArticulo;
	}

	public static String getBorrarArticulo() {

		return BorrarArticulo;
	}

	public static String getRecuperarArticulo() {
		
		return RecuperarArticulo;
	}

	public static String getRecuperarTodosArticulo() {
		
		return RecuperarTodosArticulo;
	}

	public static String getInsertarExistencia() {
	
		return InsertarExistencia;
	}

	public static String getModificarExistencia() {
		
		return ModificarExistencia;
	}

	public static String getBorrarExistencia() {
		
		return BorrarExistencia;
	}

	public static String getRecuperarExistencia() {
		
		return RecuperarExistencia;
	}

	public static String getRecuperarTodosExistencia() {
	
		return RecuperarTodosExistencia;
	}
// contador_fact
	public static String getModificarContadorFact() {
		
		return ModificarContadorFact;
	}

	public static String getRecuperarContadorFact() {
		
		return RecuperarContadorFact;
	}
// facturas
	public static String getInsertarFactura() {
		
		return InsertarFactura;
	}

	public static String getBorrarFactura() {
	
		return BorrarFactura;
	}

	public static String getRecuperarFactura() {
	
		return RecuperarFactura;
	}

	public static String getRecuperarTodosFactura() {
		
		return RecuperarTodosFactura;
	}
// recibos
	public static String getInsertarRecibo() {
	
		return InsertarRecibo;
	}

	public static String getBorrarRecibo() {
	
		return BorrarRecibo;
	}

	public static String getRecuperarRecibo() {
		
		return RecuperarRecibo;
	}

	public static String getRecuperarTodosRecibo() {
	
		return RecuperarTodosRecibo;
	}

	public static String getRecuperarTodosReciboFactura() {
	
		return RecuperarTodosReciboFactura;
	}

	public static String getInsertarLinPed() {
		
		return InsertarLinPed;
	}

	public static String getRecuperarPedido() {
	
		return RecuperarPedido;
	}
// lin ped 
	public static String getModificarLinPed() {
		
		return ModificarLinPed;
	}

	public static String getBorrarLinPed() {
	
		return BorrarLinPed;
	}

	public static String getRecuperarLinPed() {
	
		return RecuperarLinPed;
	}

	public static String getRecuperarTodosLinPed() {
	
		return RecuperarTodosLinPed;
	}

	public static String getInsertarPedido() {
		
		return InsertarPedido;
	}

	public static String getModificarPedido() {
	
		return ModificarPedido;
	}

	public static String getBorrarPedido() {
	
		return BorrarPedido;
	}

	public static String getRecuperarTodosPedido() {
		
		return RecuperarTodosPedido;
	}


	public static String getInsertarIva() {
		
		return InsertarIva;
	}
	
	
	
	

	public static String getInsertarClienteProcedure() {
		
		return InsertarClienteProcedure;
	}

	public static String getInsertarTarifa() {
	
		return InsertarTarifa;
	}

	public static String getModificarTarifa() {
	
		return ModificarTarifa;
	}

	public static String getModificarIva() {
	
		return ModificarIva;
	}

	public static String getInsertarFormaPago() {
		
		return InsertarFormaPago;
	}

	public static String getModificarFormaPago() {
		
		return ModificarFormaPago;
	}

	public static String getBorrarTodosLinPedPedido() {
	
		return BorrarTodosLinPedPedido;
	}

	public static String getRecuperarTodosLinPedPedido() {
	
		return RecuperarTodosLinPedPedido;
	}

	public static String getInsertarAlbaran() {

		return InsertarAlbaran;
	}

	public static String getBorrarAlbaran() {

		return BorrarAlbaran;
	}

	public static String getRecuperarAlbaran() {
		
		return RecuperarAlbaran;
	}

	public static String getRecuperarTodosAlbaranProveedor() {
	
		return RecuperarTodosAlbaranProveedor;
	}

	public static String getInsertarLinAlb() {
	
		return InsertarLinAlb;
	}

	public static String getModificarLinAlb() {
	
		return ModificarLinAlb;
	}

	public static String getBorrarLinAlb() {
		
		return BorrarLinAlb;
	}

	public static String getRecuperarLinAlb() {
	
		return RecuperarLinAlb;
	}

	public static String getRecuperarTodosLinAlbAlbaran() {
	
		return RecuperarTodosLinAlbAlbaran;
	}

	public static String getRecuperarTodosAlbaranProveedorS() {
	
		return RecuperarTodosAlbaranProveedorS;
	}

	public static String getRecuperarTotalImporteAlbaran() {
	
		return RecuperarTotalImporteAlbaran;
	}

	public static String getModificarAlbaranNumFactura() {

		return ModificarAlbaranNumFactura;
	}

	

	public static String getRecuperarTodosAlbaranProveedorN() {
		
		return RecuperarTodosAlbaranProveedorN;
	}
	public static String getRecuperarTodosAlbaranProveedorN1() {
	
		return RecuperarTodosAlbaranProveedorN1;
	}

	public static String getRecuperarTodosAlbaranProveedorS1() {
	
		return RecuperarTodosAlbaranProveedorS1;
	}

	public static String getRecuperarTodosFacturaNoRecibo() {
		
		return RecuperarTodosFacturaNoRecibo;
	}

	public static String getRecuperarTodosAlbaran() {
		
		return RecuperarTodosAlbaran;
	}

	public static String getModificarClienteConcurrente() {
	
		return ModificarClienteConcurrente;
	}

	public static String getRecuperarTodosReciboProvedor() {
		
		return RecuperarTodosReciboProvedor;
	}

	public static String getRecuperarTodosPedidoPro() {
		
		return RecuperarTodosPedidoPro;
	}

	public static String getRecuperarLinPedBloqueo() {
		
		return RecuperarLinPedBloqueo;
	}



	public static String getBorrarIva() {
		
		return BorrarIva;
	}

	
	

	

	
}
