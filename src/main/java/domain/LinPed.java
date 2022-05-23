package domain;

import exceptions.DomainException;
import util.Validator;

public class LinPed {
	private Pedido pedido; 
	private Articulo articulo; 
	private double cantidad; 
	private Double cantidadServ=null ; 
	



	public LinPed() {
	}
	
	public LinPed(Pedido pedido, Articulo articulo) {
		
		this.pedido = pedido;
		this.articulo = articulo;
		
	}

	public LinPed(Pedido pedido, Articulo articulo, double cantidad,
			Double cantidadServ) {
		
		this.pedido = pedido;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.cantidadServ = cantidadServ;
		
	}
	public LinPed(Pedido pedido, Articulo articulo, double cantidad
			) {
		
		this.pedido = pedido;
		this.articulo = articulo;
		this.cantidad = cantidad;
		
		
	}

	

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		if (pedido != null) {
			this.pedido = pedido;
		} else throw new DomainException("El pedido es obligatorio");
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo (Articulo articulo) {
		if (articulo != null) {
			this.articulo = articulo;
		} else throw new DomainException("El artículo es obligatorio");
		
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		
		if (cantidad > 0)
			if(Validator.lengthDecimal(cantidad, 10,3))
				 this.cantidad = cantidad;
				
			else
				throw new DomainException("la cantidad del pedido,longitud demasiado larga");
		     
			
		else 
			throw new DomainException("Cantidad no válida");
	}

	public Double getCantidadServ() {
		return cantidadServ;
	}

	public void setCantidadServ(Double cantidadServ) {
		if (cantidadServ != null){
			if (cantidadServ<=cantidad){
			if (cantidadServ > 0)
				if(Validator.lengthDecimal(cantidadServ, 10,3))
					this.cantidadServ = cantidadServ;
				else
					throw new DomainException("la cantidad servida del pedido,no valida,longitud demasiado larga");
					
				
				
			else throw new DomainException("Cantidad servida no válida");
			}else{
				throw new DomainException("Cantidad servida menor o igual que cantidad");
			}
				
		} else this.cantidadServ = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articulo == null) ? 0 : articulo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cantidad);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((cantidadServ == null) ? 0 : cantidadServ.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinPed other = (LinPed) obj;
		if (articulo == null) {
			if (other.articulo != null)
				return false;
		} else if (!articulo.equals(other.articulo))
			return false;
		if (Double.doubleToLongBits(cantidad) != Double
				.doubleToLongBits(other.cantidad))
			return false;
		if (cantidadServ == null) {
			if (other.cantidadServ != null)
				return false;
		} else if (!cantidadServ.equals(other.cantidadServ))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		return true;
	}

	
	
}
