package servicios;

import java.util.ArrayList;
import java.util.List;


import domain.Tarifa;

import daos.TarifaDAO;

import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioTarifa {

	public ServicioTarifa() {

	}
	public void insertarTarifa(Tarifa tarifa) throws ServiceException{
		TransaccionesManager trans = null;
		try {

			trans = new TransaccionesManager();
			TarifaDAO tarifadao = trans.getTarifaDAO();
			tarifadao.insertarTarifa(tarifa);


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
	public int modificarTarifa(Tarifa tarifa) throws ServiceException{
		TransaccionesManager trans = null;
		int modificar=0;
		try {

			trans = new TransaccionesManager();
			TarifaDAO tarifadao = trans.getTarifaDAO();
			modificar=tarifadao.modificarTarifa(tarifa);


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
	public Tarifa recuperarTarifa(Tarifa tarifa) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			TarifaDAO tarifaDAO = trans.getTarifaDAO();
			tarifa = tarifaDAO.recuperarTarifa(tarifa);


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
		return tarifa;
	}


	public List<Tarifa> RecuperarTodasTarifas() throws ServiceException{
		TransaccionesManager trans = null;
		List<Tarifa> list = new ArrayList<Tarifa>();
		try {
			trans = new TransaccionesManager();
			TarifaDAO tarifaDAO = trans.getTarifaDAO();
			list = tarifaDAO.recuperarTodosTarifa();


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
