package programas.facturas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import domain.Proveedor;
import servicios.ServicioFacturarProveedor;
import util.Fecha;
import exceptions.DomainException;
import exceptions.ServiceException;

public class Facturar {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args)  {

		ServicioFacturarProveedor sfacturarproveedor=null;
	    String  salida=null;
		
	try {
		sfacturarproveedor=new ServicioFacturarProveedor();
			try {
				sfacturarproveedor.facturarProveedor(Fecha.convertirADate("01/01/1900", "dd/MM/yyyy"),
						Fecha.convertirADate(null, "dd/MM/yyyy"),
						Fecha.fechaActualDate());
			} catch (ParseException e) {
				throw new DomainException(" fecha/s no válidas");
			}
			
	/*	try {

            sfacturarproveedor=new ServicioFacturarProveedor();
           	List<Proveedor> listaproveedores = new ArrayList<Proveedor>();

			listaproveedores.add(new Proveedor("1"));
			listaproveedores.add(new Proveedor("2"));
			listaproveedores.add(new Proveedor("3"));
			listaproveedores.add(new Proveedor("4"));
			listaproveedores.add(new Proveedor("5"));
			listaproveedores.add(new Proveedor("6"));

			
			try {
				sfacturarproveedor.facturarProveedor(Fecha.convertirADate("01/01/1900", "dd/MM/yyyy"),
						Fecha.convertirADate("01/01/2019", "dd/MM/yyyy"),
						Fecha.fechaActualDate(),
						listaproveedores);
			} catch (ParseException e) {
				throw new DomainException(" fecha/s no válidas");
			}
    */
			salida="proceso facturación terminado correctamente"; 
			// ESTA RUTINA DE ERRORES SIEMPRE		
		} catch (ServiceException |DomainException e) {
			if(e.getCause()==null){
				salida=e.getMessage();//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				salida="error interno";//Error interno para usuario
			}
		}

       System.out.println(salida);

	}

}
