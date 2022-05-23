package programas.clientes;


import domain.Cliente;
import domain.FormaPago;
import domain.Iva;
import domain.Tarifa;
import exceptions.DomainException;


public class ProbarClienteDominio {

	public static void main(String[] args) {
		Cliente cliente=null;
		Iva iva=null;
		Tarifa tarifa=null;
		FormaPago formaPago=null;
	
	try {
		  
			// crear cliente
		cliente=new Cliente();
		cliente.setCodCli("ggg");
		cliente.setRazonSocial("zanahoria");
		cliente.setDireccion("calle Oca 25");
		cliente.setTelf("652321444");
		cliente.setOferta("S");
		cliente.setAlbFact("S");
			iva=new Iva();
			iva.setcodIva("angel");
	    	cliente.setIva(iva);
			tarifa=new Tarifa();
			tarifa.setcodTarifa("1");
		cliente.setTarifa(tarifa);
			formaPago=new FormaPago();
			formaPago.setCodigo("1");
		cliente.setFormaPago(formaPago);
		System.out.println("cliente  validado constructor 1");
  
 System.out.println("cliente  validado constructor 2");
  // de otra forma solo aconsejable MUY EXPERTOS
    iva=new Iva();
	iva.setcodIva("2");
	tarifa=new Tarifa();
	tarifa.setcodTarifa("2");
	formaPago=new FormaPago();
	formaPago.setCodigo("2");
	
 
			cliente =new Cliente(new Cliente("345",
					                          "ffff",
					                          "478242424",
					                          "C/ gggg",
					                          "S",
					                          "N",
					                          iva,
					                          tarifa,
					                          formaPago)
			                           );
			System.out.println("cliente  validado constructor 3");
			
					                          
	} catch (DomainException  e) {
		if(e.getCause()==null){
			System.out.println(e.getMessage());//Error Lógico para usuario
		}else{
			e.printStackTrace();// para administrador
			System.out.println("error interno");//Error interno para usuario
		}
	}				                          
}// fin main
}// fin clase
