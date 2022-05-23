package servicios;





import java.util.ArrayList;
import java.util.List;

import domain.Albaran;
import domain.Articulo;
import domain.Cliente;
import domain.LinAlb;
import domain.LinPed;

import daos.AlbaranDAO;
import daos.ArticuloDAO;
import daos.ClienteDAO;
import daos.LinAlbDAO;
import daos.LinPedDAO;
import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioLinAlb {

	public ServicioLinAlb() {

	}
	
	public LinAlb recuperarLinAlb( LinAlb linAlb) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			 LinAlbDAO  linAlbDAO = trans.getLinAlbDAO();
			 linAlb = linAlbDAO.recuperarLinalb(linAlb);




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
		return linAlb;
	}
	public List<LinAlb> recuperarTodosLinAlb(Albaran albaran) throws ServiceException{
		TransaccionesManager trans = null;
		List<LinAlb> list = new ArrayList<LinAlb>();
		try {

			trans = new TransaccionesManager();
			LinAlbDAO LinAlbDAO = trans.getLinAlbDAO();
			list = LinAlbDAO.recuperarTodasLinAlb(albaran);


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
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return list;
	}


}
