package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import domain.Factura;
import domain.Proveedor;
import exceptions.DAOException;

public class FacturaDAO {
	private static final String DB_ERR = "Error de la base de datos";

	public static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public FacturaDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarFactura(Factura factura)throws  DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarFactura());
			st.setInt   (1, factura.getNumFactura());
			st.setDate(2, factura.getFFactura());
			st.setDouble(3, (Double)factura.getImporte());
			st.setString(4, factura.getProveedor().getCodPro());
			
			// rutina de verificacion de mas de una FK
			
			
			
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" Factura ya existe");
			}else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("El proveedor de la factura no existe");
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
	}
	
	
	
	
	public int borrarFactura(Factura factura) throws  DAOException{
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarFactura());
			st.setInt(1, factura.getNumFactura());
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar Factura");
				
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
	}
	
	public Factura recuperarFactura(Factura factura)throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Factura cli=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarFactura());
				st.setInt(1,factura.getNumFactura() );
				rs=st.executeQuery();
				if (rs.next()){
	
					cli=new Factura(rs.getInt(1),
							        rs.getDate(2),
							        new Double(rs.getDouble(3)),
							        new Proveedor(rs.getString(4))
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
	
	public List<Factura> recuperarTodosFacturaNoRecibo()throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Factura> list = new ArrayList<Factura>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosFacturaNoRecibo());
			rs = st.executeQuery();
			while (rs.next()) {
				
				list.add(new Factura(rs.getInt(1),
				        rs.getDate(2),
				        new Double(rs.getDouble(3)),
				        new Proveedor(rs.getString(4))
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
