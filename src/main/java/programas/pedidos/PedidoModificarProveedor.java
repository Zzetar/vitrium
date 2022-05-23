package programas.pedidos;

import java.util.List;

import domain.LinPed;
import domain.Pedido;
import domain.Proveedor;

import servicios.ServicioPedido;
import util.Teclado;
import exceptions.DomainException;
import exceptions.ServiceException;

public class PedidoModificarProveedor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServicioPedido sPedido = null;
		List<LinPed> lista = null;
		List<Pedido> pedidos = null;
		Pedido pedido = null;
		Proveedor proveedor;
		Teclado t = null;

		try {
			t = new Teclado();
			System.out.println("introducir proveedor");
			proveedor = new Proveedor(t.leerCadena());

			sPedido = new ServicioPedido();

			pedidos = sPedido.recuperarTodosPedido(proveedor);
			if (pedidos.size() > 0){
				for (int i = 0; i < pedidos.size(); i++){
					//pedido = new Pedido();
					 					
					pedido= sPedido.recuperarCabeceraPedido(pedidos.get(i));
					lista = sPedido.recuperarDetallePedido(pedido);
					if (lista.size() > 0){
						for(int j = 0; j < lista.size(); j++){
							if(lista.get(j).getArticulo().getCodArt().equals("1")){
								lista.remove(j);
							}
						}
						sPedido.modificarPedido(pedido, lista);
					}else{
						System.out.println("No se ha encontrado detalle del pedido");
					}
				}

			}else{
				System.out.println("No se ha recuperado ningun pedido con ese proveedor");
			}

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
