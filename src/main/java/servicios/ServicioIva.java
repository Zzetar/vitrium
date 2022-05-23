package servicios;

import java.util.ArrayList;
import java.util.List;


import domain.Iva;

import daos.IvaDAO;
import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioIva {

	public ServicioIva() {
		// TODO Auto-generated constructor stub
	}
	public void insertarIva(Iva iva) throws ServiceException{
		TransaccionesManager trans = null;
		try {

			trans = new TransaccionesManager();
			IvaDAO ivadao = trans.getIvaDAO();
			ivadao.insertarIva(iva);


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
	public int modificarIva(Iva iva) throws ServiceException{
		TransaccionesManager trans = null;
		 int modificar=0;
		try {

			trans = new TransaccionesManager();
			IvaDAO ivadao = trans.getIvaDAO();
			modificar=ivadao.modificarIva(iva);


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
	public Iva recuperarIva(Iva iva) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			IvaDAO ivaDAO = trans.getIvaDAO();
			iva = ivaDAO.recuperarIva(iva);


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
		return iva;
	}
	public Iva recuperarIvaById(String codIva) throws ServiceException{
		TransaccionesManager trans = null;
         Iva iva=null;
		try {
			trans = new TransaccionesManager();
			IvaDAO ivaDAO = trans.getIvaDAO();
			iva = ivaDAO.recuperarIvaById(codIva);


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
		return iva;
	}

	public List<Iva> recuperarTodosIvas() throws ServiceException{
		TransaccionesManager trans = null;
		List<Iva> list = new ArrayList<Iva>();
		try {
			trans = new TransaccionesManager();
			IvaDAO ivaDAO = trans.getIvaDAO();
			list = ivaDAO.recuperarTodosIva();


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
