package servicios;

import java.util.ArrayList;
import java.util.List;


import domain.Existencia;

import daos.ExistenciaDAO;

import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioExistencia {



	public ServicioExistencia() {

	}

	public void insertarExistencia(Existencia Existencia) throws ServiceException{
		TransaccionesManager trans = null;
		try {

			trans = new TransaccionesManager();
			ExistenciaDAO ExistenciaDAO = trans.getExistenciaDAO();
			ExistenciaDAO.insertarExistencia(Existencia);


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


	public int borrarExistencia(Existencia Existencia) throws ServiceException{
		TransaccionesManager trans = null;
		int borrado=0;
		try {
			trans = new TransaccionesManager();
			ExistenciaDAO ExistenciaDAO = trans.getExistenciaDAO();
			borrado = ExistenciaDAO.borrarExistencia(Existencia);


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

	public Existencia recuperarExistencia(Existencia Existencia) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			ExistenciaDAO ExistenciaDAO = trans.getExistenciaDAO();
			Existencia = ExistenciaDAO.recuperarExistencia(Existencia);



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
		return Existencia;
	}

	public List<Existencia> recuperarTodosExistencia() throws ServiceException{
		TransaccionesManager trans = null;
		List<Existencia> list = new ArrayList<Existencia>();
		try {

			trans = new TransaccionesManager();
			ExistenciaDAO ExistenciaDAO = trans.getExistenciaDAO();
			list = ExistenciaDAO.recuperarTodosExistencia();


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
		return list;
	}
}
