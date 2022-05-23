package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Pedido;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class PedidoDAO {
	private static final String DB_ERR = "Error de la base de datos";

	private static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public PedidoDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarPedido(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		PreparedStatement stAux = null;
		ResultSet rs = null;
		int nped;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarPedido());
			// alternativa a random utilizar una sequencia de bd en oracle.
			nped=Recursos.randomEntero(9);
			st.setInt(1,nped );
			st.setDate(2, pedido.getFechaPed());
			
			pedido.setnPed(nped);
			st.executeUpdate();
			
			
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK){
			insertarPedido( pedido);
					
			}
							
			else if (e.getErrorCode() ==ORACLE_FALLO_FK )
					throw new DAOException("El proveedor del pedido no existe");
				else
					throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closePreparedStatement(stAux);
			Recursos.closePreparedStatement(st);
			Recursos.closeResultSet(rs);
		}
		
		
	}
	
	public int modificarPedido(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		PreparedStatement stAux = null;
		ResultSet rs = null;
		int filas = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarPedido());
			
			st.setDate(1, pedido.getFechaPed());
			st.setInt(2, pedido.getnPed());
			
			
			
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() ==ORACLE_FALLO_FK ){
				   throw new DAOException("El proveedor del pedido no existe");
			} else
			   throw new DAOException(DB_ERR, e);
		}  finally {
			Recursos.closePreparedStatement(stAux);
			Recursos.closePreparedStatement(st);
			Recursos.closeResultSet(rs);
		}
		return filas;
	}

	public int borrarPedido(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		int filas = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarPedido());
			st.setInt(1, pedido.getnPed());
			filas = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK)
				throw new DAOException("No se pudo eliminar el Pedido");
			else
				throw new DAOException(DB_ERR, e);
		}  finally {
			Recursos.closePreparedStatement(st);
		}
		return filas;
	}
	
	public Pedido recuperarPedido(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null;
		Pedido objeto=null;
		
		try {
			st = con.prepareStatement(DbQuery.getRecuperarPedido());
			st.setInt(1,pedido.getnPed());
			rs=st.executeQuery();
			if (rs.next()){				
			objeto = new Pedido(rs.getInt(1), rs.getDate(2)); 
			}		
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return objeto;
	}
	
	
	public List<Pedido> recuperarTodosPedido() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Pedido> list = new ArrayList<Pedido>();
		
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosPedido());
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new Pedido(rs.getInt(1), rs.getDate(2)));
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
