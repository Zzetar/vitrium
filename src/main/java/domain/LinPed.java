package domain;

import java.io.Serializable;

public class LinPed implements Serializable {
	private int idLinea;
	//private Articulo articulo;
	private int idArticulo; 
	private int cantidad; 
	private int gastosEnvio; 
	private int precioFinal;
	private int idPedido;
	
	
	private String descripcion;
	private String path;
	
	public int getIdLinea() {
		return idLinea;
	}
	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}
	public int getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getGastosEnvio() {
		return gastosEnvio;
	}
	public void setGastosEnvio(int gastosEnvio) {
		this.gastosEnvio = gastosEnvio;
	}
	public int getPrecioFinal() {
		return precioFinal;
	}
	public void setPrecioFinal(int precioFinal) {
		this.precioFinal = precioFinal;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "LinPed [idLinea=" + idLinea + ", idArticulo=" + idArticulo + ", cantidad=" + cantidad + ", gastosEnvio="
				+ gastosEnvio + ", precioFinal=" + precioFinal + ", idPedido=" + idPedido + "]";
	}
	
}
