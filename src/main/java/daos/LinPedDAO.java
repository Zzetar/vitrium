package daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import daos.interfaces.ErroresBD;
import domain.Articulo;


import domain.LinPed;

import domain.Pedido;


import recursos.DbQuery;
import recursos.Recursos;

import exceptions.DAOException;

public class LinPedDAO implements ErroresBD {
	
	private Connection con;

	public LinPedDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarLinPed(LinPed linped) throws DAOException {
		PreparedStatement st = null;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarLinPed(), Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, linped.getIdPedido());
			st.setInt(2, linped.getIdArticulo());
			st.setInt(3, linped.getCantidad());
			st.setInt(4, linped.getGastosEnvio());
			st.setInt(5, linped.getPrecioFinal());

			// ejecutamos el insert.			
			st.executeUpdate();
			ResultSet rs= st.getGeneratedKeys();
			rs.next();
			linped.setIdLinea(rs.getInt(1));
		} catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) { //TODO: CAmbiar
				throw new DAOException(" Linea de pedido ya existe");
			} else {
				throw new DAOException(DB_ERR + ": " + e.getMessage(), e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}	
	}
		
	
	public List<LinPed> recuperarTodosLinPed(Pedido pedido) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<LinPed> list = new ArrayList<LinPed>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosLinPedPedido());
			st.setInt(1, pedido.getIdPedido());
			rs = st.executeQuery();
			while (rs.next()) {
				//idLinea, idArticulo,  cantidad,  gastosEnvio,  precioFinal,
				LinPed linea= new LinPed();
				linea.setIdLinea(rs.getInt(1));
				linea.setIdArticulo(rs.getInt(2));
				linea.setCantidad(rs.getInt(3));
				linea.setGastosEnvio(rs.getInt(4));
				linea.setPrecioFinal(rs.getInt(5));
				linea.setIdPedido(pedido.getIdPedido());
				list.add(linea);
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
