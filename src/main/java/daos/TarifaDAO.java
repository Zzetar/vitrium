package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;

import daos.interfaces.ITarifaDAO;
import domain.Tarifa;

import exceptions.DAOException;


public class TarifaDAO  implements ITarifaDAO{
	
	
	private Connection con;

	public TarifaDAO(Connection con) {
		this.con = con;
	}
	public void insertarTarifa(Tarifa tarifa) throws DAOException{
		PreparedStatement st = null;
		
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarTarifa());
			st.setString(1, tarifa.getCodTarifa());
			st.setString(2, tarifa.getDescripcion());
			
			// rutina de verificacion de mas de una FK
			
			
			
			
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" Tarifa ya existe");
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			
		}	
	}
	public int modificarTarifa(Tarifa tarifa)throws DAOException{
		PreparedStatement st = null;
		int modificado=0;
		
		try {
			st = con.prepareStatement(DbQuery.getModificarTarifa());
			
			st.setString(1, tarifa.getDescripcion());
			st.setString(2, tarifa.getCodTarifa());
			
			// rutina de verificacion de mas de una FK
			
			
			
			
			
					
		 modificado=st.executeUpdate();
		} catch (SQLException e) {
			if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			
		}
		return modificado;	
	}
	public Tarifa recuperarTarifa(Tarifa tarifa) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs =null ;
		Tarifa t=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarTarifa());
				st.setString(1,tarifa.getCodTarifa() );
				rs=st.executeQuery();
				if (rs.next()){
					t=new Tarifa(rs.getString(1),
							    rs.getString(2)); 
				}	
				

			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return t;
	}
	@Override
	public Tarifa recuperarTarifaById(String tarifa) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Tarifa objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarTarifa());
				st.setString(1,tarifa );
				rs=st.executeQuery();
				if (rs.next()){
					objeto=new Tarifa(rs.getString(1),
							    rs.getString(2)); 
				}	
				

			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	public List<Tarifa> recuperarTodosTarifa() throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Tarifa> list = new ArrayList<Tarifa>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosTarifa());
			rs = st.executeQuery();
			while (rs.next()) {
				
				list.add(new Tarifa(rs.getString(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;

	}
	@Override
	public int borrarTarifa(Tarifa tarifa) throws DAOException {
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarIva());
			st.setString(1, tarifa.getCodTarifa());
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar tarifa");
				
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
	}
	@Override
	public int borrarTarifaById(String tarifa) throws DAOException {
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarIva());
			st.setString(1, tarifa);
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar tarifa");
				
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
		
	}
	
	
}
