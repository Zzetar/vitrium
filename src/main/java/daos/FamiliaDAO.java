package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;

import domain.Familia;
import exceptions.DAOException;


public class FamiliaDAO {
	private static final String DB_ERR = "Error de la base de datos";

	public static final int ORACLE_DUPLIK = 1;
	private static final int ORACLE_DELETE_FK = 2292;
	private static final int ORACLE_FALLO_FK = 2291;
	
	private Connection con;

	public FamiliaDAO(Connection con) {
		this.con = con;
	}
	
	public Familia recuperarFamilia(Familia familia) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Familia t=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarFamilia());
				st.setString(1,familia.getCodFamilia() );
				rs=st.executeQuery();
				if (rs.next()){
					t=new Familia(rs.getString(1),
							    rs.getString(2)); 
				}		

			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return t;
	}
	
	
	public List<Familia> recuperarTodosFamilia() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Familia> list = new ArrayList<Familia>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosFamilia());
			rs = st.executeQuery();
			while (rs.next()) {
				
				list.add(new Familia(rs.getString(1),rs.getString(2)));
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
