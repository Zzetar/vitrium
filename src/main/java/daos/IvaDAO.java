package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import daos.interfaces.IIvaDAO;
import domain.Iva;
import exceptions.DAOException;


public class IvaDAO  implements IIvaDAO{
	
	
	private Connection con;

	public IvaDAO(Connection con) {
		this.con = con;
	}
	public void insertarIva(Iva iva)throws DAOException{
		PreparedStatement st = null;
		
		
		try {
			
			st = con.prepareStatement(DbQuery.getInsertarIva());
			st.setString(1, iva.getcodIva());
			st.setDouble(2, iva.gettipoIva());
			
			// rutina de verificacion de mas de una FK
			
			
			
			
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" Iva ya existe");
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);
			
		}	
	}
	public int modificarIva(Iva iva)throws DAOException{
		PreparedStatement st = null;
		int modificado=0;
		
		try {
			st = con.prepareStatement(DbQuery.getModificarIva());
			
			st.setDouble(1, iva.gettipoIva());
			st.setString(2, iva.getcodIva());
			
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
		} finally {// cerramos cursores 
			Recursos.closePreparedStatement(st);
			
		}
		return modificado;	
	}
	public Iva recuperarIva(Iva iva)throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Iva objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarIva());
				st.setString(1,iva.getcodIva() );
				rs=st.executeQuery();
				if (rs.next()){
					objeto=new Iva(rs.getString(1),
							  rs.getDouble(2)); 
				}	
				

			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return objeto;
	}
	@Override
	public Iva recuperarIvaById(String codIva) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Iva objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarIva());
				st.setString(1,codIva );
				rs=st.executeQuery();
				if (rs.next()){
					objeto=new Iva(rs.getString(1),
							  rs.getDouble(2)); 
				}	
				

			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return objeto;
	}
	public List<Iva> recuperarTodosIva()throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Iva> list = new ArrayList<Iva>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosIva());
			rs = st.executeQuery();
			while (rs.next()) {
				
				list.add(new Iva(rs.getString(1),rs.getDouble(2)));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {// cerramos cursores  y ResulSet
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;

	}

	@Override
	public int borrarIva(Iva iva) throws DAOException {
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarIva());
			st.setString(1, iva.getcodIva());
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar Iva");
				
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
	public int borrarIvaById(String iva) throws DAOException {
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarIva());
			st.setString(1, iva);
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar Iva");
				
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
