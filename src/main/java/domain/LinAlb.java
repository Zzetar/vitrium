package domain;


import util.Validator;
import exceptions.DomainException;


public class LinAlb {
   Albaran albaran;
   Existencia existencia;
   double cantent;
   double precioent;
  
   public LinAlb() {
		// TODO Auto-generated constructor stub
	}  
public LinAlb(Albaran albaran, Existencia existencia) {
	this.albaran = albaran;
	this.existencia = existencia;
}



public LinAlb(Albaran albaran, Existencia existencia, double cantent,
		double precioent) {
	this.albaran = albaran;
	this.existencia = existencia;
	this.cantent = cantent;
	this.precioent = precioent;
}







   





public void setAlbaran(Albaran albaran) {
	if (albaran!=null){
		this.albaran=albaran;
	}else throw new DomainException ("El albarán es obligatorio");
}
   
public void setExistencia(Existencia existencia) {
	if (existencia!=null){
		this.existencia=existencia;
	}else throw new DomainException ("La existencia  es obligatorio");
}
public double getCantEnt() {
	return cantent;
}
public void setCantEnt(double cantent) {
	if(Validator.lengthDecimal(cantent, 10,3))
		this.cantent = cantent;
	else
		throw new DomainException("el tipo de iva no es valido,longitud demasiado larga");
		;
	
}
public double getPrecioEnt() {
	return precioent;
}
public void setPrecioEnt(double precioent) {
	if(Validator.lengthDecimal(precioent, 10,3))
		this.precioent = precioent;
	else
		throw new DomainException("el tipo de iva no es valido,longitud demasiado larga");
		;
	
}
public Albaran getAlbaran() {
	return albaran;
}

public Existencia getExistencia() {
	return existencia;
}


   
}
