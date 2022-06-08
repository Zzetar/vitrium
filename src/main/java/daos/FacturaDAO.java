package daos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.interfaces.ErroresBD;
import recursos.DbQuery;
import recursos.Recursos;
import domain.Factura;
import domain.Proveedor;
import exceptions.DAOException;

public class FacturaDAO implements ErroresBD {
	
	private Connection con;

	public FacturaDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarFactura(Factura factura)throws  DAOException{
		PreparedStatement st = null;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarFactura());
			st.setInt(1, factura.getIdFactura());
			st.setInt(2, factura.getIdPedido());
			st.setInt(3, factura.getIdCliente());
			st.setInt(5, factura.getIdArticulo());

			// ejecutamos el insert.			
			st.executeUpdate();
			ResultSet rs= st.getGeneratedKeys();
			rs.next();
			factura.setIdFactura(rs.getInt(1));
		} catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) { //TODO: CAmbiar
				throw new DAOException(" factura ya existe");
			} else {
				throw new DAOException(DB_ERR + ": " + e.getMessage(), e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
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
