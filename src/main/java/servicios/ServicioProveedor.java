package servicios;


import java.util.ArrayList;
import java.util.List;



import domain.FormaPago;
import domain.Iva;
import domain.Proveedor;



import daos.FormaPagoDAO;
import daos.IvaDAO;

import daos.ProveedorDAO;


import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioProveedor {



	public ServicioProveedor() {

	}

	public void insertarProveedor(Proveedor proveedor) throws ServiceException{
		TransaccionesManager trans = null;
		try {

			trans = new TransaccionesManager();
			ProveedorDAO ProveedorDAO = trans.getProveedorDAO();
			ProveedorDAO.insertarProveedor(proveedor);


			trans.closeCommit();
		} catch (DAOException e) {
			try{
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


	public int borrarProveedor(Proveedor proveedor) throws ServiceException{
		TransaccionesManager trans = null;
		int borrado=0;
		try {
			trans = new TransaccionesManager();
			ProveedorDAO proveedorDAO = trans.getProveedorDAO();
			borrado = proveedorDAO.borrarProveedor(proveedor);


			trans.closeCommit();
		} catch (DAOException e) {
			try{
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
		return borrado;
	}

	public Proveedor recuperarProveedor(Proveedor proveedor) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			ProveedorDAO proveedorDAO = trans.getProveedorDAO();
			proveedor = proveedorDAO.recuperarProveedor(proveedor);

			IvaDAO ivadao = trans.getIvaDAO();
			Iva iva = ivadao.recuperarIva(proveedor.getIva());

			FormaPagoDAO formapagodao = trans.getFormaPagoDAO();
			FormaPago formapago = formapagodao.recuperarFormaPago(proveedor.getFormaPago());

			proveedor.setIva(iva);
			proveedor.setFormaPago(formapago);


			trans.closeCommit();
		} catch (DAOException e) {
			try{
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
		return proveedor;
	}
	/*
	public int modificarConBloqueos(Proveedor cli_actualizado, Proveedor cli_anterior) throws ServiceException{
		TransaccionesManager trans = new TransaccionesManager();
		int borrado=0;
		try {

			ProveedorDAO ProveedorDAO = trans.getProveedorDAO();
			borrado = ProveedorDAO.modificarProveedorBloqueos(cli_actualizado, cli_anterior);

			trans.closeCommit();
		} catch (DAOException e) {
			trans.closeRollback();
			e.printStackTrace();
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return borrado;
	}

	 */
	public List<Proveedor> recuperarTodosProveedor() throws ServiceException{
		TransaccionesManager trans =null;
		List<Proveedor> list = new ArrayList<Proveedor>();
		try {

			trans = new TransaccionesManager();
			ProveedorDAO ProveedorDAO = trans.getProveedorDAO();
			list = ProveedorDAO.recuperarTodosProveedor();
			if(list.size()!=0){		
				for(int i=0;i<list.size();i++){
					IvaDAO ivadao = trans.getIvaDAO();
					Iva iva = ivadao.recuperarIva(list.get(i).getIva());

					FormaPagoDAO formapagodao = trans.getFormaPagoDAO();
					FormaPago formapago = formapagodao.recuperarFormaPago(list.get(i).getFormaPago());

					list.get(i).setIva(iva);
					list.get(i).setFormaPago(formapago);

				}
			}	


			trans.closeCommit();
		} catch (DAOException e) {
			try{
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return list;
	}


}
