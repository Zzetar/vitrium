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

public class RecuperarAlbaranEjercicio1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Albaran albaran=null;
		ServicioAlbaran sAlbaran=null;
		
		
	
	try {
		// ejercico 1 recuperar  y visualizar  la cabecera de un albaran   
		
		/*
		 El albaran :5 19/01/2004 2004-01-16
         del proveedor:2
         con la factura:9
		 */
	
		 sAlbaran= new ServicioAlbaran();
		 albaran=sAlbaran.recuperarAlbaran(new Albaran("102a"));
		 if (albaran==null){ throw new ServiceException("El albaran no existe");}
			 System.out.println("El albaran :"+albaran.getCodAlbaran()+" "+Fecha.convertirAString(albaran.getFEntrada(), "dd/MM/yyyy")+" "+albaran.getFAlbaran() );
			 System.out.println("del proveedor:"+albaran.getProveedor().getCodPro());
			  if (albaran.getFactura()!=null)
				 System.out.println("con la factura:"+albaran.getFactura().getNumFactura());
			 else
				 System.out.println("con la factura: pendiente de facturar" )	 ;
		
		 
		
		
		
		
		
		
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
