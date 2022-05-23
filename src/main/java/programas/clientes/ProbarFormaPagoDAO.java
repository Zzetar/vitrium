package programas.clientes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbdd.ConexionOracle;


import daos.FormaPagoDAO;
import domain.FormaPago;


import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;


public class ProbarFormaPagoDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FormaPago formaPago=null;
		List<FormaPago> lista = null;
		FormaPagoDAO formaPagoDAO=null;
		
	
		Connection con=null;
	
	try {
		  
			// insertar formaPago ***************************************
		formaPago=new FormaPago();
		formaPago.setCodigo("4b");
		formaPago.setDias(null);// ojo muy importante puede ser null
		formaPago.setNumeroVtos(1);
		                
        con=new ConexionOracle().getConexion();
		 
		
      
       formaPagoDAO=new FormaPagoDAO(con);  
       formaPagoDAO.insertarFormaPago(formaPago);
       System.out.println("formaPago  " + formaPago.getCodigo()+" insertado");

		// recuperar  formaPago *************************************
	       System.out.println("probando recuperar formaPago");
	       
	     
		  formaPago= formaPagoDAO.recuperarFormaPagoById(formaPago.getCodigo());
		  if (formaPago==null)
			  System.out.println("la formaPago no existe");	
		  else
		  System.out.println(formaPago.toString());
		 

		
		 
		 
		  
		 
		
	// ESTA RUTINA DE ERRORES SIEMPRE		
	} catch (DomainException |DAOException e) {
		if(e.getCause()==null){
			System.out.println(e.getMessage());//Error Lógico para usuario
		}else{
			e.printStackTrace();// para administrador
			System.out.println("eror interno");//Error interno para usuario
		}
	} finally{		
		if(con!=null)
			try{
			con.close();
		}catch (SQLException e1){
			e1.printStackTrace();// para administrador
			System.out.println("eror interno");//Error interno para usuario
		}
	}

	

} //fin del main
}// fin de la clase
