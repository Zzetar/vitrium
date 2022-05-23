package servicios;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import recursos.Recursos;

import util.Fecha;

import daos.FacturaDAO;
import daos.FormaPagoDAO;

import daos.ClienteDAO;
import daos.IvaDAO;
import daos.ProveedorDAO;
import daos.ReciboDAO;
import daos.TarifaDAO;
import daos.TransaccionesManager;

import domain.Cliente;
import domain.Factura;
import domain.FormaPago;
import domain.Iva;
import domain.Proveedor;
import domain.Recibo;
import domain.Tarifa;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioRecibo {


	public ServicioRecibo() {

		// TODO Auto-generated constructor stub
	}


	public void  InsertarReciboTodasFacturas() throws ServiceException {
		// una transacion  por factura todos los recibos.
		TransaccionesManager trans = null;
		ReciboDAO recibodao=null;
		FacturaDAO facturadao=null;
		ProveedorDAO proveedordao=null;
		Proveedor proveedor=null;
		FormaPagoDAO formapagodao=null;
		FormaPago formapago=null;
		int dias;
		int vencimientos;
		double importe;
		double total; 
		int contadorrecibos;
		Date fecharecibo;
		List<Factura> lista= null;




		try {
			trans = new TransaccionesManager();
			facturadao = trans.getFacturaDAO();
			recibodao = trans.getReciboDAO();
			proveedordao = trans.getProveedorDAO();
			formapagodao = trans.getFormaPagoDAO();
			lista=facturadao.recuperarTodosFacturaNoRecibo();



			if(lista.size()!=0){		
				for(int i=0;i<lista.size();i++){// por cada factura 
					contadorrecibos=0;
					proveedor=proveedordao.recuperarProveedor(lista.get(i).getProveedor());
					formapago = formapagodao.recuperarFormaPago(proveedor.getFormaPago());
					vencimientos=formapago.getNumeroVtos();
					dias=formapago.getDias();
					fecharecibo = lista.get(i).getFFactura();
					if (vencimientos==0){
						recibodao.insertarRecibo(new Recibo(lista.get(i),1,lista.get(i).getFFactura(),lista.get(i).getImporte())  )	;

					}else{
						total=0;
						// redondeo el importe del recibo al tercer decimal
						importe=Recursos.redondearAngel(lista.get(i).getImporte()/vencimientos, 3);
						for (int j=1;j<=vencimientos;j++){
							fecharecibo=Fecha.sumarDiasFecha(fecharecibo, dias);
							if (j==vencimientos){// para el ultimo recibo
								importe= lista.get(i).getImporte()-total;
								
							}else{
								total=total+importe;
							
								
							}
							recibodao.insertarRecibo(new Recibo(lista.get(i),j,fecharecibo,new Double(importe))  )	;
							contadorrecibos+=1;

						}// fin del for por calcular todos los recibos de cada factura.
					}// fin del if;	
					if(contadorrecibos==vencimientos )
						trans.commit();	
					else
						trans.rollback();

				}// fin del for por cada factura	
			}// fin de lista
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
	public void InsertarRecibo(Factura factura) throws ServiceException {
		// una transacion  por factura todos los recibos.
		TransaccionesManager trans = null;
		ReciboDAO recibodao=null;
		ProveedorDAO proveedordao=null;
		Proveedor proveedor=null;
		FormaPagoDAO formapagodao=null;
		FormaPago formapago=null;
		int dias;
		int vencimientos;
		double importe;
		double total; 
		int contadorrecibos;
		Date fecharecibo;
		




		try {
			trans = new TransaccionesManager();
			recibodao = trans.getReciboDAO();
			proveedordao = trans.getProveedorDAO();
			formapagodao = trans.getFormaPagoDAO();




			if(factura!=null){		

				contadorrecibos=0;
				proveedor=proveedordao.recuperarProveedor(factura.getProveedor());
				formapago = formapagodao.recuperarFormaPago(proveedor.getFormaPago());
				vencimientos=formapago.getNumeroVtos();
				dias=formapago.getDias();
				fecharecibo = factura.getFFactura();
				if (vencimientos==0){
					recibodao.insertarRecibo(new Recibo(factura,1,factura.getFFactura(),factura.getImporte())  )	;

				}else{
					total=0;
					// redondeo el importe del recibo al tercer decimal
					importe=Recursos.redondearAngel(factura.getImporte()/vencimientos, 3);
					for (int j=1;j<=vencimientos;j++){
						fecharecibo=Fecha.sumarDiasFecha(fecharecibo, dias);
						if (j==vencimientos){
							importe=factura.getImporte()-total;
						}else{
							total=total+importe;
						}
						recibodao.insertarRecibo(new Recibo(factura,j,fecharecibo,new Double(importe))  )	;
						contadorrecibos+=1;

					}// fin del for por calcular todos los recibos de cada factura.
				}// fin del if;	
				// validacion
				if(contadorrecibos==vencimientos )
					trans.commit();	
				else
					trans.rollback();


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
	public void  InsertarReciboTodasFacturas2() throws ServiceException {
		// una transacion  por factura todos los recibos.
		FacturaDAO facturadao=null;
		List<Factura> lista= null;
		TransaccionesManager trans = null;
		try {
			trans = new TransaccionesManager();
			facturadao = trans.getFacturaDAO();
			lista=facturadao.recuperarTodosFacturaNoRecibo();
			trans.closeCommit();
			
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
		
		if(lista.size()!=0){		
			for(int i=0;i<lista.size();i++){// por cada factura 
				
				InsertarRecibo(lista.get(i)) ;

			}// fin del for por cada factura	
		}// fin de lista
	}


	public List<Recibo> recuperarRecibos(String proveedor) throws ServiceException {
		
		TransaccionesManager trans = null;
		List<Recibo> lista = new ArrayList<Recibo>();
		try {

			trans = new TransaccionesManager();
			ReciboDAO reciboDAO = trans.getReciboDAO();
			lista = reciboDAO.recuperarRecibos(proveedor);
			
		

			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!=null)
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
		return lista;
	}
}
