package servicios;

import java.util.ArrayList;
import java.util.List;

import domain.FormaPago;


import daos.FormaPagoDAO;


import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioFormaPago {

	public ServicioFormaPago() {

	}
	public void insertarFormaPago(FormaPago formapago) throws ServiceException{
		TransaccionesManager trans=null ;
		try {

			trans = new TransaccionesManager();
			FormaPagoDAO formapagodao = trans.getFormaPagoDAO();
			formapagodao.insertarFormaPago(formapago);


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
	}
	public int modificarFormaPago(FormaPago formapago) throws ServiceException{
		TransaccionesManager trans = null;
		int modificar=0;
		try {

			trans = new TransaccionesManager();
			FormaPagoDAO formapagodao = trans.getFormaPagoDAO();
			modificar=formapagodao.modificarFormaPago(formapago);


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
		return modificar;
	}

	public FormaPago  recuperarFormaPago(FormaPago formapago) throws ServiceException{
		TransaccionesManager trans = null;
		try {
			trans = new TransaccionesManager();
			FormaPagoDAO formapagoDAO = trans.getFormaPagoDAO();
			formapago = formapagoDAO.recuperarFormaPago(formapago);

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
		return formapago;
	}
	public FormaPago  recuperarFormaPagoById(String codigo) throws ServiceException{
		TransaccionesManager trans = null;
		FormaPago formaPago=null;
		try {
			trans = new TransaccionesManager();
			FormaPagoDAO formapagoDAO = trans.getFormaPagoDAO();
			formaPago = formapagoDAO.recuperarFormaPagoById(codigo);

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
		return formaPago;
	}

	public List<FormaPago> recuperarTodosFormasPago() throws ServiceException{
		TransaccionesManager trans = null;
		List<FormaPago> list = new ArrayList<FormaPago>();
		try {
			trans = new TransaccionesManager();
			FormaPagoDAO formapagoDAO = trans.getFormaPagoDAO();
			list = formapagoDAO.recuperarTodosFormaPago();


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
		return list;
	}
}
