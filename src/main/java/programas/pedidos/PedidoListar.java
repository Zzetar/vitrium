
		package programas.pedidos;
		import java.io.IOException;
		import java.sql.*;
import java.text.ParseException;
import java.util.List;

import domain.Articulo;
import domain.LinPed;
import domain.Pedido;

import servicios.ServicioArticulo;
import servicios.ServicioPedido;
import servicios.ServicioProveedor;
import util.Teclado;

import exceptions.ServiceException;
		
		/**
		 * 
		 * @author angel
		 *
		 */
public class PedidoListar{


		public static void main(String args[]){
		Teclado t=null;
		ServicioPedido sPedido=null;
		ServicioArticulo sArticulo=null;
		ServicioProveedor sProveedor=null;
		Pedido pedido=null;
		List<LinPed> lineasPedido=null;
		boolean correcto=true;
		double total = 0;
		   
		try {

					   
		   t=new Teclado();
		   System.out.println("Introduzca el numero de pedido");
		
		while (correcto){
			   try {
				   pedido=new Pedido( t.leerEntero());
				   correcto=false;
				   
			    } catch (NumberFormatException E) {
			    	System.out.println("Introduzca el número de pedido bien MERLUZO");
			    }  
		 
		   }// fin del wile
		   sPedido=new ServicioPedido();
		   sProveedor=new ServicioProveedor();
		   sArticulo=new ServicioArticulo();
		   
		   pedido=sPedido.recuperarCabeceraPedido(pedido);
		   if(pedido!=null){
		   
		   System.out.println(pedido.getnPed()+ "   "+pedido.getFechaPed()+ "   "+sProveedor.recuperarProveedor(pedido.getProveedor()).getRazonSocial());
		   lineasPedido=sPedido.recuperarDetallePedido(pedido);
		   for(LinPed linea:lineasPedido){
			   
			   System.out.println(sArticulo.recuperarArticulo(linea.getArticulo()).getDescripcion()+ "      "+linea.getCantidad());
			   total = total+linea.getCantidad();
			   
			   
		   }
		   System.out.println("total pedido "+total);
		   }else
			   System.out.println("El pedido no existe");  
		 
		   
		   System.out.println("todo ok");
		 
			
		   } catch (ServiceException e) {
				if(e.getCause()==null){
					System.out.println(e.getMessage());//Error Lógico para usuario
				}else{
					e.printStackTrace();// para administrador
					System.out.println("error interno");//Error interno para usuario
				}
			}	    

			     
		}
		}



