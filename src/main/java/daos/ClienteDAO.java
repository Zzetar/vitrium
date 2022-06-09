package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import daos.interfaces.IClienteDAO;
import domain.Cliente;
import domain.FormaPago;
import domain.Iva;
import domain.Tarifa;
import exceptions.DAOException;
import exceptions.DuplicateException;
import recursos.DbQuery;
import recursos.Recursos;



public class ClienteDAO implements IClienteDAO {
	
	
	private Connection con;

	public ClienteDAO(Connection con) {
		this.con = con;
	}
	
	public void insertarCliente(Cliente cliente)throws DAOException{
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(DbQuery.getInsertarCliente(), Statement.RETURN_GENERATED_KEYS);
			st.setString(1, cliente.getNombre());
			st.setString(2, cliente.getApellido1());
			st.setString(3, cliente.getApellido2());
			st.setString(4, cliente.getProvincia());
			st.setString(5, cliente.getLocalidad());
			st.setString(6, cliente.getDireccion());
			st.setInt(7, cliente.getCodigoPostal());
			st.setString(8, cliente.getEmail());
			st.setString(9, cliente.getPassword());
			st.setInt(10, cliente.getClase());
			
					 
			// ejecutamos el insert.			
			st.executeUpdate();
			ResultSet rs= st.getGeneratedKeys();
			rs.next();
			cliente.setIdCliente(rs.getInt(1));
		} catch (SQLException e) {
			if (e.getErrorCode() == MYSQL_DUPLICATE_PK) { //TODO: CAmbiar
				throw new DuplicateException(" cliente ya existe");
			} else {
				throw new DAOException(DB_ERR + ": " + e.getMessage(), e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}	
	}
	public void insertarClienteProcedure(String codcli,
										String razonsocial,
										String telf,
										String direccion,
										String oferta,
										String albfact,
										String iva,
										String tarifa,
										String formapago)throws DAOException {

CallableStatement st = null;

			try {
			
			st = con.prepareCall(DbQuery.getInsertarClienteProcedure());
			st.setString(1, codcli);
			st.setString(2, razonsocial);
			st.setString(3, telf);
			st.setString(4, direccion);
			st.setString(5, oferta);
			st.setString(6,albfact);
			st.setString(7, iva);
			st.setString(8, tarifa);
			st.setString(9, formapago);
			
			
			// ejecutamos el procedimiento.			
			st.execute();
			} catch (SQLException e) {
			if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
			String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
			String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			throw new DAOException(cadena1);
			} else {
			throw new DAOException(DB_ERR, e);
			}
			} finally {
			Recursos.closeCallableStatement(st);
			
			}	

}	

	public Cliente recuperarClienteByEmail(String email) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs =null ;
		Cliente cliente=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarClienteByEmail());
				st.setString(1, email);
				rs=st.executeQuery();
				if (rs.next()){
					cliente= new Cliente();
					//nombre, apellido1, apellido2, provincia, localidad, direccion, codigoPostal, contraseña, clase
					cliente.setNombre(rs.getString(1));
					cliente.setApellido1(rs.getString(2));
					cliente.setApellido2(rs.getString(3));
					cliente.setProvincia(rs.getString(4));
					cliente.setLocalidad(rs.getString(5));
					cliente.setDireccion(rs.getString(6));
					cliente.setCodigoPostal(rs.getInt(7));
					cliente.setPassword(rs.getString(8));
					cliente.setClase(rs.getInt(9));
					cliente.setIdCliente(rs.getInt(10));
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return cliente;
			
	}
	
}
