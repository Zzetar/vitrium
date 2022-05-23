package domain;
import util.Validator;
import exceptions.DomainException;

public class Iva {
	private String codiva;
	private double tipoiva;
	
	
	


	
public Iva() {}

public Iva(String codiva) {
	
	this.codiva = codiva;
}	
	
	/**
	 * @param codiva
	 * @param tipoiva
	 */
	public Iva(String codiva, double tipoiva) {
		
		this.codiva = codiva;
		this.tipoiva = tipoiva;
	}
	


	
	
	
	

	public String getcodIva() {
		return codiva;
	}

	

	public void setcodIva(String codiva) {
		if(codiva == null || codiva.trim().length()==0)
			throw new DomainException("el  codigo  iva es obligatorio");
		if (Validator.length(codiva, 1, 10)) {
			this.codiva = codiva.trim();
		} else {
			throw new DomainException("La longitud del codigo del iva no es válida.");
		}
	}

	public double gettipoIva() {
		return tipoiva;
	}

	public void  settipoIva(double tipoiva) {
		if(Validator.lengthDecimal(tipoiva, 10,3))
			this.tipoiva=tipoiva;
		else
			throw new DomainException("el tipo de iva no es valido,longitud demasiado larga");
			;
		
	}
	
	
}
