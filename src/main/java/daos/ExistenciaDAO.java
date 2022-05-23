package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import recursos.DbQuery;
import recursos.Recursos;
import domain.Almacen;
import domain.Articulo;
import domain.Existencia;
import exceptions.DAOException;

public class ExistenciaDAO {
	private static final String DB_ERR = "Error de la base de datos";

	public static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public ExistenciaDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarExistencia(Existencia Existencia)throws  DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarExistencia());
			st.setString(1, Existencia.getAlmacen().getCodAlm());
			st.setString(2, Existencia.getArticulo().getCodArt());
			st.setDate(3, Existencia.getFCaducidad());
			st.setDouble(4, Existencia.getStockIni());
			st.setDouble(5, Existencia.getStockTeorico());
			st.setDouble(6, Existencia.getPCMP());
			
			// rutina de verificacion de mas de una FK
			
			
			
			// para el almacen
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarAlmacen());
			  sti.setString(1, Existencia.getAlmacen().getCodAlm());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" el almacen del Existencia no existe");
			 }  finally {
				  Recursos.closeResultSet( rs);	
			 }
			 
			
			
					 
			  // para el articulo 
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarArticulo());
			  sti.setString(1, Existencia.getArticulo().getCodArt());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" El articulo de la existencia no existe");
		     }  finally {
			  Recursos.closeResultSet( rs);	
		     }
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" Existencia ya existe");
			}else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
	}
	
	
	public int modificarExistencia(Existencia Existencia)throws  DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		int modificado = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarExistencia());
			st.setString(4, Existencia.getAlmacen().getCodAlm());
			st.setString(5, Existencia.getArticulo().getCodArt());
			st.setDate(6, Existencia.getFCaducidad());
			st.setDouble(1, Existencia.getStockIni());
			st.setDouble(2, Existencia.getStockTeorico());
			st.setDouble(3, Existencia.getPCMP());
			
			// rutina de verificacion de mas de una FK
			
			
			
			// para el almacen
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarAlmacen());
			  sti.setString(1, Existencia.getAlmacen().getCodAlm());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" el almacen del Existencia no existe");
			 }  finally {
				  Recursos.closeResultSet( rs);	
			 }
			 
			
			
					 
			  // para el articulo 
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarArticulo());
			  sti.setString(1, Existencia.getArticulo().getCodArt());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" El articulo de la existencia no existe");
		     }  finally {
			  Recursos.closeResultSet( rs);	
		     }
			
			// ejecutamos el update.			
			modificado=st.executeUpdate();
		} catch (SQLException e) {
			 if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
		return modificado;
	}
	
	public int borrarExistencia(Existencia Existencia) throws  DAOException{
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarExistencia());
			st.setString(1, Existencia.getAlmacen().getCodAlm());
			st.setString(2, Existencia.getArticulo().getCodArt());
			st.setDate(3, Existencia.getFCaducidad());
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar Existencia");
				
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
	}
	
	public Existencia recuperarExistencia(Existencia Existencia)throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Existencia cli=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarExistencia());
				st.setString(1, Existencia.getAlmacen().getCodAlm());
				st.setString(2, Existencia.getArticulo().getCodArt());
				st.setDate(3, Existencia.getFCaducidad());
				rs=st.executeQuery();
				if (rs.next()){
	
					cli=new Existencia(new Almacen(rs.getString(1)),
							           new Articulo(rs.getString(2)),
							          rs.getDate(3),
							          rs.getDouble(4),
							          rs.getDouble(5),
							          rs.getDouble(6)
							          ); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return cli;
	}
	
	public List<Existencia> recuperarTodosExistencia()throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Existencia> list = new ArrayList<Existencia>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosExistencia());
			rs = st.executeQuery();
			while (rs.next()){		
			list.add(new Existencia(new Almacen(rs.getString(1)),
			                        new Articulo(rs.getString(2)),
			                        rs.getDate(3),
			                        rs.getDouble(4),
			                        rs.getDouble(5),
			                        rs.getDouble(6)
			                        )
				   				                     
			          );
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
