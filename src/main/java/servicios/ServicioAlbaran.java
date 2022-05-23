package servicios;





import java.util.List;

import domain.Albaran;
import domain.AlbaranCompleto;
import domain.LinAlb;
import domain.Proveedor;

import daos.AlbaranDAO;

import daos.FacturaDAO;
import daos.LinAlbDAO;

import daos.ProveedorDAO;
import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioAlbaran {

	public ServicioAlbaran() {

	}
	public void insertarAlbaran(Albaran albaran, List<LinAlb> lista) throws ServiceException{
		TransaccionesManager trans = null; 
		try {

			trans = new TransaccionesManager();
			AlbaranDAO albaranDAO = trans.getAlbaranDAO();
			albaran=albaranDAO.insertarAlbaran(albaran);
			// inserto todas las lineas del albaran
			LinAlbDAO linalbdao = trans.getLinAlbDAO();
			if(lista.size()!=0){		
				for(int i=0;i<lista.size();i++){
					lista.get(i).setAlbaran(albaran)	;
					linalbdao.insertarLinAlb(lista.get(i));
				}
				trans.closeCommit();	
			}else{
				trans.closeRollback();
			}



			//trans.closeCommit();
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
	public Albaran recuperarAlbaran(Albaran albaran) throws ServiceException{
		TransaccionesManager trans = null;
		AlbaranDAO albaranDAO=null;
		
		

		try {
			trans = new TransaccionesManager();
			albaranDAO = trans.getAlbaranDAO();
			albaran = albaranDAO.recuperarAlbaran(albaran);
			
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
		return albaran;
	}
	public Albaran recuperarAlbaranCompleto(Albaran albaran) throws ServiceException{
		TransaccionesManager trans = null;
		AlbaranDAO albaranDAO=null;
		ProveedorDAO proveedorDAO=null;
		FacturaDAO facturaDAO=null;
		Proveedor proveedor;
		

		try {
			trans = new TransaccionesManager();
			albaranDAO = trans.getAlbaranDAO();
			albaran = albaranDAO.recuperarAlbaran(albaran);
			
			// complementar el objeto  albaran si existe y queremos
			if (albaran!=null){
				// recuperamos todos los datos del proveedor del albaran
			proveedorDAO=trans.getProveedorDAO();
		    proveedor= proveedorDAO.recuperarProveedor(albaran.getProveedor());
			albaran.setProveedor(proveedor);
			//// recuperamos todos los datos  de la factura del albaran
			facturaDAO=trans.getFacturaDAO();
			if (albaran.getFactura()!= null)
			albaran.setFactura(facturaDAO.recuperarFactura(albaran.getFactura()));
			}

      

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
		return albaran;
	}
	public AlbaranCompleto recuperarAlbaranYDetalle(Albaran albaran) throws ServiceException{
		TransaccionesManager trans = null;
		AlbaranDAO albaranDAO=null;
		LinAlbDAO  linAlbDAO=null;
		AlbaranCompleto albaranCompleto=null;
		try {
			trans = new TransaccionesManager();
			albaranCompleto= new AlbaranCompleto();
			albaranDAO = trans.getAlbaranDAO();
			linAlbDAO = trans.getLinAlbDAO();
			 
			
			albaran = albaranDAO.recuperarAlbaran(albaran);
			if (albaran!=null){
				albaranCompleto.setAlbaran(albaran);
				albaranCompleto.setList(linAlbDAO .recuperarTodasLinAlb(albaran));
				}
	

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
		
		
		return albaranCompleto ;
	}



}
