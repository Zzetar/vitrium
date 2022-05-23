package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Factura;
import domain.Recibo;
import recursos.DbQuery;
import recursos.Recursos;
import exceptions.DAOException;

public class ReciboDAO {
	private static final String DB_ERR = "Error de la base de datos";

	public static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public ReciboDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarRecibo(Recibo recibo)throws  DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarRecibo());
			st.setInt(1, recibo.getFactura().getNumFactura());
			st.setInt(2,recibo.getNum());
			st.setDate(3, recibo.getFVto());
			st.setDouble(4, recibo.getImporte()
			);
			// rutina de verificacion de mas de una FK
			
			
			
			
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" recibo ya existe");
			}else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("El numero de la factura del recibo no existe");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
	}
	
	
	
	public int borrarRecibo(Recibo recibo) throws  DAOException{
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarRecibo());
			st.setInt(1, recibo.getFactura().getNumFactura());
			st.setInt(2, recibo.getNum());
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar recibo");
				
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
	}
	
	public Recibo recuperarRecibo(Recibo recibo)throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Recibo recibo1=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarRecibo());
				st.setInt(1, recibo.getFactura().getNumFactura());
				st.setInt(2, recibo.getNum());
				rs=st.executeQuery();
				if (rs.next()){
	
					recibo1=new Recibo(new Factura(rs.getInt(1)),
							       rs.getInt(2),
							       rs.getDate(3),
							      rs.getDouble(4)
							      ); 
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return recibo1;
	}
	
	public List<Recibo> recuperarTodosRecibo()throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Recibo> list = new ArrayList<Recibo>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosRecibo());
			rs = st.executeQuery();
			while (rs.next()) {
				
				list.add(new Recibo(new Factura(rs.getInt(1)),
					       rs.getInt(2),
					       rs.getDate(3),
					       rs.getDouble(4)
					       ));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
		

	}
	public List<Recibo> recuperarTodosReciboFactura(Factura factura)throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Recibo> list = new ArrayList<Recibo>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosReciboFactura());
			st.setInt(1, factura.getNumFactura());
			rs = st.executeQuery();
			while (rs.next()) {
				
				list.add(new Recibo(new Factura(rs.getInt(1)),
					       rs.getInt(2),
					       rs.getDate(3),
					       rs.getDouble(4)
					       ));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
		

	}

	public List<Recibo> recuperarRecibos(String proveedor) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Recibo> list = new ArrayList<Recibo>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosReciboProvedor());
			st.setString(1, proveedor);
			rs = st.executeQuery();
			while (rs.next()) {
				
				list.add(new Recibo(new Factura(rs.getInt(1)),
					       rs.getInt(2),
					       rs.getDate(3),
					       rs.getDouble(4)
					       ));
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
