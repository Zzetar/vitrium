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
	public int modificarCliente(Cliente cliente)throws DAOException{
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		int modificado = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarCliente());
			st.setString(1, cliente.getRazonSocial());
			st.setString(2, cliente.getTelf());
			st.setString(3, cliente.getDireccion());
			st.setString(4, cliente.getOferta());
			st.setString(5, cliente.getAlbFact());
			st.setString(6, cliente.getIva().getcodIva());
			st.setString(7, cliente.getTarifa().getCodTarifa());
			st.setString(8, cliente.getFormaPago().getCodigo());
			st.setString(9, cliente.getCodCli());
			// rutina de verificacion de mas de una FK
			
			
			
			// para el iva
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarIva());
			  sti.setString(1, cliente.getIva().getcodIva());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" el iva del cliente no existe");
			 }  finally {
				  Recursos.closeResultSet( rs);	
			 }
			 
			
			  // para la tarifa   
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarTarifa());
			  sti.setString(1, cliente.getTarifa().getCodTarifa());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" La Tarifa del  cliente no existe");
		    } finally {
				  Recursos.closeResultSet( rs);	
			 }
					 
			  // para la forma de Pago 
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarFormaPago());
			  sti.setString(1, cliente.getFormaPago().getCodigo());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" La Tarifa del  cliente no existe");
		     }  finally {
			  Recursos.closeResultSet( rs);	
		     }
			
			// ejecutamos el insert.			
			modificado=st.executeUpdate();
		} catch (SQLException e) {
			 if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
		return modificado;
	}
	
	public int borrarCliente(Cliente cliente) throws DAOException{
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarCliente());
			st.setString(1, cliente.getCodCli());
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar Cliente");
				
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
	}
	@Override
	public int borrarClienteById(String cliente) throws DAOException {
		// TODO Auto-generated method stub
		
		PreparedStatement st = null;
		int borrado = 0;
		try {
			st = con.prepareStatement(DbQuery.getBorrarCliente());
			st.setString(1, cliente);
			borrado = st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" No permitido borrar Cliente");
				
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		return borrado;
		
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
	
	public Cliente recuperarCliente(Cliente cliente) throws DAOException{
		PreparedStatement st = null;
		ResultSet rs =null ;
		Cliente objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarCliente());
				st.setString(1,cliente.getCodCli() );
				rs=st.executeQuery();
				if (rs.next()){
	
					objeto=new Cliente(rs.getString(1),
							        rs.getString(2),
							        rs.getString(3),
							        rs.getString(4),
							        rs.getString(5),
							        rs.getString(6),
							        new Iva(rs.getString(7)),
							        new Tarifa(rs.getString(8)),
							        new FormaPago(rs.getString(9))); 
					
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
			
	}
		
	
	@Override
	public Cliente recuperarClienteById(String cliente) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		Cliente objeto=null;
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarCliente());
				st.setString(1,cliente );
				rs=st.executeQuery();
				if (rs.next()){
	
					objeto=new Cliente(rs.getString(1),
							        rs.getString(2),
							        rs.getString(3),
							        rs.getString(4),
							        rs.getString(5),
							        rs.getString(6),
							        new Iva(rs.getString(7)),
							        new Tarifa(rs.getString(8)),
							        new FormaPago(rs.getString(9))); 
					
					
				}		
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return objeto;
	}
	
	public List<Cliente> recuperarTodosCliente()throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Cliente> list = new ArrayList<Cliente>();
		try {
			st = con.prepareStatement(DbQuery.getRecuperarTodosCliente());
			rs = st.executeQuery();
			while (rs.next()) {
	Cliente cliente = new Cliente(rs.getString("cod_cli"),
				                  rs.getString("razon_social"),
				                  rs.getString("telf"),
				                  rs.getString("direccion"),
				                  rs.getString("oferta"),
				                  rs.getString("alb_fact"),
				                  new Iva(rs.getString("cod_iva")),
				                  new Tarifa(rs.getString("cod_tarifa")),
				                  new FormaPago(rs.getString("forma_pago"))
						);
				
				list.add(cliente);
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
		}
		return list;

	}

	public int modificarClienteConcurrente(Cliente clienteActual,
			                              Cliente clienteInicial) 
			                                throws DAOException {
		PreparedStatement st = null;
		PreparedStatement sti = null;
		ResultSet rs=null;
		int modificado = 0;
		try {
			st = con.prepareStatement(DbQuery.getModificarClienteConcurrente());
			st.setString(1, clienteActual.getRazonSocial());
			st.setString(2, clienteActual.getTelf());
			st.setString(3, clienteActual.getDireccion());
			st.setString(4, clienteActual.getOferta());
			st.setString(5, clienteActual.getAlbFact());
			st.setString(6, clienteActual.getIva().getcodIva());
			st.setString(7, clienteActual.getTarifa().getCodTarifa());
			st.setString(8, clienteActual.getFormaPago().getCodigo());
			
			// para los where   -- ojo con campos que pueden ser nulos
			
			st.setString(9, clienteInicial.getCodCli());
			st.setString(10, clienteInicial.getRazonSocial());
			if(clienteInicial.getTelf()== null)
				st.setString(11, "null");	
			else
			st.setString(11, clienteInicial.getTelf());
			st.setString(12, clienteInicial.getDireccion());
			st.setString(13, clienteInicial.getOferta());
			st.setString(14, clienteInicial.getAlbFact());
			st.setString(15, clienteInicial.getIva().getcodIva());
			st.setString(16, clienteInicial.getTarifa().getCodTarifa());
			st.setString(17, clienteInicial.getFormaPago().getCodigo());
			
			// rutina de verificacion de mas de una FK
			
			
			
			// para el iva
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarIva());
			  sti.setString(1, clienteActual.getIva().getcodIva());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" el iva del cliente no existe");
			 }  finally {
				  Recursos.closeResultSet( rs);	
			 }
			 
			
			  // para la tarifa   
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarTarifa());
			  sti.setString(1, clienteActual.getTarifa().getCodTarifa());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" La Tarifa del  cliente no existe");
		    } finally {
				  Recursos.closeResultSet( rs);	
			 }
					 
			  // para la forma de Pago 
			try{
			  sti = con.prepareStatement(DbQuery.getRecuperarFormaPago());
			  sti.setString(1, clienteActual.getFormaPago().getCodigo());
			  rs=sti.executeQuery();
			  if(!rs.next())	
			  throw new DAOException(" La Tarifa del  cliente no existe");
		     }  finally {
			  Recursos.closeResultSet( rs);	
		     }
			
			// ejecutamos el insert.			
			modificado=st.executeUpdate();
		} catch (SQLException e) {
			 if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}	
		return modificado;
		
		
	}

	

	
	
	
}
