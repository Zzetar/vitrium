package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articulo/imagen")
public class ImagenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 1024*10;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (FileInputStream fis= new FileInputStream("/vitrium/imagenes/articulos/" + request.getParameter("fichero"))) {
			OutputStream os= response.getOutputStream();
			byte[] buffer= new byte[BUFFER_SIZE];
			int leido;
			while ((leido= fis.read(buffer)) != -1) {
				os.write(buffer, 0, leido);
			}
		}
	}
}
