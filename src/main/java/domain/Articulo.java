package domain;

import util.Validator;
import exceptions.DomainException;

public class Articulo {
	private int idArticulo;
	private String categoria;
	private int precio;
	private String descripcion;
	private String path;
	
	
	
	public Articulo() {
		
		
	}
	
	


	public int getIdArticulo() {
		return idArticulo;
	}




	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}




	public String getCategoria() {
		return categoria;
	}




	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}




	public int getPrecio() {
		return precio;
	}




	public void setPrecio(int precio) {
		this.precio = precio;
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
		StringBuilder builder = new StringBuilder();
		builder.append("Articulo [idArticulo=");
		builder.append(idArticulo);
		builder.append(", categoria=");
		builder.append(categoria);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", path=");
		builder.append(path);
		builder.append("]");
		return builder.toString();
	}


	/**
	 * @param codArt
	 */
	
	
	
}
