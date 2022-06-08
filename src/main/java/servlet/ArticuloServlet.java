package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.Articulo;
import exceptions.ServiceException;
import servicios.ServicioArticulo;

/**
 * Servlet implementation class OperacionesCliente
 */
@WebServlet("/articulo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class ArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 1024;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticuloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String salida= null;
	
		ServicioArticulo sArticulo = new ServicioArticulo();
		try {
			Articulo articulo= articuloDePeticion(request);
			sArticulo.insertarArticulo(articulo);
			request.getSession().setAttribute("articulo", articulo);
			
			salida="/productosDisponibles.jsp";
			request.setAttribute("info","Articulo dado de alta correctamente");
			
		} catch (ServiceException e) {
			e.printStackTrace();// para administrador 
			//Error interno para usuario
			request.setAttribute("mensaje", "error interno");
			salida="/Fin";
		}
		getServletContext().getRequestDispatcher(salida).forward(request, response);
	}

	private Articulo articuloDePeticion(HttpServletRequest request) throws ServiceException {
		try {
			Part imagen= request.getPart("imagen");
			
			File fichero= new File("/vitrium/imagenes/articulos");
			if (!fichero.isDirectory()) {
				fichero.mkdirs();
			}
			try (FileOutputStream fos= new FileOutputStream("/vitrium/imagenes/articulos/" + imagen.getSubmittedFileName());
				InputStream is= imagen.getInputStream();) {
				byte[] buffer= new byte[BUFFER_SIZE];
				int leido;
				while ((leido= is.read(buffer)) != -1) {
					fos.write(buffer, 0, leido);
				}
			}
			
			Articulo articulo= new Articulo();
			articulo.setCategoria(request.getParameter("categoria"));
			articulo.setPrecio(Integer.parseInt(request.getParameter("precio")));
			articulo.setDescripcion(request.getParameter("descripcion"));
			articulo.setPath(imagen.getSubmittedFileName());
			
			return articulo;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		} catch (ServletException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}

	}

}
