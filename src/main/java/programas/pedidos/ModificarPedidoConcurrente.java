package programas.pedidos;

import java.util.ArrayList;
import java.util.List;

import domain.Articulo;
import domain.LinPed;
import domain.Pedido;

import servicios.ServicioPedido;
import util.Teclado;
import exceptions.DomainException;
import exceptions.ServiceException;

public class ModificarPedidoConcurrente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServicioPedido sPedido = null;
		Pedido pedido = null;
		List<LinPed> listaInicial = null;
		List<LinPed> listaActualizada = null;
		Teclado t = null;
		LinPed linPed=null;

		try {

			try {
				t = new Teclado();
				pedido = new Pedido(t.leerEntero());

			} catch (NumberFormatException e) {
				throw new ServiceException("Numero de pedido no valido");
			}
			sPedido = new ServicioPedido();
			pedido = sPedido.recuperarCabeceraPedido(pedido);
			
			if(pedido== null){
				throw new ServiceException("el pedido no existe");
			}	
			listaInicial = sPedido.recuperarDetallePedido(pedido);
			
			listaActualizada=new ArrayList<LinPed>() ;
			// hago un copia de la lista inicial en lista Actualizada
			for(int i = 0; i < listaInicial.size(); i++){
				linPed = new LinPed();
				linPed.setPedido(new Pedido(listaInicial.get(i).getPedido().getnPed()));
				linPed.setArticulo(new Articulo(listaInicial.get(i).getArticulo().getCodArt()));
				linPed.setCantidad(listaInicial.get(i).getCantidad());
				if (listaInicial.get(i).getCantidadServ()==null)
				linPed.setCantidadServ(null);
				else
				linPed.setCantidadServ(new Double(listaInicial.get(i).getCantidadServ().doubleValue()));
							
				listaActualizada.add(linPed);
			}
			
			
			// simulamos modificaciones en  listaActualizada
			
			if (listaActualizada.size() > 0 && listaInicial.size() > 0){
				for(int i = 0; i < listaActualizada.size(); i++){
					//listaActualizada.remove(i);
					
					if(listaActualizada.get(i).getArticulo().getCodArt().equals("2")){
						listaActualizada.get(i).setCantidad(4);						
					}
				}
				
				sPedido.modificarPedidoBloqueo(pedido, listaInicial, listaActualizada);
				
			}else{
				System.out.println("el pedido no tiene detalle");
			}
			
			
			
			System.out.println("proceso terminado");

			// ESTA RUTINA DE ERRORES SIEMPRE		
		} catch (ServiceException e) {
			if(e.getCause()==null){
				System.out.println(e.getMessage());//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				System.out.println("error interno");//Error interno para usuario
			}
		}catch (DomainException e) {
			System.out.println(e.getMessage());//Error Lógico para usuario

		}


	}

}
