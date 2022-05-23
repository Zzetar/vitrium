package domain;

import util.Validator;
import exceptions.DomainException;

public class Tarifa {
	
 public String codtarifa;
 public String descripcion;
 


 
 
 public Tarifa() {}
 public Tarifa(String codtarifa) {
 	
 	this.codtarifa = codtarifa;
 }
public Tarifa(String codtarifa, String descripcion) {
	
	this.codtarifa = codtarifa;
	this.descripcion = descripcion;
}
public Tarifa(Tarifa tarifa) {
	
	setcodTarifa(tarifa.codtarifa);
	setDescripcion(tarifa.descripcion);
	
}








public String getCodTarifa() {
	return codtarifa;
}

public  void setcodTarifa(String codtarifa) {
	if (Validator.length(codtarifa, 1, 10)) {
		this.codtarifa = codtarifa.trim();
	} else {
		throw new DomainException("La longitud del codigo de tarifa no es válida.");
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
