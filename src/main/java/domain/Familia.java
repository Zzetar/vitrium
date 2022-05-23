package domain;

import util.Validator;
import exceptions.DomainException;

public class Familia {
	
 public String codfamilia;
 public String descripcion;
 // constructores
 public Familia() { }
 
// recuperadores 
 public Familia(String codfamilia) {
		
		this.codfamilia = codfamilia;
		
	}
public Familia(String codfamilia, String descripcion) {
	
	this.codfamilia = codfamilia;
	this.descripcion = descripcion;
	
}



public Familia(Familia familia) {
    
    setCodFamilia(familia.codfamilia);
    setDescripcion(familia.descripcion);
	
}

public String getCodFamilia() {
	return codfamilia;
}

public  void setCodFamilia(String codfamilia) {
	if (Validator.length(codfamilia, 1, 10)) {
		this.codfamilia = codfamilia.trim();
	} else {
		throw new DomainException("La longitud del codigo de Familia no es válida.");
	}
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	if (Validator.length(descripcion, 1, 50)) {
		this.descripcion = descripcion.trim();
	} else {
		throw new DomainException("La longitud de la descripcion no es válida.");
	}
}
 
 
 
 
	
}
