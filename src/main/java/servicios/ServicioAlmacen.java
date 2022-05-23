package servicios;

import java.util.ArrayList;
import java.util.List;

import domain.Almacen;
import daos.AlmacenDAO;
import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioAlmacen {

	public ServicioAlmacen() {
		// TODO Auto-generated constructor stub
	}

	public Almacen recuperarAlmacen(Almacen Almacen) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			AlmacenDAO AlmacenDAO = trans.getAlmacenDAO();
			Almacen = AlmacenDAO.recuperarAlmacen(Almacen);


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
		return Almacen;
	}

	public List<Almacen> recuperarTodosAlmacen() throws ServiceException{
		TransaccionesManager trans = null;
		List<Almacen> list = new ArrayList<Almacen>();
		try {
			trans = new TransaccionesManager();
			AlmacenDAO AlmacenDAO = trans.getAlmacenDAO();
			list = AlmacenDAO.recuperarTodosAlmacen();


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
