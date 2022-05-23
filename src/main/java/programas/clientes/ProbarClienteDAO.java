package programas.clientes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import bbdd.ConexionMySql;
import bbdd.ConexionOracle;

import util.Teclado;
import daos.ClienteDAO;
import domain.Cliente;
import domain.FormaPago;
import domain.Iva;
import domain.Tarifa;
import exceptions.DAOException;
import exceptions.DomainException;
import exceptions.ServiceException;

public class ProbarClienteDAO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cliente cliente=null;
		List<Cliente> lista = null;
		Teclado teclado=new Teclado();
		ClienteDAO clienteDAO=null;
		int filas=0;
		Iva iva=null;
		Tarifa tarifa=null;
		FormaPago formaPago=null;
		
		Connection con;
	
	try {
		con=new ConexionOracle().getConexion();
		//con=new ConexionMySql().getConexion();
			// insertar cliente ***************************************
		cliente=new Cliente();
		cliente.setCodCli("29f19");
		cliente.setRazonSocial("zanahoria");
		cliente.setDireccion("calle Oca 25");
		cliente.setTelf("652321444");
		cliente.setOferta("S");
		cliente.setAlbFact("S");
			iva=new Iva();
			iva.setcodIva("1");
		cliente.setIva(iva);
			tarifa=new Tarifa();
			tarifa.setcodTarifa("1");
		cliente.setTarifa(tarifa);
			formaPago=new FormaPago();
			formaPago.setCodigo(null);
		cliente.setFormaPago(formaPago);
                  
        
		 
		
      
       clienteDAO=new ClienteDAO(con);  
       clienteDAO.insertarCliente(cliente);
       System.out.println("cliente  " + cliente.getCodCli()+" insertado");

		// recuperar  cliente *************************************
	       System.out.println("probando recuperar cliente");
	       
	     
		  cliente= clienteDAO.recuperarClienteById(teclado.leerCadena());
		  if (cliente==null)
			  System.out.println("el cliente no existe");	
		  else
		  System.out.println(cliente.toString());
		  
		  
		// recuperar  cliente con otro método*************************************
	       System.out.println("probando recuperar cliente por otro método");
	       
	      cliente=new Cliente("");
		  cliente=clienteDAO.recuperarCliente(cliente);
		  if (cliente==null)
			  System.out.println("el cliente no existe");	
		  else{
		  System.out.println(cliente.toString());
		  
		  }
		  
		  
		 

		
		  // borrar  cliente   *****************************************
		  System.out.println("probando borrar cliente ");
		  cliente=new Cliente();
		  cliente.setCodCli(teclado.leerCadena());
	      
		  filas=clienteDAO.borrarCliente(cliente);
		  
		  if (filas==0)
			  System.out.println("el cliente que Ud quiere borrar no existe");
		  else
			  System.out.println("el cliente ha sido  borrado");
		
		  // modificar cliente
		  System.out.println("probando Modificar cliente OJO LOS DATOS no válidados ");
		  cliente=new Cliente (new  Cliente("hhhf",
				                            "fff", null,
	                                        "C/hhhh","S","N",
	                                         new Iva("1"),
	                                         new Tarifa("1"),
	                                         new FormaPago("1")
                                            )
		                       );
		   
		  
          filas=clienteDAO.modificarCliente(cliente);
		
          if (filas==0)
			  System.out.println("el cliente que Ud quiere modificar no existe");
		  else
			  System.out.println("el cliente ha sido modificado");
			  
		  // ver todos los clientes
		  System.out.println("probando Ver todos los clientes ");
		  lista = new ArrayList<Cliente>();
		  lista=clienteDAO.recuperarTodosCliente();
		  if(lista.size()!=0){
			  for(int i=0;i<lista.size();i++){
				System.out.println(lista.get(i).toString());  
			  }	  
		  } else
			  System.out.println("no hay clientes"); 
		  
		  
		 
		
	// ESTA RUTINA DE ERRORES SIEMPRE		
	} catch (ServiceException|DomainException |DAOException e) {
		if(e.getCause()==null){
			System.out.println(e.getMessage());//Error Lógico para usuario
		}else{
			e.printStackTrace();// para administrador
			System.out.println("error interno");//Error interno para usuario
		}
	}catch (Exception e){
		e.printStackTrace();// para administrador
		System.out.println("error interno no controlado");//Error interno para usuario
	}

	

} //fin del main
}// fin de la clase
