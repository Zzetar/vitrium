package daos;

import java.sql.*;
import java.util.*;

import recursos.*;
import domain.*;
import exceptions.*;

public class LinAlbDAO {
	Connection conexion=null;
	private static final String DB_ERR = "Error de la base de datos";
	public static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;

	public LinAlbDAO (Connection conexion){
		this.conexion=conexion;
	}

	public void insertarLinAlb(LinAlb linalb) throws DAOException{
		 PreparedStatement orden1= null, orden = null;
		 ResultSet res=null;
		 try{
			orden1=conexion.prepareStatement(DbQuery.getInsertarLinAlb());
			orden1.setString(1, linalb.getAlbaran().getCodAlbaran() );
			orden1.setString(2, linalb.getExistencia().getAlmacen().getCodAlm() );
			orden1.setString(3, linalb.getExistencia().getArticulo().getCodArt());
			orden1.setDate(4, linalb.getExistencia().getFCaducidad() );
			orden1.setDouble(5, linalb.getCantEnt() );
			orden1.setDouble(6, linalb.getPrecioEnt() );
			
	        // rutina de verificacion de mas de una FK
			// para el albaran
			try{
			  orden = conexion.prepareStatement(DbQuery.getRecuperarAlbaran());
			  orden.setString(1, linalb.getAlbaran().getCodAlbaran());
			  res=orden.executeQuery();
			  if(!res.next())	
			    throw new DAOException("El albarán no existe.");
			 }  finally {
				  Recursos.closeResultSet(res);	
			 }
			// para la existencia
			try{
				orden = conexion.prepareStatement(DbQuery.getRecuperarExistencia());
				orden.setString(1, linalb.getExistencia().getAlmacen().getCodAlm());
				orden.setString(2, linalb.getExistencia().getArticulo().getCodArt());
				orden.setDate(3, linalb.getExistencia().getFCaducidad());
			    res=orden.executeQuery();
				if(!res.next())	
					throw new DAOException(" La existencia no existe");
				}  finally {
					Recursos.closeResultSet( res);	
				}
			   orden1.executeUpdate();
		}catch (SQLException e){
			 if (e.getErrorCode()==ORACLE_DUPLICATE_PK)
				throw new DAOException ("Linea de albarán repetida");
			 else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
				   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else
				throw new DAOException(DB_ERR, e);
		}finally {
			Recursos.closePreparedStatement(orden1);
		}
	}
	
