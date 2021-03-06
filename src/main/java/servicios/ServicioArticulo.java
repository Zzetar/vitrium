package servicios;

import java.util.ArrayList;
import java.util.List;


import domain.Articulo;

import daos.ArticuloDAO;

import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioArticulo {



	public ServicioArticulo() {

	}

	public void insertarArticulo(Articulo Articulo) throws ServiceException{
		TransaccionesManager trans = null;
		try {

			trans = new TransaccionesManager();
			ArticuloDAO ArticuloDAO = trans.getArticuloDAO();
			ArticuloDAO.insertarArticulo(Articulo);


			trans.closeCommit();
		} catch (DAOException e) {
			try{
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error L?gico
			}else{

				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
	}


	public int borrarArticulo(Articulo Articulo) throws ServiceException{
		TransaccionesManager trans = null;
		int borrado=0;
		try {
			trans = new TransaccionesManager();
			ArticuloDAO ArticuloDAO = trans.getArticuloDAO();
			borrado = ArticuloDAO.borrarArticulo(Articulo);


			trans.closeCommit();
		} catch (DAOException e) {
			try{
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error L?gico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return borrado;
	}

	public Articulo recuperarArticulo(Articulo Articulo) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			ArticuloDAO ArticuloDAO = trans.getArticuloDAO();
			Articulo = ArticuloDAO.recuperarArticulo(Articulo);



			trans.closeCommit();
		} catch (DAOException e) {
			try{
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){

				throw new ServiceException(e.getMessage());//Error L?gico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return Articulo;
	}

	public List<Articulo> recuperarTodosArticulo() throws ServiceException{
		TransaccionesManager trans = null;
		List<Articulo> list = new ArrayList<Articulo>();
		try {

			trans = new TransaccionesManager();
			ArticuloDAO ArticuloDAO = trans.getArticuloDAO();
			list = ArticuloDAO.recuperarTodosArticulo();


			trans.closeCommit();
		} catch (DAOException e) {
			try{
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error L?gico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return list;
	}
}
