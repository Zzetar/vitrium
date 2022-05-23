package servicios;

import java.util.ArrayList;
import java.util.List;

import domain.Familia;
import daos.FamiliaDAO;
import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioFamilia {

	public ServicioFamilia() {
		// TODO Auto-generated constructor stub
	}

	public Familia recuperarFamilia(Familia Familia) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			FamiliaDAO FamiliaDAO = trans.getFamiliaDAO();
			Familia = FamiliaDAO.recuperarFamilia(Familia);


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
		return Familia;
	}

	public List<Familia> recuperarTodosFamilias() throws ServiceException{
		TransaccionesManager trans = null;
		List<Familia> list = new ArrayList<Familia>();
		try {
			trans = new TransaccionesManager();
			FamiliaDAO FamiliaDAO = trans.getFamiliaDAO();
			list = FamiliaDAO.recuperarTodosFamilia();


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
