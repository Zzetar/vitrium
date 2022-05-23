package daos.interfaces;


import java.util.List;

import domain.Tarifa;
import exceptions.DAOException;

public interface ITarifaDAO extends ErroresBD {
	
	public void insertarTarifa(Tarifa tarifa)throws DAOException;
	public int  borrarTarifa(Tarifa tarifa)throws DAOException;
	public int  borrarTarifaById(String tarifa)throws DAOException;
	public int  modificarTarifa(Tarifa tarifa)throws DAOException;
	public Tarifa  recuperarTarifa(Tarifa tarifa)throws DAOException;
	public Tarifa recuperarTarifaById(String tarifa)throws DAOException;
	public List<Tarifa>  recuperarTodosTarifa()throws DAOException;
	
}
