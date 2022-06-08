package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Carrito implements Serializable {
	private Map<Integer,LinPed> lineasPedido= new HashMap<>();
	
	public void addArticulo(Articulo articulo, int cantidad) {
		if (lineasPedido.containsKey(articulo.getIdArticulo())) {
			lineasPedido.get(articulo.getIdArticulo()).setCantidad(cantidad);
			lineasPedido.get(articulo.getIdArticulo()).setPrecioFinal(cantidad * articulo.getPrecio());
		} else {
			LinPed linea= new LinPed();
			linea.setIdArticulo(articulo.getIdArticulo());
			linea.setCantidad(cantidad);
			linea.setPrecioFinal(cantidad * articulo.getPrecio());
			lineasPedido.put(articulo.getIdArticulo(), linea);
		}
	}
	
	public int precioTotal() {
		int precioTotal= 0;
		for (LinPed linea: lineasPedido.values()) {
			precioTotal+= linea.getPrecioFinal();
		}
		
		return precioTotal;
	}
	
	public LinPed getLinea(int idArticulo) {
		return lineasPedido.get(idArticulo);
	}
}
