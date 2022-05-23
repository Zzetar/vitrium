package domain;

import util.Validator;
import exceptions.DomainException;

public class Cliente {
 
	private  String codcli;
	private  String razonsocial;
	private  String telf;// puede ser nulo
	private  String direccion;
	private  String oferta;
	private  String albfact;
	private  Iva iva;
	private  Tarifa  tarifa;
	private  FormaPago formapago;
	
	public Cliente(){}
	
	
public Cliente(String codcli) {this.codcli = codcli;}
	
	/**
	 * @param codcli
	 * @param razonsocial
	 * @param telf
	 * @param direccion
	 * @param oferta
	 * @param albfact
	 * @param iva
	 * @param tarifa
	 * @param formapago
	 */
	public Cliente(String codcli, String razonsocial, String telf,
			String direccion, String oferta, String albfact, Iva iva,
			Tarifa tarifa, FormaPago formapago) {
		
		this.codcli = codcli;
		this.razonsocial = razonsocial;
		this.telf = telf;
		this.direccion = direccion;
		this.oferta = oferta;
		this.albfact = albfact;
		this.iva = iva;
		this.tarifa = tarifa;
		this.formapago = formapago;
	}
	public Cliente(Cliente cliente){
    	 
    	setCodCli(cliente.codcli);
    	setRazonSocial(cliente.razonsocial);
    	setTelf(cliente.telf);
    	setDireccion(cliente.direccion);
    	setOferta(cliente.oferta);
    	setAlbFact (cliente.albfact);
    	setIva (cliente.iva);
    	setTarifa (cliente.tarifa);
    	setFormaPago (cliente.formapago);
    	
    	
    }

  

	


	/**
	 * @param codcli
	 */
	


	public String getCodCli() {
		return codcli;
	}
	public void setCodCli(String codcli) {
		if (Validator.length(codcli, 1, 10)) {
			this.codcli = codcli.trim();
		} else {
			throw new DomainException("La longitud del codigo del cliente no es válida.");
		}
	}
	
	public String getRazonSocial() {
		return razonsocial;
	}
	
	public void setRazonSocial(String razonsocial) {
		if (Validator.length(razonsocial, 1, 20)) {
			this.razonsocial = razonsocial.trim();
		} else {
			throw new DomainException("La longitud de la razón social del cliente no es válida.");
		}
	}
	
	public String getTelf(){
			return telf;
	}
	
	
	public void setTelf(String telf) {
		if(telf==null || telf.trim().length()==0){
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
			throw new DomainException("La longitud de la dirección del cliente no es válida.");
		}
	}
	public String getOferta() {
		return oferta;
	}
	
	public void setOferta(String oferta) {
		if (Validator.length(oferta, 1, 1)) {
			if (oferta.compareTo("S")==0||oferta.compareTo("N")==0)
				this.oferta = oferta;
			else 
				throw new DomainException("oferta S/N.");
		} else {
			throw new DomainException("La longitud del campo oferta no es válida.");
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
			throw new DomainException("El  iva del cliente es obligatorio.");
		}
	}
	
	
	public Tarifa getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(Tarifa tarifa) {
		if (tarifa !=null) {
			this.tarifa = tarifa;
		} else {
			throw new DomainException("La tarifa del cliente es obligatoria.");
		}
	}
	
	
	public FormaPago getFormaPago() {
		return formapago;
	}

	
	public void setFormaPago(FormaPago formapago) {
		if (formapago!=null ) {
			this.formapago = formapago;
		} else {
			throw new DomainException("la forma de pago del cliente es obligatoria.");
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [codcli=" + codcli + ", razonsocial=" + razonsocial
				+ ", telf=" + telf + ", direccion=" + direccion + ", oferta="
				+ oferta + ", albfact=" + albfact + ", iva=" + iva
				+ ", tarifa=" + tarifa + ", formapago=" + formapago + "]";
	}
	public String toStringFashion() {
		return "Cliente [codcli=" + codcli + ", razonsocial=" + razonsocial
				+ ", telf=" + telf + ", direccion=" + direccion + ", oferta="
				+ oferta + ", albfact=" + albfact + ", iva=" + iva.gettipoIva()
				+ ", tarifa=" + tarifa.getDescripcion() + ", formapago=(" + formapago.getNumeroVtos()+" vencinientos a  "+formapago.getDias()+" Días)" + "]";
	}
	
	
}
