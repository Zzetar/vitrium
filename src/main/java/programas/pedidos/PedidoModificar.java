package programas.pedidos;

import java.util.List;

import domain.LinPed;
import domain.Pedido;

import servicios.ServicioPedido;
import util.Teclado;
import exceptions.DomainException;
import exceptions.ServiceException;

public class PedidoModificar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServicioPedido sPedido = null;
		List<LinPed> lista = null;
		Pedido pedido = null;
		Teclado t = null;

		try {

			try {
				t = new Teclado();
				System.out.println(" introducir número de pedido");
				pedido = new Pedido(t.leerEntero());

			} catch (NumberFormatException e) {
				throw new ServiceException("Numero de pedido no valido");
			}
			sPedido = new ServicioPedido();
			pedido = sPedido.recuperarCabeceraPedido(pedido);
			if(pedido!=null){
			lista = sPedido.recuperarDetallePedido(pedido);
			if (lista.size() > 0){
				for(int i = 0; i < lista.size(); i++){
					if(lista.get(i).getArticulo().getCodArt().equals("2")){
						lista.remove(i);
					}
				}
				sPedido.modificarPedido(pedido, lista);
				
			}else{
				System.out.println("el pedido no tiene detalle");
			}
			}else
				System.out.println("No se ha encontrado pedido");
			
			System.out.println("proceso finalizado");

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
