package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import recursos.DbQuery;
import recursos.Recursos;
import exceptions.DAOException;


public class ContadorFactDAO {
	
	private static final String DB_ERR = "Error de la base de datos";
	private Connection con;

	public ContadorFactDAO(Connection con) {
		this.con = con;
	}
	
	public int recuperarContadorFact() throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		int numero ;
		
			try {
				st = con.prepareStatement(DbQuery.getModificarContadorFact());
				st.executeUpdate();
				st = con.prepareStatement(DbQuery.getRecuperarContadorFact());
				rs=st.executeQuery();	
				
				if(rs.next())
				numero=rs.getInt(1);
				else
				throw new SQLException("El contador de facturas no existe");	
						

			} catch (SQLException e) {
				if(e.getErrorCode()==0)
				   throw new DAOException(e.getMessage(), e);	
				else 
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return numero;
	}
	
	
	
	
}
