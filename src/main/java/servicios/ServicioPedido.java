package servicios;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import util.Teclado;
import domain.Articulo;
import domain.LinPed;
import domain.Pedido;
import domain.Proveedor;
import daos.ArticuloDAO;
import daos.LinPedDAO;
import daos.PedidoDAO;
import daos.TransaccionesManager;
import exceptions.DAOException;
import exceptions.ServiceException;
public class ServicioPedido {
	public ServicioPedido() {}
	public   void insertarPedidoCompleto(Pedido pedido,Collection<LinPed> lista ) throws ServiceException{
		TransaccionesManager trans = null ;
		try {
			
			trans = new TransaccionesManager();
			// inserto la cabecera del pedido
			PedidoDAO pedidodao = trans.getPedidoDAO();
			pedidodao.insertarPedido(pedido);
			// inserto todas las lineas del pedido
			LinPedDAO linpeddao = trans.getLinPedDAO();
			if(lista.size()!=0  ){
				for(LinPed linea: lista){
					linea.setIdPedido(pedido.getIdPedido())	;
					linpeddao.insertarLinPed(linea);
				}
				trans.closeCommit();	
			}else{
				trans.closeRollback();
			}
					
			
		} catch (DAOException e) {
			
			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
			
		}
	}
	public List<LinPed>  recuperarDetallePedido(Pedido pedido) throws ServiceException{
		TransaccionesManager trans = null ;
		List<LinPed> objeto=null;
		try {
			trans = new TransaccionesManager();
			LinPedDAO linpeddao = trans.getLinPedDAO();
			objeto=linpeddao.recuperarTodosLinPed(pedido);
			
			

			trans.closeCommit();
		} catch (DAOException e) {
			
			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
			
		}
		return objeto;
	}
	
	public List<Pedido> recuperarPedidosCliente(int idCliente) throws ServiceException {
		TransaccionesManager trans = null ;
		List<Pedido> objeto=null;
		try {
			trans = new TransaccionesManager();
			PedidoDAO pedidoDAO = trans.getPedidoDAO();
			objeto=pedidoDAO.recuperarPedidosCliente(idCliente);

			LinPedDAO linPedDAO = trans.getLinPedDAO();
			ArticuloDAO articuloDao= trans.getArticuloDAO();
			for (Pedido pedido: objeto) {
				pedido.setLineas(linPedDAO.recuperarTodosLinPed(pedido));
				
				for (LinPed linea: pedido.getLineas()) {
					Articulo articulo= articuloDao.recuperarArticulo(linea.getIdArticulo());
					linea.setDescripcion(articulo.getDescripcion());
					linea.setPath(articulo.getPath());
				}
			}
			
			trans.closeCommit();
		} catch (DAOException e) {
			
			try{
				if(trans!=null)
					trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}
			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}
			
		}
		return objeto;
	}
	
	
	
	
	
}
