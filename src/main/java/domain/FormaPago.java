package domain;

import util.Validator;
import exceptions.DomainException;

public class FormaPago {
	public String codigo;
	public int numerovtos;// obligatorio
	public Integer dias;// puede se nulo
	
	
	public FormaPago() {}
	
	/**
	 * @param codigo
	 */
	public FormaPago(String codigo) {
	
		this.codigo = codigo;
	}
	/**
	 * @param codigo
	 * @param numerovtos
	 * @param dias
	 */
	public FormaPago(String codigo, int numerovtos, Integer dias) {
	
		this.codigo = codigo;
		this.numerovtos = numerovtos;
		this.dias = dias;
	}
	
	public  FormaPago (FormaPago formafago) {
		
		setCodigo(formafago.codigo);
		setNumeroVtos(formafago.numerovtos);
		setDias(formafago.dias);
		
		
	}

	
	


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (Validator.length(codigo, 1, 10)) {
			this.codigo = codigo.trim();
		} else {
			throw new DomainException("La longitud del codigo de la forma de pago no es válida.");
		}
	}

	public int getNumeroVtos() {
		return numerovtos;
	}

	public void setNumeroVtos(int numerovtos) {
		
		if (numerovtos > 999 ){
		throw new DomainException("numero ventimientos no valido");
		}else{
			this.numerovtos=numerovtos;
		}
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		if(dias!=null){
		    if (dias.intValue()<999999) {
		    	this.dias = dias;
	    	} else {
			throw new DomainException("La longitud de los días no es válida.");
		    }
		}else{
			this.dias = dias;
		}
			
			
	}
}
