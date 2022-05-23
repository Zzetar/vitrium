package daos.interfaces;


import java.util.List;

import domain.Iva;
import exceptions.DAOException;

public interface IIvaDAO extends ErroresBD {
	
	public void insertarIva(Iva iva)throws DAOException;
	public int  borrarIva(Iva iva)throws DAOException;
	public int  borrarIvaById(String iva)throws DAOException;
	public int  modificarIva(Iva iva)throws DAOException;
	public Iva recuperarIva(Iva iva)throws DAOException;
	public Iva  recuperarIvaById(String codIva)throws DAOException;
	public List<Iva>  recuperarTodosIva()throws DAOException;
	
}
