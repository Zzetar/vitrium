package programas.email;

import javax.mail.MessagingException;

import servicios.Email;








public class MandarEmail {

	
	public static void main(String[] args) {
		
     Email e=new Email("smtp.gmail.com", 465, "cuenta emisora","contraseña emisora" );
     try {
		e.enviarEmail("cuenta receptora", "prueba", "hola");
	} catch (MessagingException e1) {
		
		e1.printStackTrace();
	}
     System.out.println("ok");
	}

}
