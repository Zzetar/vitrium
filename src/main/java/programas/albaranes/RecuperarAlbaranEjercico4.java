package programas.albaranes;





import java.util.ArrayList;
import java.util.List;

import domain.Albaran;

import domain.LinAlb;

import servicios.ServicioAlbaran;

import servicios.ServicioLinAlb;


import exceptions.DomainException;
import exceptions.ServiceException;

public class RecuperarAlbaranEjercico4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// listar la cabecera de un albaran y el detalle del albaran (todas las lineas de albaran)
		
		
		/*
		cabecera del albaran
		ALBARAN 04/06-4666
		Proveedor 1
		Fecha albaran 2020-09-09
		Fecha Entrada 2020-06-04
		Numero Factura  ¡¡¡Pendiente de facturar!!!!
		detalle del albaran
		1 2 3000-01-01 8.0 0.0
		1 3 3000-01-01 5.0 0.0
		
		*/
		
		Albaran albaran=null;
		ServicioAlbaran sAlbaran=null;
		ServicioLinAlb sLinAlb=null;
		List<LinAlb> list = new ArrayList<LinAlb>();
		
	



		try {


			sAlbaran= new ServicioAlbaran();
			albaran= new Albaran();
			albaran.setCodAlbaran("1ddddddddddddddddddddddddddddddddddddddd");
			//albaran=sAlbaran.recuperarAlbaran(new Albaran("1ddddddddddddddddddddddddddddddddddddddddd"));
			if (albaran!=null){
				System.out.println("cabecera del albaran");


				System.out.println("ALBARAN " +albaran.getCodAlbaran());
				System.out.println("Proveedor " +albaran.getProveedor().getCodPro());
				System.out.println("Fecha albaran " +albaran.getFAlbaran());
				System.out.println("Fecha Entrada " +albaran.getFEntrada());
				if(albaran.getFactura()!=null)
					System.out.println("Numero Factura " +albaran.getFactura().getNumFactura());
				else
				System.out.println("Numero Factura  " +"¡¡¡Pendiente de facturar!!!!");	 


					// recuperamos el detalle del albaran
					sLinAlb= new ServicioLinAlb();
					list =sLinAlb.recuperarTodosLinAlb(albaran);
					if(list.size()!=0){	
						System.out.println("detalle del albaran");
						for(int i=0;i<list.size();i++){// por cada linea del albaran
							System.out.println(list.get(i).getExistencia().getAlmacen().getCodAlm()+" "+
									list.get(i).getExistencia().getArticulo().getCodArt()+" "+
									list.get(i).getExistencia().getFCaducidad()+" "+
									list.get(i).getCantEnt()+" "+
									list.get(i).getPrecioEnt());
						}

					}else
						System.out.println("el albaran no tiene detalle ... IMPOSIBLE , pero bueno ....." );	 

			}else
				System.out.println("El albaran no existe" );









			// ESTA RUTINA DE ERRORES SIEMPRE		
		} catch (ServiceException| DomainException e) {
			if(e.getCause()==null){
				System.out.println(e.getMessage());//Error Lógico para usuario
			}else{
				e.printStackTrace();// para administrador
				System.out.println("eror interno");//Error interno para usuario
			}
		}



	} //fin del main
}// fin de la clase
