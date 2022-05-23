package servicios;

import daos.FacturaDAO;
import daos.TransaccionesManager;
import domain.Factura;
import domain.Factura;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioFactura {

	public ServicioFactura() {}



	public Factura recuperarFactura(Factura factura) throws ServiceException{
		TransaccionesManager trans = null;

		try {
			trans = new TransaccionesManager();
			FacturaDAO FacturaDAO = trans.getFacturaDAO();
			factura = FacturaDAO.recuperarFactura(factura);


			trans.closeCommit();
		} catch (DAOException e) {
			try{
				if(trans!= null)
					trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return factura;
	}


}// fin de la clase
