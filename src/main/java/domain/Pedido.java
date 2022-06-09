package domain;

import java.sql.Date;
import java.util.List;

import exceptions.DomainException;

public class Pedido {

	private int idPedido;
	private int idCliente;
	private String estadoPedido;
	private Date fechaPed;
	private int importe;
	private List<LinPed> lineas;

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

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

	public List<LinPed> getLineas() {
		return lineas;
	}

	public void setLineas(List<LinPed> lineas) {
		this.lineas = lineas;
	}
	
	
}
