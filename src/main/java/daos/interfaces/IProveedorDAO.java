package daos.interfaces;


import java.util.List;

import domain.Proveedor;
import exceptions.DAOException;

public interface IProveedorDAO extends ErroresBD {
	
	public void insertarProveedor(Proveedor proveedor)throws DAOException;
	public int  borrarProveedor(Proveedor proveedor)throws DAOException;
	public int  borrarProveedorById(String proveedor)throws DAOException;
	public int  modificarProveedor(Proveedor proveedor)throws DAOException;
	public void recuperarProveedor(Proveedor proveedor)throws DAOException;
	public Proveedor  recuperarProveedorById(String proveedor)throws DAOException;
	public List<Proveedor>  recuperarTodosProveedor()throws DAOException;
	
}
