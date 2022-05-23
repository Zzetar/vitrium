package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import util.Fecha;

import domain.Albaran;
import domain.Factura;
import domain.Proveedor;

import exceptions.DAOException;

public class AlbaranDAO {
	private static final String DB_ERR = "Error de la base de datos";

	private static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public AlbaranDAO(Connection con) {
		this.con = con;
	}
	
	public Albaran insertarAlbaran(Albaran albaran) throws DAOException {
		PreparedStatement st = null;
		PreparedStatement stAux = null;
		ResultSet rs = null;
		String auxiliar=null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarAlbaran());
			auxiliar=Fecha.convertirAString(albaran.getFEntrada(), "dd/MM-")+Recursos.randomEntero(4);
			
			st.setString(1,auxiliar );
			st.setDate(2, albaran.getFAlbaran());
			st.setDate(3, albaran.getFEntrada());
			st.setString(4, albaran.getProveedor().getCodPro());
			st.setNull(5, Types.INTEGER);
			
			
			
			st.executeUpdate();
			albaran.setCodAlbaran(auxiliar);
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK)
				 albaran=insertarAlbaran( albaran);
				
			else 
				if (e.getErrorCode() ==ORACLE_FALLO_FK )
					throw new DAOException("El proveedor del albaran no existe");
				else
					throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closePreparedStatement(stAux);
			Recursos.closePreparedStatement(st);
			Recursos.closeResultSet(rs);
		}
		return albaran;
		
	}
	public int  modificarAlbaranNumFactura(Albaran albaran) throws DAOException {
		PreparedStatement st = null;
		int modificadas;
		try {
			st = con.prepareStatement(DbQuery.getModificarAlbaranNumFactura());
			
			
			if(albaran.getFactura()!= null)
			st.setInt(1, albaran.getFactura().getNumFactura())	;
			else	
			st.setNull(1, Types.INTEGER);
			st.setString(2,albaran.getCodAlbaran() );
			
			
			
			modificadas=st.executeUpdate();
			
		} catch (SQLException e) {
			
					throw new DAOException(DB_ERR, e);
		} finally {
			
			Recursos.closePreparedStatement(st);
			
		}
		return modificadas ;
	}
	public int borrarAlbaran(Albaran albaran) throws DAOException {
		PreparedStatement st = null;
		int filas = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarAlbaran());
			st.setString(1, albaran.getCodAlbaran());
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK)
				throw new DAOException("No se pudo eliminar el Albaran");
			else
				throw new DAOException(DB_ERR, e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}
	
	public Albaran recuperarAlbaran(Albaran albaran) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null;
		Albaran albaran1=null;
		Factura numFactura=null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarAlbaran());
			st.setString(1,albaran.getCodAlbaran());
			rs=st.executeQuery();
			if (rs.next()){	
				
                if(rs.getObject("num_factura") ==null )
                	numFactura= null;
            	else
            		numFactura=	new Factura(rs.getInt("num_factura")); 
            		  
			albaran1 = new Albaran(rs.getString(1), rs.getDate(2),rs.getDate(3),
					  new Proveedor(rs.getString(4)),numFactura); 
			}
			
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return albaran1;
	}
	public List<Albaran> recuperarTodosAlbaran() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Albaran> list = new ArrayList<Albaran>();
		Factura numFactura=null;
		
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosAlbaran());
			rs = st.executeQuery();
			while (rs.next()) {
				numFactura= null;
				if(rs.getObject("num_factura") !=null )
                   numFactura=	new Factura(rs.getInt("num_factura")); 
				
				list.add(new Albaran(rs.getString(1), rs.getDate(2),rs.getDate(3),
						  new Proveedor(rs.getString(4)),numFactura));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	}
	
	public List<Albaran> recuperarTodosAlbaran(Proveedor proveedor) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Albaran> list = new ArrayList<Albaran>();
		Factura numFactura=null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosAlbaranProveedor());
			st.setString(1, proveedor.getCodPro());
			rs = st.executeQuery();
			while (rs.next()) {
				numFactura= null;
				if(rs.getObject("num_factura") !=null )
                   numFactura=	new Factura(rs.getInt("num_factura")); 
				list.add(new Albaran(rs.getString(1), rs.getDate(2),rs.getDate(3),
						  new Proveedor(rs.getString(4)),numFactura));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	}
	// metodos para el ejercicio de facturacion
	public List<Albaran> recuperarTodosAlbaranProveedorS1(Date finicial,
			Date ffinal, Proveedor proveedor) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Albaran> list = new ArrayList<Albaran>();
		
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosAlbaranProveedorS1());
			st.setDate(1, finicial);
			st.setDate(2, ffinal);
			st.setString(3, proveedor.getCodPro());
			
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new Albaran(rs.getString(1), rs.getDate(2),rs.getDate(3),
						  new Proveedor(rs.getString(4)),new Factura(rs.getInt(5))));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	}
	public List<Albaran> recuperarTodosAlbaranProveedorS(Date finical,
			Date ffinal) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Albaran> list = new ArrayList<Albaran>();
		
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosAlbaranProveedorS());
			st.setDate(1, finical);
			st.setDate(2, ffinal);
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new Albaran(rs.getString(1), rs.getDate(2),rs.getDate(3),
						  new Proveedor(rs.getString(4)),new Factura(rs.getInt(5))));
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
		
		
	}
	
	public List<Albaran> recuperarTodosAlbaranProveedorN1(Date finicial,
			Date ffinal,  Proveedor proveedor) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Albaran> list = new ArrayList<Albaran>();
		
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosAlbaranProveedorN1());
			st.setDate(1, finicial);
			st.setDate(2, ffinal);
			st.setString(3, proveedor.getCodPro());
						
			
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new Albaran(rs.getString(1), rs.getDate(2),rs.getDate(3),
						  new Proveedor(rs.getString(4)),new Factura(rs.getInt(5))));
			}
		
		
			
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;
	}
	public List<Albaran> recuperarTodosAlbaranProveedorN(Date finicial,Date ffinal) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Albaran> list = new ArrayList<Albaran>();
		
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosAlbaranProveedorN());
			st.setDate(1,finicial );
			st.setDate(2, ffinal);
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new Albaran(rs.getString(1), rs.getDate(2),rs.getDate(3),
						  new Proveedor(rs.getString(4)),new Factura(rs.getInt(5))));
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
