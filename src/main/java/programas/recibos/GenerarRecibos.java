package programas.recibos;



import servicios.ServicioRecibo;

import exceptions.DomainException;
import exceptions.ServiceException;

public class GenerarRecibos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	ServicioRecibo srecibo=null;
	String salida=null;
	try {
		  
			
		      
      
       srecibo=new ServicioRecibo();  
       srecibo.InsertarReciboTodasFacturas();
      // srecibo.InsertarReciboTodasFacturas2();

		
		  
       salida="proceso generar recibos terminado correctamente";  
		
	// ESTA RUTINA DE ERRORES SIEMPRE		
	} catch (ServiceException|DomainException  e) {
		if(e.getCause()==null){
			salida=e.getMessage();//Error Lógico para usuario
		}else{
			e.printStackTrace();// para administrador
			salida="error interno";//Error interno para usuario
		}
	}
	System.out.println(salida);

	

} //fin del main
}// fin de la clase
