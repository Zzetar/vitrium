package programas.clientes;

import java.util.ArrayList;
import java.util.List;
import servicios.ServicioCliente;
import util.Teclado;
import domain.Cliente;
import domain.FormaPago;
import domain.Iva;
import domain.Tarifa;
import exceptions.DomainException;
import exceptions.ServiceException;

public class ProbarClienteDAOServicio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cliente cliente=null;
		List<Cliente> lista = null;
		Teclado teclado=new Teclado();
		ServicioCliente scliente=null;
		int filas=0;
		Iva iva=null;
		Tarifa tarifa=null;
		FormaPago formaPago=null;
	
	try {
		  
			// insertar cliente
		cliente=new Cliente();
		cliente.setCodCli("PrrdeLosPa");
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
			formaPago.setCodigo("1");
		cliente.setFormaPago(formaPago);
                  
 
		 
		
      
       scliente=new ServicioCliente();  
       scliente.insertarCliente(cliente);
       System.out.println("cliente  " + cliente.getCodCli()+" insertado");
/*
		// recuperar  cliente 
	       System.out.println("probando recuperar cliente");
	       
	      cliente = new Cliente();
	      cliente.setCodCli(teclado.leerCadena());
		  cliente= scliente.recuperarCliente(cliente);
		  if (cliente==null)
			  System.out.println("el cliente no existe");	
		  else
		  System.out.println(cliente.toString());
		 
		  System.out.println("probando recuperar cliente completo");
		  cliente = new Cliente();
	      cliente.setCodCli(teclado.leerCadena());
		  cliente= scliente.recuperarClienteCompleto(cliente);
		  if (cliente==null)
			  System.out.println("el cliente no existe");	
		  else
		   System.out.println(cliente.toStringFashion());
		
		  // borrar  cliente   
		  System.out.println("probando borrar cliente ");
		  cliente = new Cliente();
	      cliente.setCodCli(teclado.leerCadena());
		  
	      
		  filas=scliente.borrarCliente(cliente);
		  
		  if (filas==0)
			  System.out.println("el cliente que Ud quiere borrar no existe");
		  else
			  System.out.println("el cliente ha sido  borrado");
		
		  // modificar cliente
		  System.out.println("probando Modificar cliente ");
		  cliente=new Cliente(new Cliente("hhhf", "fff", null,
	                                   "C/hhhh","S","N",
	                                   new Iva("1"),
	                                   new Tarifa("1"),
	                                   new FormaPago("1")
                                       ));	   
		  
          filas=scliente.modificarCliente(cliente);
		
          if (filas==0)
			  System.out.println("el cliente que Ud quiere modificar no existe");
		  else
			  System.out.println("el cliente ha sido modificado");
		  // ver todos los clientes
		  System.out.println("probando Ver todos los clientes ");
		  lista = new ArrayList<Cliente>();
		  lista=scliente.recuperarTodosCliente();
		  if(lista.size()!=0){
			  for(int i=0;i<lista.size();i++){
				System.out.println(lista.get(i).toString());  
			  }	  
		  } else
			  System.out.println("no hay clientes"); 
		  System.out.println("*******************vista Fashion*****************"); 
		  System.out.println("*******************vista Fashion*****************");
		  lista = new ArrayList<Cliente>();
		  lista=scliente.recuperarTodosClienteCompleto();
		  if(lista.size()!=0){
			  for(int i=0;i<lista.size();i++){
				System.out.println(lista.get(i).toStringFashion());  
			  }	  
		  } else
			  System.out.println("no hay clientes"); 
	*/	  
		  
	 
		
	// ESTA RUTINA DE ERRORES SIEMPRE		
	} catch (ServiceException|DomainException  e) {
		if(e.getCause()==null){
			System.out.println(e.getMessage());//Error Lógico para usuario
		}else{
			e.printStackTrace();// para administrador
			System.out.println("error interno");//Error interno para usuario
		}
	}

	

} //fin del main
}// fin de la clase
