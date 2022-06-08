package domain;

import java.sql.Date;

import exceptions.DomainException;

public class Pedido {

	private int idPedido;
	private int idCliente;
	private String estadoPedido;
	Date fechaPed;

	public Pedido() {

	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public Date getFechaPed() {
		return fechaPed;
	}

	public void setFechaPed(Date fechaPed) {
		this.fechaPed = fechaPed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido [idPedido=");
		builder.append(idPedido);
		builder.append(", idCliente=");
		builder.append(idCliente);
		builder.append(", estadoPedido=");
		builder.append(estadoPedido);
		builder.append(", fechaPed=");
		builder.append(fechaPed);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	

	
	/*
	public Pedido(int nPed, Date fechaPed) {

		this.nPed = nPed;
		this.fechaPed = fechaPed;

	}

	public Pedido(Date fechaPed) {

		this.fechaPed = fechaPed;

	}

	public Pedido(int nPed) {

		this.nPed = nPed;

	}

	public int getnPed() {
		return nPed;
	}

	public void setnPed(int nped) {
		if (nPed <= 99999999 && nped >= 0)
			this.nPed = nped;
		else
			throw new DomainException("El número de pedido no es válido");
	}

	public Date getFechaPed() {
		return fechaPed;
	}

	public void setFechaPed(Date fechaPed) {

		this.fechaPed = fechaPed;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nPed;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (nPed != other.nPed)
			return false;
		return true;
	}
	*/

}
