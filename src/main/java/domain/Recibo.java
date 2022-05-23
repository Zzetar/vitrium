package domain;

import java.sql.Date;

import util.Validator;

import exceptions.DomainException;

public class Recibo {
	Factura factura;
	int num;
	java.sql.Date fvto;
	Double importe;
	
	
	
	public Recibo(Factura factura, int num) {
		this.factura = factura;
		this.num = num;
	}
	public Recibo(Factura factura, int num, Date fvto, Double importe) {
		this.factura = factura;
		this.num = num;
		this.fvto = fvto;
		this.importe = importe;
	}
	
	/**
	 * @param factura
	 * @param num
	 */
	
	public Recibo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}
	/**
	 * @param factura the factura to set
	 */
	public void setFactura(Factura factura) {
		if (factura!= null)
			this.factura = factura;
			else
				throw new DomainException("la factura del recibo es obligatoria");	
		
	}
	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		
			if (num.intValue()<999)
		     this.num = num;
			else
			throw new DomainException("el numero del recibo demasiado largo");
		
	}
	/**
	 * @return the fvto
	 */
	public java.sql.Date getFVto() {
		return fvto;
	}
	/**
	 * @param fvto the fvto to set
	 */
	public void setFVto(java.sql.Date fvto) {
		this.fvto = fvto;
	}
	/**
	 * @return the importe
	 */
	public Double getImporte() {
		   return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(Double importe) {
		if (importe!= null){
			if (Validator.lengthDecimal(importe, 13, 3))
				this.importe = importe;
			else
			throw new DomainException("el formato del importe demasiado largo");
		}else
			this.importe = importe;
		
	}
	
	

}

