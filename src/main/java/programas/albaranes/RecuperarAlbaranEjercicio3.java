package programas.albaranes;





import domain.Albaran;

import servicios.ServicioAlbaran;

import util.Fecha;
import exceptions.DomainException;
import exceptions.ServiceException;

public class RecuperarAlbaranEjercicio3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/* repetir ejercio 2   utilizando el metodo del servicio albaran 
		   //   public Albaran recuperarAlbaranCompleto(Albaran albaran)
		   
		salida por consola
		El albaran :1 12/01/2004 2004-01-10
		la razon social del proveedor es HP HIERBAS S.L
		la fecha de la factura del albaran 1  es 12-10-2018
		*/
		
		Albaran albaran=null;
		ServicioAlbaran sAlbaran=null;
		
	try {
		sAlbaran=new ServicioAlbaran();
		albaran=sAlbaran.recuperarAlbaranCompleto(new Albaran("12"));
		 if (albaran!=null){
			 System.out.println("El albaran :"+albaran.getCodAlbaran()+" "+Fecha.convertirAString(albaran.getFEntrada(), "dd/MM/yyyy")+" "+albaran.getFAlbaran() );
			 // para la razon social del proveedor
			 System.out.println("proveedor :"+albaran.getProveedor().getRazonSocial());
			 // para la Fecha de la factura del albaran
			 if(albaran.getFactura()!=null)
				 System.out.println("Fecha de la factura :"+albaran.getFactura().getFFactura());
			 else
				 System.out.println("con Fecha factura: pendiente de facturar" )	 ;
		
		 }else
				 System.out.println("El albaran no existe");
		
	

		
		
		
		
	// ESTA RUTINA DE ERRORES SIEMPRE		
	} catch (ServiceException e) {
		if(e.getCause()==null){
			System.out.println(e.getMessage());//Error Lógico para usuario
		}else{
			e.printStackTrace();// para administrador
			System.out.println("eror interno");//Error interno para usuario
		}
	}

	

} //fin del main
}// fin de la clase
