package domain;

import java.sql.Date;

import exceptions.DomainException;

public class Pedido {

	private int nPed;
	Date fechaPed;

	public Pedido() {

	}

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
	 */
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
	 */
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

}
