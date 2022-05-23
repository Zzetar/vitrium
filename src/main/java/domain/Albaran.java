package domain;



import java.sql.Date;

import util.Validator;

import exceptions.DomainException;

public class Albaran {
	String codalbaran;
	java.sql.Date falbaran;
	java.sql.Date fentrada;
	Proveedor proveedor;
	Factura factura;// puede ser nulo,casi siempre lo será
	
	public Albaran() {
		// TODO Auto-generated constructor stub
	}
	public Albaran(String codalbaran, Date falbaran, Date fentrada,
			Proveedor proveedor, Factura factura) {
		this.codalbaran = codalbaran;
		this.falbaran = falbaran;
		this.fentrada = fentrada;
		this.proveedor = proveedor;
		this.factura = factura;
	}
	public Albaran(Date falbaran, Date fentrada	,Proveedor proveedor	) {
		
		this.falbaran = falbaran;
		this.fentrada = fentrada;
		this.proveedor = proveedor;
		
	}
	
	public Albaran(String codalbaran) {
		this.codalbaran = codalbaran;
	}
	
   
	
	
	
	
	public String getCodAlbaran() {
		return codalbaran;
	}
	public void setCodAlbaran(String codalbaran) {
		if (Validator.length(codalbaran, 1, 10)) {
			this.codalbaran = codalbaran.trim();
		} else {
			throw new DomainException("La longitud del codigo del albarana no es válida.");
		}
		
	}
	public java.sql.Date getFAlbaran() {
		return falbaran;
	}
	public void setFAlbaran(java.sql.Date falbaran) {
		this.falbaran = falbaran;
	}
	public java.sql.Date getFEntrada() {
		return fentrada;
	}
	public void setFEntrada(java.sql.Date fentrada) {
		
		this.fentrada = fentrada;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		if (proveedor!=null)
			this.proveedor = proveedor;
		else
			throw new DomainException("El proveedor del albaran es obligatorio");
		
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	

}
