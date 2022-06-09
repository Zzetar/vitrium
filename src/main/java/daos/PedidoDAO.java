package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import daos.interfaces.ErroresBD;
import domain.Pedido;
import exceptions.DAOException;
import recursos.DbQuery;
import recursos.Recursos;

public class PedidoDAO implements ErroresBD {
	
	private Connection con;

	public PedidoDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarPedido(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarPedido(), Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, pedido.getIdCliente());
			st.setString(2, pedido.getEstadoPedido());
			st.setDate(3, pedido.getFechaPed());
			st.setInt(4, pedido.getImporte());

			// ejecutamos el insert.			
			st.executeUpdate();
			ResultSet rs= st.getGeneratedKeys();
			rs.next();
			pedido.setIdPedido(rs.getInt(1));

		} catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) { //TODO: CAmbiar
				throw new DAOException(" pedido ya existe");
			} else {
				throw new DAOException(DB_ERR + ": " + e.getMessage(), e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		
		
	}

	public List<Pedido> recuperarPedidosCliente(int idCliente) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Pedido> list = new ArrayList<Pedido>();
		
		try {
			st = con.prepareStatement(DbQuery.getRecuperarPedidosCliente());
			st.setInt(1, idCliente);
			rs = st.executeQuery();
			while (rs.next()) {
				Pedido pedido= new Pedido();
				pedido.setIdPedido(rs.getInt(1));
				pedido.setEstadoPedido(rs.getString(2));
				pedido.setFechaPed(rs.getDate(3));
				pedido.setImporte(rs.getInt(4));
				list.add(pedido);
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
