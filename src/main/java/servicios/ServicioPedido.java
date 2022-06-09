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
	public void modificarPedido(Pedido pedido, List<LinPed> lista) throws ServiceException{
		TransaccionesManager trans = null ;
		try {
			trans = new TransaccionesManager();
			// modifico la cabecera del pedido
			PedidoDAO pedidodao = trans.getPedidoDAO();
			int a=pedidodao.modificarPedido(pedido);
			// borro el detalle antiguo
			LinPedDAO linpeddao = trans.getLinPedDAO();
			linpeddao.borrarLinPed(pedido);
			// inserto la lista	nueva		
			if(lista.size()!=0){		
				for(int i=0;i<lista.size();i++){
			        linpeddao.insertarLinPed(lista.get(i));
				}
				trans.closeCommit();	
			}else{
				trans.closeRollback();
				throw new ServiceException("el detalle del pedido no puede quedar vacio,borre el pedido");//Error Lógico
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
	public int borrarPedido(Pedido pedido) throws ServiceException{
		TransaccionesManager trans = null ;
		int borrado=0;
		try {
			trans = new TransaccionesManager();
			PedidoDAO pedidoDAO = trans.getPedidoDAO();
			pedidoDAO.borrarPedido(pedido);
			// se borran las lineas del pedido por check ondelete cascade en bbDD
			

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
		return borrado;
	}
	public Pedido recuperarCabeceraPedido(Pedido pedido) throws ServiceException{
		TransaccionesManager trans = null ;
		Pedido objeto=null;
		try {
			trans = new TransaccionesManager();
			PedidoDAO pedidoDAO = trans.getPedidoDAO();
			objeto=pedidoDAO.recuperarPedido(pedido);
			
			

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
	
	public void modificarPedidoBloqueo(Pedido pedido, List<LinPed> listaInicial, List<LinPed> listaActualizada) throws ServiceException{
		TransaccionesManager trans = null ;
		LinPedDAO linPedDao = null;
		List<LinPed> listaBloqueo = null;

		try {
			trans = new TransaccionesManager();
			linPedDao = trans.getLinPedDAO();

			listaBloqueo = linPedDao.recuperarLinPedBloqueo(pedido);

			if (listaInicial.size() != listaBloqueo.size()){
				throw new DAOException("Otro usuario ha modificado el pedido antes que usted");
			}	
			if (listaActualizada.size()==0 )
				throw new DAOException("el detalle del pedido no puede quedar vacio,borre el pedido");//Error Lógico

			// habria que ordenar la listaInicial para tener la misma vista que listaBloqueo
			for(int i=0;i<listaBloqueo.size();i++){

				// he implementado los metodos equals en las clase de dominio afectadas	

				if (!listaBloqueo.get(i).equals(listaInicial.get(i))){

					throw new DAOException("Otro usuario ha modificado el pedido antes que usted");//Error alguien ha tocado y ha realizado  cambios
				}
			}	
			// borro el detalle antiguo
			linPedDao.borrarLinPed(pedido);
			// quito duplicados e inserto la lista nueva		
			quitarDuplicados(listaActualizada);
			for(int i = 0; i < listaActualizada.size(); i++){
				linPedDao.insertarLinPed(listaActualizada.get(i));
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
