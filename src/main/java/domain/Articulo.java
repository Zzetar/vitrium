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
	
	/*
	public Articulo(String codArt) {
	
		  this.codArt = codArt;
		
	}
public Articulo(Articulo articulo) {
		
		setCodArt( articulo.codArt) ;
		setDescripcion(articulo.descripcion);
		setPrecioMer(articulo.precioMer);
		setFamilia( articulo.familia);
		
	}

	public static Articulo crearArticulo(String codArt) {
		Articulo articulo=new Articulo();
		articulo.setCodArt( codArt) ;
		return articulo;
		
	}
	
	
	public static Articulo crearArticulo(String codArt, String descripcion, Double precioMer,
			Familia familia) {
		Articulo articulo=new Articulo();
		articulo.setCodArt( codArt) ;
		articulo.setDescripcion(descripcion);
		articulo.setPrecioMer(precioMer);
		articulo.setFamilia( familia);
		return articulo;
	}
	
	
	
	
	
	
	
	  @return the codArt
	 
	public String getCodArt() {
		return codArt;
	}
	
	 @param codArt the codArt to set
	
	public void setCodArt(String codArt) {
		if (Validator.length(codArt, 1, 10)) {
			this.codArt = codArt.trim();
		} else {
			throw new DomainException("La longitud del codigo de articulo no es válida.");
		}
	}

	 @return the descripcion
	
	public String getDescripcion() {
		return descripcion;
	}
	
	 * @param descripcion the descripcion to set
	
	public void setDescripcion(String descripcion) {
		if (Validator.length(descripcion, 1, 50)) {
			this.descripcion = descripcion.trim();
		} else {
			throw new DomainException("La longitud de la descripcion  del articulo no es válida.");
		}
	}
	
	 * @return the preciMer
	
	public Double getPreciMer() {
		return precioMer;
	}
	
	 * @param preciMer the preciMer to set
	
	public void setPrecioMer(Double precioMer) {
		if (precioMer!=null){
		     if(Validator.lengthDecimal(precioMer, 10,3))
		    	this.precioMer=precioMer;
		    else
		    	throw new DomainException("el precio  de mercado no es valido,longitud demasiado larga");
			;
		}else
			this.precioMer=precioMer;
	}
	
	 * @return the familia
	
	public Familia getFamilia() {
		return familia;
	}
	/**
	 * @param familia the familia to set
	 
	public void setFamilia(Familia familia) {
		if (familia!=null)
		this.familia = familia;
		else
		throw new DomainException("La familia del articulo es obligatoria");
			
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codArt == null) ? 0 : codArt.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((familia == null) ? 0 : familia.hashCode());
		result = prime * result
				+ ((precioMer == null) ? 0 : precioMer.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		if (codArt == null) {
			if (other.codArt != null)
				return false;
		} else if (!codArt.equals(other.codArt))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (familia == null) {
			if (other.familia != null)
				return false;
		} else if (!familia.equals(other.familia))
			return false;
		if (precioMer == null) {
			if (other.precioMer != null)
				return false;
		} else if (!precioMer.equals(other.precioMer))
			return false;
		return true;
	}
	*/
	
}
