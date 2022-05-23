
		package programas.pedidos;

import java.util.List;

import domain.Articulo;
import domain.LinPed;
import domain.Pedido;
import domain.Proveedor;

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
public class PedidoListarProveedor{


		public static void main(String args[]){
		Teclado t=null;
		ServicioPedido sPedido=null;
		ServicioArticulo sArticulo=null;
		ServicioProveedor sProveedor=null;
		List<LinPed> lineasPedido=null;
		List<Pedido> listaPedidos=null;
		Proveedor proveedor=null;
		Articulo articulo=null;
		double total=0;
		   
		try {
			sPedido= new ServicioPedido();
        	sArticulo= new ServicioArticulo();
        	sProveedor = new ServicioProveedor();
        	t = new Teclado();
        	System.out.println("Insertar un codigo proveedor");
        	 proveedor= sProveedor.recuperarProveedor(new Proveedor(t.leerCadena()));
        	
        	if(proveedor!=null){
        		System.out.print("Codigo proveedor :"+proveedor.getCodPro());
            	System.out.print("       ");
            	System.out.println("Razon Social   :"+proveedor.getRazonSocial());
        		
        	listaPedidos = sPedido.recuperarTodosPedido(proveedor);
    
        	if(listaPedidos.size()>0){
                	for(int i=0;i<listaPedidos.size();i++){
        		
        		 System.out.println(" pedido "+listaPedidos.get(i).getnPed()+"     fecha "+listaPedidos.get(i).getFechaPed());
        		 total = 0;
        		 lineasPedido=sPedido.recuperarDetallePedido(listaPedidos.get(i));
        		 System.out.println("      DESCRIPCION                   CANTIDAD");
                  for( int j= 0;j<lineasPedido.size();j++){
              
              		articulo = sArticulo.recuperarArticulo(lineasPedido.get(j).getArticulo());
              		System.out.println(articulo.getDescripcion()+"                  "+ lineasPedido.get(j).getCantidad());
              		    		
              		total = total+lineasPedido.get(j).getCantidad(); 
              	
              }//fin for de lineas
              System.out.println("Total pedido*********:"+total);
              System.out.println();
              
        	}//fin for de pedidos
        	
        	 
        	}else
        		System.out.println("no hay pedidos del proveedor introducido");
        	
        }else
        		System.out.println("el proveedor no existe");
        	
        	
        	
       	
    
					   
		 
		   System.out.println("proceso finalizado ");
		 
			
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