	public void modificarLinAlb(LinAlb linalb) throws DAOException{
		 PreparedStatement orden1= null, orden = null;
		 ResultSet res=null;
		 try{
			orden1=conexion.prepareStatement(DbQuery.getModificarLinAlb());
			orden1.setDouble(1, linalb.getCantEnt() );
			orden1.setDouble(2, linalb.getPrecioEnt() );
			orden1.setString(3, linalb.getAlbaran().getCodAlbaran() );
			orden1.setString(4, linalb.getExistencia().getAlmacen().getCodAlm() );
			orden1.setString(5, linalb.getExistencia().getArticulo().getCodArt() );
			orden1.setDate(6, linalb.getExistencia().getFCaducidad() );
			
	        // rutina de verificacion de mas de una FK
			// para el albaran
			try{
			  orden = conexion.prepareStatement(DbQuery.getRecuperarIva());
			  orden.setString(1, linalb.getAlbaran().getCodAlbaran());
			  res=orden.executeQuery();
			  if(!res.next())	
			    throw new DAOException("El albarán no existe.");
			 }  finally {
				  Recursos.closeResultSet(res);	
			 }
			// para la existencia
			try{
				orden = conexion.prepareStatement(DbQuery.getRecuperarExistencia());
				orden.setString(1, linalb.getExistencia().getAlmacen().getCodAlm());
				orden.setString(2, linalb.getExistencia().getArticulo().getCodArt());
				orden.setDate(3, linalb.getExistencia().getFCaducidad());
			    res=orden.executeQuery();
				if(!res.next())	
					throw new DAOException(" La existencia no existe");
				}  finally {
					Recursos.closeResultSet( res);	
				}
			   orden1.executeUpdate();
		}catch (SQLException e){
			if (e.getErrorCode() ==ORACLE_FALLO_FK ){
				   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else
				throw new DAOException(DB_ERR, e);
		}finally {
			Recursos.closePreparedStatement(orden1);
		}
	}

	public int borrarLinAalb (LinAlb linalb) throws DAOException {
		int res=0;
		PreparedStatement orden=null;
		try{
			orden=conexion.prepareStatement(DbQuery.getBorrarLinAlb());
			orden.setString(1, linalb.getAlbaran().getCodAlbaran() );
			orden.setString(2, linalb.getExistencia().getAlmacen().getCodAlm());
			orden.setString(3, linalb.getExistencia().getArticulo().getCodArt());
			orden.setDate(4, linalb.getExistencia().getFCaducidad() );
			res = orden.executeUpdate();
		}catch (SQLException e){
			if (e.getErrorCode()==ORACLE_DELETE_FK) throw new DAOException ("La línea de albarán no se puede borrar");
			else 
				new DAOException(DB_ERR, e);
		}finally {
			Recursos.closePreparedStatement(orden);
		}
		return res;
	}
	
	public LinAlb recuperarLinalb (LinAlb linalb) throws DAOException{
		PreparedStatement orden=null;
		ResultSet res=null;
		LinAlb l=null;
		try{
			orden=conexion.prepareStatement(DbQuery.getRecuperarLinAlb());
			orden.setString(1, linalb.getAlbaran().getCodAlbaran() );
			orden.setString(2, linalb.getExistencia().getAlmacen().getCodAlm());
			orden.setString(3, linalb.getExistencia().getArticulo().getCodArt() );
			orden.setDate(4, linalb.getExistencia().getFCaducidad());
			res = orden.executeQuery();
			if (res.next()){
				l = new LinAlb (new Albaran(res.getString(1)), 
						         new Existencia(new Almacen (res.getString(2)) , new Articulo (res.getString(3)),res.getDate(4)), 
						         res.getInt(5),
						         res.getDouble(6));
			}
		}catch (SQLException e){
			throw new DAOException (DB_ERR, e);
		}finally{
			Recursos.closeResultSet(res);
			Recursos.closePreparedStatement(orden);
		}return l;
	}
		public List<LinAlb> recuperarTodasLinAlb(Albaran albaran) throws DAOException {
			PreparedStatement orden=null;
			ResultSet res=null;
			LinAlb l = null;
			List<LinAlb> list = new ArrayList<LinAlb>();
			try{
				orden=conexion.prepareStatement(DbQuery.getRecuperarTodosLinAlbAlbaran());
				orden.setString(1, albaran.getCodAlbaran());
				res = orden.executeQuery();
				while (res.next()){
					l = new LinAlb (new Albaran(res.getString(1)), 
					         new Existencia(new Almacen (res.getString(2)) , new Articulo (res.getString(3)),res.getDate(4)), 
					         res.getInt(5),
					         res.getDouble(6));
					list.add(l);
				}
			}catch (SQLException e){
				throw new DAOException (DB_ERR, e);
			}finally{
				Recursos.closeResultSet(res);
				Recursos.closePreparedStatement(orden);
			}return list;
		}

		public double RecuperarImporteTotalAlbaran(Albaran albaran) throws DAOException {
			PreparedStatement orden=null;
			ResultSet res=null;
			double totalalbaran=0 ;
			
			try{
				orden=conexion.prepareStatement(DbQuery.getRecuperarTotalImporteAlbaran());
				orden.setString(1, albaran.getCodAlbaran());
				res = orden.executeQuery();
				if(res.next())
					totalalbaran= res.getDouble(1);
				
			}catch (SQLException e){
				throw new DAOException (DB_ERR, e);
			}finally{
				Recursos.closeResultSet(res);
				Recursos.closePreparedStatement(orden);
			}
			return totalalbaran;
		}

		

	
		
	}
	

