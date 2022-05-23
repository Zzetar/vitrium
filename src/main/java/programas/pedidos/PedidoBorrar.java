package programas.pedidos;



import servicios.ServicioPedido;

import util.Teclado;

import domain.Pedido;
import exceptions.DomainException;
import exceptions.ServiceException;

public class PedidoBorrar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Teclado t= null;
        ServicioPedido sped=null;
      
        Pedido ped= null;
        
        
        int hecho=0;
        try{
     	   t= new Teclado();
     	   sped= new ServicioPedido();
     	  
     	  
		try{
     	   System.out.println("introducir  un numero de pedido");
     	              ped=new Pedido( t.leerEntero());
        }catch(NumberFormatException e){
     	   throw new ServiceException ("Debes ingresar un numero ");
        }
     	    		  
     		   
     		      hecho = sped.borrarPedido(ped);
     		      if(hecho>0){
     		    	  System.out.println("pedido borrado");
     		      }else
     		    	 System.out.println("el pedido no existe");
     	
     	   
     	   System.out.println("Programa finalizado");
        }catch( ServiceException|DomainException e){
     	    if(e.getCause()==null){
     	        System.out.println(e.getMessage());//Error Lógico para usuario
     	    }else{
     	        e.printStackTrace();// para administrador
     	        System.out.println("eror interno");//Error interno para usuario
     	 }
     }
	}

}
