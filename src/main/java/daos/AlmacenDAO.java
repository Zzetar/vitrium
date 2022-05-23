package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;

import domain.Almacen;
import exceptions.DAOException;


public class AlmacenDAO {
	private static final String DB_ERR = "Error de la base de datos";

	private  static final int ORACLE_DUPLICATE_PK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public AlmacenDAO(Connection con) {
		this.con = con;
	}
	
	public Almacen recuperarAlmacen(Almacen Almacen) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Almacen t=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarAlmacen());
				st.setString(1,Almacen.getCodAlm() );
				rs=st.executeQuery();
				if (rs.next()){
					t=new Almacen(rs.getString(1),
							    rs.getString(2),rs.getString(3),
							    rs.getString(4)); 
				}		

			} catch (SQLException e) {
				try {
					throw new DAOException(DB_ERR, e);
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return t;
	}
	
	
	public List<Almacen> recuperarTodosAlmacen() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Almacen> list = new ArrayList<Almacen>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosAlmacen());
			rs = st.executeQuery();
			while (rs.next()) {
				
				list.add(new Almacen(rs.getString(1),rs.getString(2),
						rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			try {
				throw new DAOException(DB_ERR, e);
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;

	}
	
}
