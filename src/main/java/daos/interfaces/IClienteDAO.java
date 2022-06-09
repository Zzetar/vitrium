package daos.interfaces;


import java.util.List;

import domain.Cliente;
import exceptions.DAOException;

public interface IClienteDAO extends ErroresBD {
	
	public void insertarCliente(Cliente cliente)throws DAOException;
	
}
