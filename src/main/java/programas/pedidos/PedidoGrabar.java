package programas.pedidos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import servicios.ServicioPedido;
import util.Fecha;
import util.Teclado;

import domain.Articulo;
import domain.LinPed;
import domain.Pedido;
import domain.Proveedor;
import exceptions.DomainException;
import exceptions.ServiceException;

public class PedidoGrabar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		java.sql.Date fecha;
		Proveedor proveedor;
		Pedido pedido;
		Articulo articulo;
		double cantidad; 
		LinPed linped;
		List<LinPed> lista = new ArrayList<LinPed>();
		String mas="S";
		Teclado teclado= new Teclado();
	try {
		// entrada de datos:
		// fecha del pedido
		
		   
		   try {
			   System.out.println("fecha del pedido  con formato dd/MM/yyyy ");
			 fecha=Fecha.convertirADate(teclado.leerCadena(),"dd/MM/yyyy");
		     } catch (ParseException e) {
			throw new DomainException("la fecha del pedido no es válida");
		    }
		   // Proveedor  del pedido
		   System.out.println("proveedor  del pedido");
		   // creamos el pedido	 
		 	 pedido= new Pedido(fecha);
		   // pedir  por teclado datos de las lineas del pedido;
		 	System.out.println("Quiere introducir lineas de pedido S/N");
		 	mas=teclado.leerCadena();
		 	while (mas.equals("S")|| mas.equals("s")){
		 		try{
		 		System.out.println("Introducir articulo");	
		 		articulo= new Articulo();
		 		articulo.setCodArt(teclado.leerCadena());
		 		System.out.println("Introducir cantidad");
		 		cantidad=teclado.leerNumero();
		 		linped= new LinPed(pedido,articulo,cantidad);
		 		añadirALista( lista, linped );
		 		//lista.add(linped);
		 		
		 		}catch (DomainException e) {
					System.out.println(e.getMessage());
					
				}catch (NumberFormatException e) {
					System.out.println("numero no valido");
					
				}
		 		
		 		
		 		
		 	System.out.println("Quiere seguir introduciendo lineas de pedido S/N");
		 	mas=teclado.leerCadena();
		 	}
		   
		 	// grabar el pedido( una fila en pedidos y muchas en LINPED
			ServicioPedido serviciopedido=new ServicioPedido();
			serviciopedido.insertarPedidoCompleto(pedido, lista);
			System.out.println("proceso finalizado correctamente");
			
		} catch (ServiceException |DomainException e) {
			if(e.getCause()==null){
				System.out.println(e.getMessage());//Error Lógico
			}else{
				e.printStackTrace();
				System.out.println(e.getMessage());//Error interno
			}
		}


	}

	private static void  añadirALista( List<LinPed> lista,LinPed linped ){

		if(lista.size()!=0){

			int i=0;
			boolean encontrado=false;
			while(i<lista.size() && !encontrado ){
				if(lista.get(i).getArticulo().getCodArt().equals(linped.getArticulo().getCodArt())){
					lista.get(i).setCantidad(lista.get(i).getCantidad()+ linped.getCantidad()); 
					encontrado=true;				  
				} else i++;
			} 
			if (!encontrado )
				lista.add(linped);	
		}else{

			lista.add(linped);

		}
	
	}

}
