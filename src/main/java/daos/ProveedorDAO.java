package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import domain.Proveedor;
import domain.FormaPago;
import domain.Iva;

import exceptions.DAOException;

public class ProveedorDAO {
	private static final String DB_ERR = "Error de la base de datos";

	public static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public ProveedorDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarProveedor(Proveedor proveedor)throws  DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarProveedor());
			st.setString(1, proveedor.getCodPro());
			st.setString(2, proveedor.getRazonSocial());
			st.setString(3, proveedor.getTelf());
			st.setString(4, proveedor.getDireccion());
			st.setString(5, proveedor.getAlbFact());
			st.setString(6, proveedor.getCodpos());
			st.setString(7, proveedor.getIva().getcodIva());
			st.setString(8, proveedor.getFormaPago().getCodigo());
			// rutina de verificacion de mas de una FK
			
			
			
			// para el iva
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarIva());
			  sti.setString(1, proveedor.getIva().getcodIva());
			   rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" el iva del Proveedor no existe");
			   
				  
			 }  finally {
				  Recursos.closeResultSet( rs);	
			 }
			 
			
			
					 
			  // para la forma de Pago 
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarFormaPago());
			  sti.setString(1, proveedor.getFormaPago().getCodigo());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" La Tarifa del  Proveedor no existe");
		     }  finally {
			  Recursos.closeResultSet( rs);	
		     }
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" Proveedor ya existe");
			}else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
	}
	
	
	public int modificarProveedor(Proveedor proveedor)throws  DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		int modificado = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarProveedor());
			st.setString(1, proveedor.getRazonSocial());
			st.setString(2, proveedor.getTelf());
			st.setString(3, proveedor.getDireccion());
			st.setString(4, proveedor.getAlbFact());
			st.setString(5, proveedor.getCodpos());
			st.setString(6, proveedor.getIva().getcodIva());
			st.setString(7, proveedor.getFormaPago().getCodigo());
			st.setString(9, proveedor.getCodPro());
			// rutina de verificacion de mas de una FK
			
			
			
			// para el iva
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarIva());
			  sti.setString(1, proveedor.getIva().getcodIva());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" el iva del Proveedor no existe");
			 }  finally {
				  Recursos.closeResultSet( rs);	
			 }
			 
			
		
					 
			  // para la forma de Pago 
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarFormaPago());
			  sti.setString(1, proveedor.getFormaPago().getCodigo());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" La Tarifa del  Proveedor no existe");
		     }  finally {
			  Recursos.closeResultSet( rs);	
		     }
			
			// ejecutamos el insert.			
			modificado=st.executeUpdate();
		} catch (SQLException e) {
			 if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			} else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			}else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
		return modificado;
	}
	
	public int borrarProveedor(Proveedor proveedor) throws  DAOException{
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarProveedor());
			st.setString(1, proveedor.getCodPro());
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar Proveedor");
				
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
	
	public Proveedor recuperarProveedor(Proveedor proveedor)throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Proveedor pro=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarProveedor());
				st.setString(1,proveedor.getCodPro() );
				rs=st.executeQuery();
				if (rs.next()){
	
		pro=new Proveedor(     rs.getString(1),
						       rs.getString(2),
						       rs.getString(3),
						       rs.getString(4),
						       rs.getString(5),
						       rs.getString(6),
						       new Iva(rs.getString(7)),
						       new FormaPago(rs.getString(8))); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return pro;
	}
	
	public List<Proveedor> recuperarTodosProveedor()throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Proveedor> list = new ArrayList<Proveedor>();
		try {
			st = con.prepareStatement(DbQuery.getTodosProveedor());
			rs = st.executeQuery();
			while (rs.next()){		
			list.add(new Proveedor(rs.getString(1),
			                       rs.getString(2),
			                       rs.getString(3),
			                       rs.getString(4),
			                       rs.getString(5),
			                       rs.getString(6),
			                       new Iva(rs.getString(7)),
			                       new FormaPago(rs.getString(8))
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
