package daos.interfaces;


import java.util.List;

import domain.Cliente;
import exceptions.DAOException;

public interface IClienteDAO extends ErroresBD {
	
	public void insertarCliente(Cliente cliente)throws DAOException;
	public int  borrarCliente(Cliente cliente)throws DAOException;
	public int  borrarClienteById(String cliente)throws DAOException;
	public int  modificarCliente(Cliente cliente)throws DAOException;
	public Cliente recuperarCliente(Cliente cliente)throws DAOException;
	public Cliente  recuperarClienteById(String cliente)throws DAOException;
	public List<Cliente>  recuperarTodosCliente()throws DAOException;
	
}
