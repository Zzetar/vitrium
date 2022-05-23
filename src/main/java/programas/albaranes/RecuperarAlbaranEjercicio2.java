package programas.albaranes;





import domain.Albaran;
import domain.Factura;
import domain.Proveedor;
import servicios.ServicioAlbaran;
import servicios.ServicioFactura;
import servicios.ServicioProveedor;
import util.Fecha;
import exceptions.DomainException;
import exceptions.ServiceException;

public class RecuperarAlbaranEjercicio2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Albaran albaran=null;
		ServicioAlbaran sAlbaran=null;
		Proveedor proveedor=null;
		ServicioProveedor sProveedor=null;
		Factura factura;
		ServicioFactura sFactura=null;
		
	
	try {
		
		
		
		/* ejercico 2 recuperar y visualizar  la cabecera de un albaran ,visualizando la razon social
		   del proveedor y la fecha de la factura del albaran
		
		salida por consola
		El albaran :1 12/01/2004 2004-01-10
		la razon social del proveedor es HP HIERBAS S.L
		la fecha de la factura del albaran 1  es 12-10-2018
		*/

		
		
		 sAlbaran= new ServicioAlbaran();
		 albaran=sAlbaran.recuperarAlbaran(new Albaran("12"));
		 if (albaran!=null){
			 System.out.println("El albaran :"+albaran.getCodAlbaran()+" "+Fecha.convertirAString(albaran.getFEntrada(), "dd/MM/yyyy")+" "+albaran.getFAlbaran() );
			 // para la razon social del proveedor
			 sProveedor=new ServicioProveedor();
			 proveedor=sProveedor.recuperarProveedor(albaran.getProveedor());
			 System.out.println("la razon social del proveedor es "+proveedor.getRazonSocial());
			 // para la fecha de la factura del albaran
			 if(albaran.getFactura()!=null){
			   sFactura=new ServicioFactura();	
			   factura=sFactura.recuperarFactura(albaran.getFactura());
			   System.out.println("la fecha de la factura del albaran "+albaran.getCodAlbaran()+"  es "+Fecha.convertirAString(factura.getFFactura(), "dd-MM-yyyy"));

			 }else	 System.out.println("con la factura: pendiente de facturar" )	 ;



		 }else
			 System.out.println("El albaran no existe" );
		
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
