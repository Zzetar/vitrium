package programas.recibos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import servicios.ServicioFacturarProveedor;
import servicios.ServicioRecibo;
import util.Fecha;
import util.Teclado;
import domain.Proveedor;
import domain.Recibo;
import exceptions.DomainException;
import exceptions.ServiceException;

public class ListarRecibos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServicioRecibo sRecibo=null;
		List<Recibo> lista = null;
		String proveedor =null;
		
				
				try {
					System.out.println("introducir una cadena con el codigo del proveedor");
					proveedor=new Teclado().leerCadena();
		            sRecibo=new ServicioRecibo();
		            System.out.println("recibos del proveedor "+proveedor);
		           	lista=sRecibo.recuperarRecibos(proveedor);
		           	for(int i=0;i<lista.size();i++){
		           		System.out.println(lista.get(i).getFactura().getNumFactura()+" "+
		           				           lista.get(i).getNum()+"  "+
		           				           lista.get(i).getFVto()+"  "+
		           				           lista.get(i).getImporte()
		           				           );
		           	  }
		           	if(lista.size()==0)
		           	 System.out.println("el proveedor "+proveedor+ " no tiene recibos");
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
