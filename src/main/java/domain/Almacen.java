package domain;

import util.Validator;
import exceptions.DomainException;

public class Almacen {
	private String codAlm;
	private String descripcion;
	private String direccion;
	private String telf;
	public Almacen() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Almacen(String codAlm, String descripcion, String direccion,
			String telf) {
		this.codAlm = codAlm;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.telf = telf;
	}


	public Almacen(String codAlm) {
		this.codAlm = codAlm;
	}


	
	public Almacen(Almacen almacen) {
	
	setCodAlm( almacen.codAlm);
	setDescripcion( almacen.descripcion) ;
	setdireccion( almacen.direccion);
	settelf( almacen.telf);
	
	
}
	
	
	

	/**
	 * @return the codAlm
	 */
	public String getCodAlm() {
		return codAlm;
	}
	/**
	 * @param codAlm the codAlm to set
	 */
	public void setCodAlm(String codAlm) {
		if (Validator.length(codAlm, 1, 10)) {
			this.codAlm = codAlm.trim();
		} else {
			throw new DomainException("La longitud del codigo del almacen no es válida.");
		}
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		if (Validator.length(descripcion, 1, 50)) {
			this.descripcion = descripcion.trim();
		} else {
			throw new DomainException("La longitud de la descripcion  del almacen no es válida.");
		}
	}
	
	public String gettelf(){
		return telf;
   }


public void settelf(String telf) {
	if(telf==null||telf.length()==0){
		this.telf=telf;
	}
	else {
		if (Validator.telephone(telf, 1, 9)) {
		this.telf = telf.trim();
	    } else {
		throw new DomainException("El teléfono no es válido.");
	    }
	}	
}


public String getdireccion() {
	return direccion;
}


public void setdireccion(String direccion) {
	if (Validator.length(direccion, 1, 50)) {
		this.direccion = direccion.trim();
	} else {
		throw new DomainException("La longitud de la dirección del almacen no es válida.");
	}
}

}
