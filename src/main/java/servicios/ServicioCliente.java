package servicios;



import daos.ClienteDAO;
import daos.TransaccionesManager;
import domain.Cliente;
import exceptions.DAOException;
import exceptions.DuplicateException;
import exceptions.ServiceException;

public class ServicioCliente {


	public ServicioCliente() {

	}
	public void insertarCliente(Cliente cliente) throws ServiceException, DuplicateException {
		TransaccionesManager trans =  null;
		ClienteDAO clienteDAO=null;
		try {
			trans =  new TransaccionesManager();
			clienteDAO=trans.getClienteDAO();
			clienteDAO.insertarCliente(cliente);

			trans.closeCommit();
		} catch (DuplicateException e2) {
			throw e2;
		} catch (DAOException e) {

			try{
				if(trans!=null)
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
	public Cliente recuperarClienteByEmail(String email) throws ServiceException{
		TransaccionesManager trans = null;
		Cliente cliente= null;

		try {
			trans = new TransaccionesManager();
			ClienteDAO clientedao = trans.getClienteDAO();
			cliente = clientedao.recuperarClienteByEmail(email);




			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!=null)
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
		return cliente;
	}
}
