package daos.interfaces;


import java.util.List;

import domain.FormaPago;
import exceptions.DAOException;

public interface IFormaPagoDAO extends ErroresBD {
	
	public void insertarFormaPago(FormaPago formaPago)throws DAOException;
//  public int  borrarFormaPago(FormaPago formaPago)throws DAOException;
//  public int  borrarFormaPagoById(String formaPago)throws DAOException;
	public int  modificarFormaPago(FormaPago formaPago)throws DAOException;
	public FormaPago  recuperarFormaPago(FormaPago formaPago)throws DAOException;
	public FormaPago recuperarFormaPagoById(String formaPago)throws DAOException;
	public List<FormaPago>  recuperarTodosFormaPago()throws DAOException;
	
}
