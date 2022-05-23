package programas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import daos.TransaccionesManager;

import bbdd.ConexionOracle;

import recursos.DbQuery;
import recursos.Recursos;

public class PruebaNulos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PreparedStatement st = null;
		PreparedStatement stAux = null;
		ResultSet rs = null;
		int nped;
	    Connection con;
		TransaccionesManager trans =  null;
		
		try {			
			

				trans =  new TransaccionesManager();
			con=trans.getConexion();
			st = con.prepareStatement(" Select a,b,c,d,e,f from pruebanulos ");
			rs=st.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));
			System.out.println(rs.getInt(2));
			System.out.println(rs.wasNull());
			System.out.println(rs.getDate(3));
			System.out.println(rs.wasNull());
			System.out.println(rs.getDate(4));
			System.out.println(rs.wasNull());
			System.out.println(rs.getString(5));
			System.out.println(rs.wasNull());
			System.out.println(rs.getString(6));
			System.out.println(rs.wasNull());
		
			con.close();
			
			
		}catch(Exception e)	{
			e.printStackTrace();
		}
	

	

}
}
