package daos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import daos.interfaces.ErroresBD;
import recursos.DbQuery;
import recursos.Recursos;
import domain.Articulo;
import domain.Factura;
import domain.Familia;

import exceptions.DAOException;

public class ArticuloDAO implements ErroresBD {
	
	
	private Connection con;

	public ArticuloDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarArticulo(Articulo articulo)throws  DAOException{
		PreparedStatement st = null;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarArticulo(), Statement.RETURN_GENERATED_KEYS);
			st.setString(1, articulo.getCategoria());
			st.setInt(2, articulo.getPrecio());
			st.setString(3, articulo.getDescripcion());
			st.setString(4, articulo.getPath());
					
			
			// ejecutamos el insert.			
			st.executeUpdate();
			ResultSet rs= st.getGeneratedKeys();
			rs.next();
			articulo.setIdArticulo(rs.getInt(1));
		} catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) { //TODO: CAmbiar
				throw new DAOException(" articulo ya existe");
			} else {
				throw new DAOException(DB_ERR + ": " + e.getMessage(), e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}	
	}
	
	
	public Articulo recuperarArticulo(int idArticulo)throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Articulo articulo=null;
			try {
				st = con.prepareStatement(DbQuery.getRecuperarArticulo());
				st.setInt(1, idArticulo);
				rs=st.executeQuery();
				if (rs.next()){
					articulo= new Articulo();
					articulo.setCategoria(rs.getString(1));
					articulo.setPrecio(rs.getInt(2));
					articulo.setDescripcion(rs.getString(3));
					articulo.setPath(rs.getString(4));
					articulo.setIdArticulo(idArticulo);
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return articulo;
	}
	
	public List<Articulo> recuperarTodosArticulo()throws  DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Articulo> list = new ArrayList<Articulo>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosArticulo());
			rs = st.executeQuery();
			while (rs.next()) {
				Articulo articulo= new Articulo();
				articulo.setCategoria(rs.getString(1));
				articulo.setPrecio(rs.getInt(2));
				articulo.setDescripcion(rs.getString(3));
				articulo.setPath(rs.getString(4));
				articulo.setIdArticulo(rs.getInt(5));
				
				list.add(articulo);
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
