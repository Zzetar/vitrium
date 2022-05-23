package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import recursos.DbQuery;
import recursos.Recursos;
import daos.interfaces.IFormaPagoDAO;
import domain.FormaPago;

import exceptions.DAOException;


public class FormaPagoDAO  implements  IFormaPagoDAO{
	
	
	private Connection con;

	public FormaPagoDAO(Connection con) {
		this.con = con;
	}
	public void insertarFormaPago(FormaPago formapago) throws DAOException{
		PreparedStatement st = null;
		
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarFormaPago());
			st.setString(1, formapago.getCodigo());
			st.setInt(2, formapago.getNumeroVtos());
			if(formapago.getDias()!=null) // OJO PUEDE SER NULO EN LA BASE DE DATOS
			st.setInt(3, formapago.getDias());
			else
			st.setNull(3,  Types.INTEGER);
			
			// rutina de verificacion de mas de una FK
			
			
			
			
			
			// ejecutamos el insert.			
			st.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" FormaPago ya existe");
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
	}
	public int modificarFormaPago(FormaPago formapago)throws DAOException{
		PreparedStatement st = null;
		int modificado=0;
		
		try {
			st = con.prepareStatement(DbQuery.getModificarFormaPago());
			
			
			st.setInt(1, formapago.getNumeroVtos());
			
			if(formapago.getDias()!=null) // OJO PUEDE SER NULO EN LA BASE DE DATOS
			st.setInt(2, formapago.getDias());
			else
			st.setNull(2,  Types.INTEGER);
						
			st.setString(3, formapago.getCodigo());
			
			// rutina de verificacion de mas de una FK
			
			
			
			
			
						
			modificado =st.executeUpdate();
		} catch (SQLException e) {
			if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			
		}
		return modificado;	
	}
	
	public FormaPago recuperarFormaPago(FormaPago formapago) throws DAOException {
		PreparedStatement st = null;
		ResultSet rs =null ;
		FormaPago f=null;
		Integer pdias;
			try {
				
				st = con.prepareStatement(DbQuery.getRecuperarFormaPago());
				st.setString(1,formapago.getCodigo() );
				rs=st.executeQuery();
				if (rs.next()){
					// el Resulset devuelve cero para valores  enteros nulos de la BD
					// lo resuelvo como puedo ¡¡¡¡¡¡¡HE TARDADO UNA TARDE ENTERA!!!!!!.
					pdias=null;
                    if(rs.getObject("dias") ==null )
                	    pdias= null;
                	else
                	    pdias=	rs.getInt("dias"); 
                		   ;
                  
					
					f=new FormaPago(rs.getString(1),
								    rs.getInt(2),
								    pdias); 
				}	
				formapago=f;

			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
			return f;
		
	}
	@Override
	public FormaPago recuperarFormaPagoById(String formaPago)
			throws DAOException {
		// TODO Auto-generated method stub
		
		PreparedStatement st = null;
		ResultSet rs =null ;
		FormaPago f=null;
		Integer pdias;
			try {
				
				st = con.prepareStatement(DbQuery.getRecuperarFormaPago());
				st.setString(1,formaPago );
				rs=st.executeQuery();
				if (rs.next()){
					// el Resulset devuelve cero para valores  enteros nulos de la BD
					// lo resuelvo como puedo ¡¡¡¡¡¡¡HE TARDADO UNA TARDE ENTERA!!!!!!.
					pdias=null;
                    if(rs.getObject("dias") ==null )
                	    pdias= null;
                	else
                	    pdias=	rs.getInt("dias"); 
                		   ;
                  
					
					f=new FormaPago(rs.getString(1),
								    rs.getInt(2),
								    pdias); 
				}	
				

			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		return f;
	}	
	public List<FormaPago> recuperarTodosFormaPago()throws DAOException {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<FormaPago> list = new ArrayList<FormaPago>();
		try {
			Integer pdias;
			st = con.prepareStatement(DbQuery.getRecuperarTodosFormaPago());
			rs = st.executeQuery();
			while (rs.next()) {
				// el Resulset devuelve cero para valores  enteros nulos de la BD
				// lo resuelvo como puedo.¡¡¡¡¡¡¡HE TARDADO UNA TARDE ENTERA!!!!!!.
				pdias=null;
                if(rs.getObject("dias") ==null )
            	    pdias= null;
            	else
            	    pdias=	rs.getInt("dias"); 
            		   ;
				list.add(new FormaPago(rs.getString(1),rs.getInt(2),pdias));
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
