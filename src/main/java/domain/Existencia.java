package domain;

import java.sql.Date;

import util.Validator;

import exceptions.DomainException;

public class Existencia {
	 public Almacen almacen;
	 public Articulo articulo;
	 public java.sql.Date fcaducidad;
	 public double stockini;
	 public double stockteorico;
	 public double pcmp ;
	 
		public Existencia() {
			// TODO Auto-generated constructor stub
		}
	
	public Existencia(Almacen almacen, Articulo articulo, Date fcaducidad) {
		this.almacen = almacen;
		this.articulo = articulo;
		this.fcaducidad = fcaducidad;
	}

	public Existencia(Almacen almacen, Articulo articulo, Date fcaducidad,
			double stockini, double stockteorico, double pcmp) {
		this.almacen = almacen;
		this.articulo = articulo;
		this.fcaducidad = fcaducidad;
		this.stockini = stockini;
		this.stockteorico = stockteorico;
		this.pcmp = pcmp;
	}
	public Existencia(Existencia existencia) {
		
		
		
		setAlmacen( existencia.almacen);
		setArticulo( existencia.articulo);
		setFCaducidad( existencia.fcaducidad);
		setStockIni( existencia.stockini);
		setStockTeorico(existencia.stockteorico);
		setPCMP( existencia.pcmp);
		
	}


	
	
	/**
	 * @return the codalam
	 */
	public Almacen getAlmacen() {
		return almacen;
	}
	/**
	 * @param codalam the codalam to set
	 */
	public void setAlmacen(Almacen almacen) {
		if (almacen != null) {
			this.almacen = almacen;
		} else {
			throw new DomainException("El   almacen es obligatorio.");
		}
	}
	/**
	 * @return the articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}
	/**
	 * @param articulo the articulo to set
	 */
	public void setArticulo(Articulo articulo) {
		if (articulo != null) {
			this.articulo = articulo;
		} else {
			throw new DomainException("El   articulo es obligatorio.");
		}
	}
	/**
	 * @return the fcaducidad
	 */
	public java.sql.Date getFCaducidad() {
		return fcaducidad;
	}
	/**
	 * @param fcaducidad the fcaducidad to set
	 */
	public void setFCaducidad(java.sql.Date fcaducidad) {
		if (fcaducidad != null) {
			this.fcaducidad = fcaducidad;
		} else {
			throw new DomainException("La fecha de caducidad es obligatoria");
		}
	}
	/**
	 * @return the stockini
	 */
	public double getStockIni() {
		return stockini;
	}
	/**
	 * @param stockini the stockini to set
	 */
	public void setStockIni(double stockini) {
		if (stockini>=0) {
			if (Validator.lengthDecimal(stockini, 10, 3)) {
				this.stockini= stockini;
			} else {
				throw new DomainException("La longitud del stock inicial no es valida");
			}
    	} else {
		throw new DomainException("el stock inicial debe ser mayor  o igual a cero");
	    }
	}
	/**
	 * @return the stockteorico
	 */
	public double getStockTeorico() {
		return stockteorico;
	}
	/**
	 * @param stockteorico the stockteorico to set
	 */
	public void setStockTeorico(double stockteorico) {
		if (stockteorico>=0) {
			if (Validator.lengthDecimal(stockteorico, 10, 3)) {
				this.stockteorico= stockteorico;
			} else {
				throw new DomainException("La longitud del stock teorico no es valida");
			}
    	} else {
		throw new DomainException("el stock teorico debe ser mayor  o igual a cero");
	    }
	}
	/**
	 * @return the pcmp
	 */
	public double getPCMP() {
		return pcmp;
	}
	/**
	 * @param pcmp the pcmp to set
	 */
	public void setPCMP(double pcmp) {
		if (pcmp>=0) {
			if (Validator.lengthDecimal(pcmp, 10, 3)) {
				this.pcmp= pcmp;
			} else {
				throw new DomainException("La longitud del precio coste medio ponderado no es valida");
			}
    	} else {
		throw new DomainException("el precio coste medio ponderado debe ser mayor  o igual a cero");
	    }
	}
	
	 

}
