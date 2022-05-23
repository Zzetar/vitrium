package servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import util.Fecha;


import domain.Albaran;
import domain.Factura;

import domain.Proveedor;


import daos.AlbaranDAO;
import daos.ContadorFactDAO;
import daos.FacturaDAO;


import daos.LinAlbDAO;



import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioFacturarProveedor {



	public ServicioFacturarProveedor() {

	}


	private void facturarM1(Date finicial, Date ffinal,Date ffactura) throws ServiceException {
		TransaccionesManager trans = null;
		// una transacion una factura de un albaran.
		AlbaranDAO albarandao;
		ContadorFactDAO contadorfactdao;
		LinAlbDAO linalbdao;
		FacturaDAO facturadao;
		List<Albaran> list ;
		double totalalbaran;
		int numerofactura;
		int modificado;
		try {

			trans = new TransaccionesManager();
			albarandao = trans.getAlbaranDAO();
			linalbdao= trans.getLinAlbDAO();
			contadorfactdao=trans.getContadorFactDAO();
			facturadao=trans.getFacturaDAO();

			if(Fecha.compararFechas( finicial,ffinal)>0)
				throw new DAOException("la fecha final mayor que fecha inicial");
			if(Fecha.compararFechas( ffinal,ffactura)>0)
				throw new DAOException("la fecha de la factura debe ser mayor o igual a la fecha final");

			// recupero todos los albaranes que voy a facturar
			list = albarandao.recuperarTodosAlbaranProveedorS( finicial,  ffinal	);
			if(list.size()!=0){		
				for(int i=0;i<list.size();i++){// por cada albaran
					// recupero el importe total del albaran.
					totalalbaran=linalbdao.RecuperarImporteTotalAlbaran(list.get(i));	
					// recupero el numero de factura y bloqueo el contador de facturas.
					numerofactura=contadorfactdao.recuperarContadorFact();
					// inserto la factura;
					facturadao.insertarFactura(new Factura(numerofactura,ffactura,totalalbaran,list.get(i).getProveedor()));
					// Modifico albaran que no han sido facturados;
					list.get(i).setFactura(new Factura(numerofactura)); 
					modificado=albarandao.modificarAlbaranNumFactura(list.get(i));

					// validacion
					if(modificado==1)
						trans.commit();// libero el contador de facturas y resto de bloqueos-
					else
						trans.rollback();


				}
			}	


			trans.close();	
		} catch (DAOException e) {

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}



	}
	private void facturarM3(Date finicial, Date ffinal,Date ffactura,List<Proveedor> listaprov) throws ServiceException {
		TransaccionesManager trans = null;
		// una transacion una factura de un albaran.
		AlbaranDAO albarandao;
		ContadorFactDAO contadorfactdao;
		LinAlbDAO linalbdao;
		FacturaDAO facturadao;
		List<Albaran> list ;
		double totalalbaran;
		int numerofactura;
		int modificado;
		try {

			trans = new TransaccionesManager();
			albarandao = trans.getAlbaranDAO();
			linalbdao= trans.getLinAlbDAO();
			contadorfactdao=trans.getContadorFactDAO();
			facturadao=trans.getFacturaDAO();

			if(Fecha.compararFechas( finicial,ffinal)>0)
				throw new DAOException("la fecha final mayor que fecha inicial");
			if(Fecha.compararFechas( ffinal,ffactura)>0)
				throw new DAOException("la fecha de la factura debe ser mayor o igual a la fecha final");


			if(listaprov.size()!=0){		
				for(int j=0;j<listaprov.size();j++){// por cada proveedor 
					list = albarandao.recuperarTodosAlbaranProveedorS1( finicial,  ffinal,listaprov.get(j)	);
					if(list.size()!=0){
						for(int i=0;i<list.size();i++){// por cada albaran
							// recupero el importe total del albaran.
							totalalbaran=linalbdao.RecuperarImporteTotalAlbaran(list.get(i));	
							// recupero el numero de factura y bloqueo el contador de facturas.
							numerofactura=contadorfactdao.recuperarContadorFact();
							// inserto la factura;
							facturadao.insertarFactura(new Factura(numerofactura,ffactura,totalalbaran,list.get(i).getProveedor()));
							// Modifico albaran que no han sido facturados;
							list.get(i).setFactura(new Factura(numerofactura)); 
							modificado=albarandao.modificarAlbaranNumFactura(list.get(i));

							// validacion
							if(modificado==1)
								trans.commit();// libero el contador de facturas y resto de bloqueos-
							else
								trans.rollback();

						}// fin for  de cada albaran
					} // fin de los albaranes del proveedor
				}// fin del for por cada proveedor.
			}// fin de todos los proveedores
			trans.close();	
		} catch (DAOException e) {

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}



	}
	private void facturarM4(Date finicial, Date ffinal,Date ffactura) throws ServiceException {
		// una transacion una factura de un proveedor,con todos los albaranes del proveedor.
		TransaccionesManager trans = null;
		AlbaranDAO albarandao;
		ContadorFactDAO contadorfactdao;
		LinAlbDAO linalbdao;
		FacturaDAO facturadao;
		List<Albaran> list ;// lista de todos los albaranes que voy a facturar
		List<Albaran> listalba ;// lista de albaranes de un proveedor
		double total; // suma de todos los albaranes de un proveedor.
		int numerofactura;
		int modificado;
		try {

			trans = new TransaccionesManager();
			albarandao = trans.getAlbaranDAO();
			linalbdao= trans.getLinAlbDAO();
			contadorfactdao=trans.getContadorFactDAO();
			facturadao=trans.getFacturaDAO();

			if(Fecha.compararFechas( finicial,ffinal)>0)
				throw new DAOException("la fecha final mayor que fecha inicial");
			if(Fecha.compararFechas( ffinal,ffactura)>0)
				throw new DAOException("la fecha de la factura debe ser mayor o igual a la fecha final");

			// recupero todos los albaranes que voy a facturar ordenados por proveedor
			list = albarandao.recuperarTodosAlbaranProveedorN( finicial,  ffinal	);

			if(list.size()!=0){
				// hago una ruptura de control por proveedor
				int i=0;
				String auxiliar;
				while (i<list.size()){
					total=0;
					listalba = new ArrayList<Albaran>();
					auxiliar=list.get(i).getProveedor().getCodPro();
					while(i<list.size() && auxiliar.equals(list.get(i).getProveedor().getCodPro()) ){
						// recupero el importe total del albaran.
						total=total+linalbdao.RecuperarImporteTotalAlbaran(list.get(i));	
						listalba.add(list.get(i)) ;
						i=i+1;

					}// fin del while anidado

					// iniciamos el proceso de facturacion.	
					// recupero el numero de factura y bloqueo el contador de facturas.
					numerofactura=contadorfactdao.recuperarContadorFact();
					// inserto la factura;
					facturadao.insertarFactura(new Factura(numerofactura,ffactura,total,new Proveedor(auxiliar)));
					// Modifico todos los  albaranes que no han sido facturados del proveedor;
					modificado=0;

					for(int j=0;j<listalba.size();j++){
						listalba.get(j).setFactura(new Factura(numerofactura)); 
						modificado=modificado+albarandao.modificarAlbaranNumFactura(listalba.get(j));

					}
					// validacion
					if(modificado==listalba.size())
						trans.commit();// libero el contador de facturas y resto de bloqueos-
					else
						trans.rollback();


				}// fin del primer while

			}// fin del if
			trans.close();	
		} catch (DAOException e) {

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
	}
	private void facturarM5(Date finicial, Date ffinal,Date ffactura,List<Proveedor> listaprov) throws ServiceException {
		// una transacion una factura de un proveedor,con todos los albaranes del proveedor.
		TransaccionesManager trans = null;
		AlbaranDAO albarandao;
		ContadorFactDAO contadorfactdao;
		LinAlbDAO linalbdao;
		FacturaDAO facturadao;
		List<Albaran> list ;// lista de todos los albaranes que voy a facturar
		double total; // suma de todos los albaranes de un proveedor.
		int numerofactura;
		int modificado;
		try {

			trans = new TransaccionesManager();
			albarandao = trans.getAlbaranDAO();
			linalbdao= trans.getLinAlbDAO();
			contadorfactdao=trans.getContadorFactDAO();
			facturadao=trans.getFacturaDAO();

			if(Fecha.compararFechas( finicial,ffinal)>0)
				throw new DAOException("la fecha final mayor que fecha inicial");
			if(Fecha.compararFechas( ffinal,ffactura)>0)
				throw new DAOException("la fecha de la factura debe ser mayor o igual a la fecha final");

			if(listaprov.size()!=0){		
				for(int j=0;j<listaprov.size();j++){// por cada proveedor 
					list = albarandao.recuperarTodosAlbaranProveedorN1( finicial,  ffinal,listaprov.get(j)	);
					total=0;
					modificado=0;
					if(list.size()!=0){
						// recupero el importe total de todos los albaran.
						for(int i=0;i<list.size();i++){// por cada albaran

							total=total+linalbdao.RecuperarImporteTotalAlbaran(list.get(i));	

						} 
						// recupero el numero de factura y bloqueo el contador de facturas.
						numerofactura=contadorfactdao.recuperarContadorFact();
						// inserto la factura;
						facturadao.insertarFactura(new Factura(numerofactura,ffactura,total,listaprov.get(j)));
						// Modifico  todos los albaran que no han sido facturados;
						for(int i=0;i<list.size();i++){// por cada albaran
							list.get(i).setFactura(new Factura(numerofactura)); 
							modificado=modificado+albarandao.modificarAlbaranNumFactura(list.get(i));
						} 


						// validacion
						if(modificado==list.size())
							trans.commit();// libero el contador de facturas y resto de bloqueos-
						else
							trans.rollback();


					} // fin de los albaranes del proveedor
				}// fin del for por cada proveedor.
			}// fin de todos los proveedores
			trans.close();	 


		} catch (DAOException e) {

			try{
				if(trans!= null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
	}
	public synchronized void  facturarProveedor(Date finicial, Date ffinal,Date ffactura,List<Proveedor> listaprov) throws ServiceException {

		// no hacemos transsaciones, estas se hacen en los metodos privados
		facturarM3( finicial,  ffinal, ffactura,listaprov);
		facturarM5( finicial,  ffinal, ffactura,listaprov);





	}
	public synchronized void  facturarProveedor(Date finicial, Date ffinal,Date ffactura) throws ServiceException {

		// no hacemos transsaciones, estas se hacen en los metodos privados

		facturarM1( finicial,  ffinal, ffactura);
		facturarM4( finicial,  ffinal, ffactura);


	}
}

