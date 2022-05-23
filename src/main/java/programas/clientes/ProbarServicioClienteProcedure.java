package programas.clientes;

import servicios.ServicioCliente;

import exceptions.DomainException;
import exceptions.ServiceException;

public class ProbarServicioClienteProcedure {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	ServicioCliente scliente=null;
	try {
		  
			// insertar cliente desde un servicio que llama  llama a un procedure
		      
      
       scliente=new ServicioCliente();  
       scliente.insertarClienteProcedure("1","333",null, "calle la oca","S", "S","1","1","1");

		
		  
		 System.out.println("OK");
		
	// ESTA RUTINA DE ERRORES SIEMPRE		
	} catch (ServiceException|DomainException e) {
		if(e.getCause()==null){
			System.out.println(e.getMessage());//Error Lógico para usuario
		}else{
			e.printStackTrace();// para administrador
			System.out.println("ertor interno");//Error interno para usuario
		}
	}
	

} //fin del main
}// fin de la clase
