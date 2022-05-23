package domain;

import java.sql.Date;

import util.Validator;
import exceptions.DomainException;

public class ArtPro {
	private Proveedor proveedor;  //  NOT NULL VARCHAR2(10)
	private Articulo articulo;//  NOT NULL VARCHAR2(10)
	private String CodArtPro;//    NOT NULL VARCHAR2(10)
	private String Descripcion;//   NOT NULL VARCHAR2(50)
	private double Precio;//   NOT NULL NUMBER(10,3)
	private java.sql.Date FTarifa;// date
	private Double  PrecioAnt;// NUMBER(10,3)

	// constructores  con datos de la base de datos, es decir validados;	
	public ArtPro() {
	}
	public ArtPro(Proveedor  proveedor, Articulo articulo) {
		this.proveedor = proveedor;
		this.articulo = articulo;
	}
	public ArtPro(Proveedor proveedor, Articulo articulo, String codArtPro,
			String descripcion, double precio, Date ftarifa, Double precioAnt) {
		this.proveedor = proveedor;
		this.articulo = articulo;
		this.CodArtPro = codArtPro;
		this.Descripcion = descripcion;
		this.Precio = precio;
		this.FTarifa = ftarifa;
		this.PrecioAnt = precioAnt;
	}
	
	
	// constructores  con datos externos a la base de datos,es decir sin validar;	
	public ArtPro(ArtPro artpro) {
		
		
		
		setProveedor(artpro.proveedor);
		setArticulo(artpro.articulo);
		setCodArtPro(artpro.CodArtPro);
		setDescripcion(artpro.Descripcion);
		setPrecio(artpro.Precio);
		setFTarifa (artpro.FTarifa );
		setPrecioAnt (artpro.PrecioAnt );
				
		
	}
	
	
	/**
	 * @return the codPro
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}
	/**
	 * @param codPro the codPro to set
	 */
	public void setProveedor(Proveedor proveedor) {
		
			if (proveedor!=null){
				this.proveedor = proveedor;
			} else {
				throw new DomainException("el proveedor es obligatorio")	;
				
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
		if(articulo!=null)
		this.articulo = articulo;
		else 
	  throw new DomainException("el articulo  es obligatorio.");
	}
	/**
	 * @return the codArtPro
	 */
	public String getCodArtPro() {
		return CodArtPro;
	}
	/**
	 * @param codArtPro the codArtPro to set
	 */
	public void setCodArtPro(String codArtPro) {
		if (Validator.length(codArtPro, 1, 10)) {
			this.CodArtPro = codArtPro.trim();
		} else {
			throw new DomainException("La longitud del codigo de articulo no es válida.");
		}
		
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return Descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		
		if (Validator.length(descripcion, 1, 50)) {
			this.Descripcion = descripcion.trim();
		} else {
			throw new DomainException("La longitud de la descripcion  del articulo no es válida.");
		}
	}
	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return Precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		if(Validator.lengthDecimal(precio, 10,3))
			this.Precio = precio;
		else
			throw new DomainException("el precio no es valido,longitud demasiado larga");
			;
		
	}
	/**
	 * @return the fTarifa
	 */
	public java.sql.Date getFTarifa() {
		return FTarifa;
	}
	/**
	 * @param fTarifa the fTarifa to set
	 */
	public void setFTarifa(java.sql.Date ftarifa) {
		this.FTarifa = ftarifa;
	}
	/**
	 * @return the precioAnt
	 */
	public Double getPrecioAnt() {
		return PrecioAnt;
	}
	/**
	 * @param precioAnt the precioAnt to set
	 */
	public void setPrecioAnt(Double precioAnt) {
		if(precioAnt!= null){
			if(Validator.lengthDecimal(precioAnt.doubleValue(), 10,3))
				this.Precio = precioAnt;
			else
				throw new DomainException("el precio anterior no es valido,longitud demasiado larga");
				;
		
		}else		this.PrecioAnt = precioAnt;
	}
	
	
	
	
	
	
}// fin de la clase