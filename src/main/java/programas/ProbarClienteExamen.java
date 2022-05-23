package programas;


import servicios.ServicioCliente;

import exceptions.DomainException;
import exceptions.ServiceException;

public class ProbarClienteExamen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	ServicioCliente scliente=null;
	try {
		  
			// insertar cliente desde un servicio que llama a un objeto 
		    //dao que este a su ves llama a un procedure
		      
      
       scliente=new ServicioCliente();  
       scliente.insertarCliente("1","333",null, "calle la oca","S", "S","1","1","1");

		
		  
		 
		
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

	

} //fin del main
}// fin de la clase
