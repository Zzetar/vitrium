package domain;

import util.Validator;
import exceptions.DomainException;

public class Proveedor {
 
	private String codpro;
	private String razonsocial;
	private String telf;
	private String direccion;
	private String albfact;
	private String codpos;
	private Iva iva;
	private FormaPago formapago;
	
	public Proveedor(){}
	
	public Proveedor(String codpro, String razonsocial, String telf,
			String direccion, String albfact, String codpos,Iva iva,
			 FormaPago formapago) {
		
		this.codpro = codpro;
		this.razonsocial = razonsocial;
		this.telf = telf;
		this.direccion = direccion;
		this.albfact = albfact;
		this.codpos = codpos;
		this.iva = iva;
		this.formapago = formapago;
		
	}
	public Proveedor(Proveedor proveedor){
    	
		
		setCodPro( proveedor.codpro);
		setRazonSocial( proveedor.razonsocial);
		setTelf( proveedor.telf);
		setDireccion( proveedor.direccion);
		setAlbFact ( proveedor.albfact);
		setCodPos (  proveedor.codpos);
		setIva ( proveedor.iva);
		setFormaPago ( proveedor.formapago);
    	
    	
    	
    }

    

	/**
	 * @param codcli
	 */
	public Proveedor(String codpro) {
	
		this.codpro = codpro;
		
	}
	

	


	


	public String getCodPro() {
		return codpro;
	}
	public void setCodPro(String codpro) {
		if (Validator.length(codpro, 1, 10)) {
			this.codpro = codpro.trim();
		} else {
			throw new DomainException("La longitud del codigo del Proveedor no es válida.");
		}
	}
	
	public String getRazonSocial() {
		return razonsocial;
	}
	
	public void setRazonSocial(String razonsocial) {
		if (Validator.length(razonsocial, 1, 20)) {
			this.razonsocial = razonsocial.trim();
		} else {
			throw new DomainException("La longitud de la razón social del Proveedor no es válida.");
		}
	}
	
	public String getTelf(){
			return telf;
	}
	
	
	public void setTelf(String telf) {
		if(telf==null|| telf.trim().length()==0){
			this.telf=null;
		}
		else {
			if (Validator.telephone(telf, 1, 9)) {
			this.telf = telf.trim();
		    } else {
			throw new DomainException("El teléfono no es válido.");
		    }
		}	
	}
	
	
	public String getDireccion() {
		return direccion;
	}
	
	
	public void setDireccion(String direccion) {
		if (Validator.length(direccion, 1, 50)) {
			this.direccion = direccion.trim();
		} else {
			throw new DomainException("La longitud de la dirección del Proveedor no es válida.");
		}
	}
	
	public String getCodpos() {
		return codpos;
	}

	
	public void setCodPos(String codpos) {
		if (Validator.length(codpos, 1, 50)) {
			this.codpos = codpos.trim();
		} else {
			throw new DomainException("La longitud del codigo postal no es válida.");
		}
	
	}
	
	
	public String getAlbFact() {
		return albfact;
	}
	
	public void setAlbFact(String albfact) {
		if (Validator.length(albfact, 1, 1)) {
			if (albfact.compareTo("S")==0||albfact.compareTo("N")==0)
				this.albfact = albfact;
			else 
				throw new DomainException("El valor para el campo albfact no valido S/N.");
		} else {
			throw new DomainException("La longitud del campo albarán/factura no es válida.");
		}	
	}
	
	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		if (iva != null) {
			this.iva = iva;
		} else {
			throw new DomainException("El  iva del Proveedor es obligatorio.");
		}
	}
	
	
	
	
	
	public FormaPago getFormaPago() {
		return formapago;
	}

	
	public void setFormaPago(FormaPago formapago) {
		if (formapago!=null ) {
			this.formapago = formapago;
		} else {
			throw new DomainException("la forma de pago del Proveedor es obligatoria.");
		}
	}




	


	
	
	
	
}
