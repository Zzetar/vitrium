package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Articulo;


import domain.LinPed;

import domain.Pedido;


import recursos.DbQuery;
import recursos.Recursos;

import exceptions.DAOException;

public class LinPedDAO {
	private static final String DB_ERR = "Error de la base de datos";

	private static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public LinPedDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarLinPed(LinPed linped) throws DAOException {
		PreparedStatement st = null;
		PreparedStatement stAux = null;
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarLinPed());
			st.setInt(1, linped.getPedido().getnPed());
			st.setString(2, linped.getArticulo().getCodArt());
			st.setDouble(3, linped.getCantidad());
			if (linped.getCantidadServ()==null)
			st.setNull(4, Types.DOUBLE)	;
			else	
			st.setDouble(4, (Double)linped.getCantidadServ());
			
			
			try {
				stAux = con.prepareStatement(DbQuery.getRecuperarPedido());
				stAux.setInt(1, linped.getPedido().getnPed());
				rs = stAux.executeQuery();
				if (!rs.next()) throw new DAOException("El pedido no existe");
			} finally {
				Recursos.closeResultSet(rs);
			}
			
			try {
				stAux = con.prepareStatement(DbQuery.getRecuperarArticulo());
				stAux.setString(1, linped.getArticulo().getCodArt());
				rs = stAux.executeQuery();
				if (!rs.next()) throw new DAOException("El artículo no existe");
			} finally {
				Recursos.closeResultSet(rs);
			}
			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK)
				throw new DAOException("La linea de pedido ya existe");
			else if (e.getErrorCode() ==ORACLE_FALLO_FK )
					throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			
			else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			}else
					throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closePreparedStatement(stAux);
			Recursos.closePreparedStatement(st);
			Recursos.closeResultSet(rs);
		}
	}
	
	public int modificarLinPed(LinPed linped) throws DAOException {
		PreparedStatement st = null;
		PreparedStatement stAux = null;
		ResultSet rs = null;
		int filas = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarLinPed());
			
			st.setDouble(1, linped.getCantidad());
			if(linped.getCantidadServ()!=null)
			st.setDouble(2, linped.getCantidadServ());
			else
				st.setNull(2,Types.DOUBLE)	;
			st.setInt(3, linped.getPedido().getnPed());
			st.setString(4, linped.getArticulo().getCodArt());
			
			
			
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			}
			else
				throw new DAOException(DB_ERR, e);
		}  finally {
			Recursos.closePreparedStatement(stAux);
			Recursos.closePreparedStatement(st);
			Recursos.closeResultSet(rs);
		}
		return filas;
	}

	public int borrarLinPed(LinPed linped) throws DAOException {
		PreparedStatement st = null;
		int filas = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarLinPed());
			st.setInt(1, linped.getPedido().getnPed());
			st.setString(2, linped.getArticulo().getCodArt());
			
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK)
				throw new DAOException("No se pudo eliminar la Linea de Pedido");
			else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			}else
				throw new DAOException(DB_ERR, e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}
	public int borrarLinPed(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		int filas = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarTodosLinPedPedido());
			st.setInt(1, pedido.getnPed());
			
			
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK)
				throw new DAOException("No se pudo eliminar la Linea de Pedido");
			else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			}else
				throw new DAOException(DB_ERR, e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}
	
	public LinPed recuperarLinPed(LinPed linped) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		LinPed objeto = null;
		Double cantidadServida=null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarLinPed());
			st.setInt(1,linped.getPedido().getnPed());
			st.setString(2,linped.getArticulo().getCodArt());
			
			rs=st.executeQuery();
			if (rs.next()){				
				cantidadServida= null;
				if(rs.getObject("cantidad_serv") !=null )
					cantidadServida=	new Double(rs.getDouble("cantidad_serv")); 
				objeto = new LinPed( new Pedido(rs.getInt(1)),
						             new Articulo(rs.getString(2)),
						                 rs.getDouble(3),
						                 cantidadServida
						                 ); 
			}		
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return objeto;
	}
	
	
	public List<LinPed> recuperarTodosLinPed(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<LinPed> list = new ArrayList<LinPed>();
		Double cantidadServida=null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosLinPedPedido());
			st.setInt(1, pedido.getnPed());
			rs = st.executeQuery();
			while (rs.next()) {
				cantidadServida= null;
				if(rs.getObject("cantidad_serv") !=null )
					cantidadServida=	new Double(rs.getDouble("cantidad_serv")); 
				list.add(new LinPed(
		                      new Pedido(rs.getInt(1)),
		                      new Articulo(rs.getString(2)),
		                      rs.getDouble(3),
		                      cantidadServida
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

	public List<LinPed> recuperarLinPedBloqueo(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<LinPed> list = new ArrayList<LinPed>();
		Double cantidadServida=null;
		try {
			st = con.prepareStatement(DbQuery.getRecuperarLinPedBloqueo());
			st.setInt(1, pedido.getnPed());
			rs = st.executeQuery();
			while (rs.next()) {
				cantidadServida= null;
				if(rs.getObject("cantidad_serv") !=null )
					cantidadServida=	new Double(rs.getDouble("cantidad_serv")); 
				list.add(new LinPed(
		                      new Pedido(rs.getInt(1)),
		                      new Articulo(rs.getString(2)),
		                      rs.getDouble(3),
		                      cantidadServida
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
