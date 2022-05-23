package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import domain.Articulo;
import domain.Factura;
import domain.Familia;

import exceptions.DAOException;

public class ArticuloDAO {
	private static final String DB_ERR = "Error de la base de datos";

	public static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public ArticuloDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarArticulo(Articulo articulo)throws  DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarArticulo());
			st.setString(1, articulo.getCodArt());
			st.setString(2, articulo.getDescripcion());
			
			if(articulo.getPreciMer()!=null) // OJO PUEDE SER NULO EN LA BASE DE DATOS
				st.setDouble(3, articulo.getPreciMer());
			else
				st.setNull(3,  Types.DOUBLE);
			
			
			st.setString(4, articulo.getFamilia().getCodFamilia());
			
			// rutina de verificacion de mas de una FK
					  
			
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" Articulo ya existe");
			}else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("la familia  del articulo no existe");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
	}
	
	
	public int modificarArticulo(Articulo articulo)throws  DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		int modificado=0;
		try {
			st = con.prepareStatement(DbQuery.getModificarArticulo());
			st.setString(4, articulo.getCodArt());
			st.setString(1, articulo.getDescripcion());
			if(articulo.getPreciMer()!=null) // OJO PUEDE SER NULO EN LA BASE DE DATOS
				st.setDouble(2, articulo.getPreciMer());
			else
				st.setNull(2,  Types.DOUBLE);
			
			st.setString(3, articulo.getFamilia().getCodFamilia());
			
			// rutina de verificacion de mas de una FK
					  
			
			
			// ejecutamos el insert.			
			modificado=st.executeUpdate();
		} catch (SQLException e) {
			 if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("la familia  del articulo no existe");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
		return modificado;
	}
	
	public int borrarArticulo(Articulo articulo) throws  DAOException{
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarArticulo());
			st.setString(1, articulo.getCodArt());
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar Articulo");
				
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
	}
	
	public Articulo recuperarArticulo(Articulo articulo)throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Articulo cli=null;
		Double percioMercado= null;
			try {
				st = con.prepareStatement(DbQuery.getRecuperarArticulo());
				st.setString(1,articulo.getCodArt() );
				rs=st.executeQuery();
				if (rs.next()){
					percioMercado= null;
					if(rs.getObject("precio_mer") !=null )
						percioMercado=	new Double(rs.getDouble("precio_mer")); 
					cli=new Articulo(rs.getString(1),
							         rs.getString(2),
							         percioMercado,
							         new Familia(rs.getString(4))); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return cli;
	}
	
	public List<Articulo> recuperarTodosArticulo()throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Articulo> list = new ArrayList<Articulo>();
		Double percioMercado= null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosArticulo());
			rs = st.executeQuery();
			while (rs.next()) {
				percioMercado= null;
				if(rs.getObject("precio_mer") !=null )
					percioMercado=	new Double(rs.getDouble("percio_mer")); 
				list.add(new Articulo(rs.getString(1),
				                       rs.getString(2),
				                       percioMercado,
				                       new Familia(rs.getString(4))));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;

	}
	
}
