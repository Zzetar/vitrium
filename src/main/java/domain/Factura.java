package domain;



import java.sql.Date;

import util.Validator;

import exceptions.DomainException;

	public class Factura {
	private int idFactura;
	private int idPedido;
	private int idCliente;
	private int idLinea;
	private int idArticulo;
	



public Factura() {
	// TODO Auto-generated constructor stub
}




public int getIdFactura() {
	return idFactura;
}




public void setIdFactura(int idFactura) {
	this.idFactura = idFactura;
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




@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Factura [idFactura=");
	builder.append(idFactura);
	builder.append(", idPedido=");
	builder.append(idPedido);
	builder.append(", idCliente=");
	builder.append(idCliente);
	builder.append(", idLinea=");
	builder.append(idLinea);
	builder.append(", idArticulo=");
	builder.append(idArticulo);
	builder.append("]");
	return builder.toString();
}




/*
public Factura(int numfactura) {
	this.numfactura = numfactura;
}

public Factura(int numfactura, Date ffactura, double importe,
		Proveedor proveedor) {
	this.numfactura = numfactura;
	this.ffactura = ffactura;
	this.importe = importe;
	this.proveedor = proveedor;
}
public  Factura(Factura factura ) {
	
	
	setNumFactura( factura.numfactura);
	factura.setFFactura(factura.ffactura) ;
	factura.setImporte( factura.importe);
	factura.setProveedor( factura.proveedor);

}






/**
 * @return the numfactura
 
public int getNumFactura() {
	return numfactura;
}

/**
 * @param numfactura the numfactura to set
 
public void setNumFactura(int numfactura) {
	if (numfactura>999999999)
		throw new DomainException("El numero de la factura es demasiado largo");
	else
		this.numfactura = numfactura;
}

/**
 * @return the ffactura
 
public java.sql.Date getFFactura() {
	return ffactura;
}

/**
 * @param ffactura the ffactura to set
 
public void setFFactura(java.sql.Date ffactura) {
		this.ffactura = ffactura;
}

/**
 * @return the importe
 
public double getImporte() {
	return importe;
}

/**
 * @param importe the importe to set
 
public void setImporte(double importe) {
	
	if(Validator.lengthDecimal(importe, 12,3))
		this.importe = importe;
	else
		throw new DomainException("el formato del importe de la factura demasiado largo");
	
	;
	
}

/**
 * @return the proveedor
 
public Proveedor getProveedor() {
	return proveedor;
}

/**
 * @param proveedor the proveedor to set
 
public void setProveedor(Proveedor proveedor) {
	if (proveedor!= null)
	this.proveedor = proveedor;
	else
		throw new DomainException("el Proveedor de la factura es obligatorio");	
		
}

*/

}
