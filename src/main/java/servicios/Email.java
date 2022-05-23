package servicios;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
/* ***************************************************************************
 * 
 *    ESTE SERVICIO SERÁ LLAMADO DESDE OTROS SERVICIOS QUE LO NECESITEN
 *    NO HAY TRANSACCION NI CONEXION A BASE DE DATOS 
 *    ESTOS SERVICIOS PUEDEN FORMAR PARTE DE UNA TRANSACCION QUE LO LLAME Ó NO.
 *    
 * 
 * 
 *     PARA SU CORRECTO funcionamiento necesita la libreria mail.jar entregada por el profesor
 *      dentro de la carpeta librerias
 */
public class Email {
	// private  String SMTP_HOST_NAME = "smtp.gmail.com";
	// private  int SMTP_HOST_PORT = 465;
	// private  String SMTP_AUTH_USER = "cuenta@gmail.com";
	 //private  String SMTP_AUTH_PWD  = "contraseña";
    private  String SMTP_HOST_NAME = null;
    private  int SMTP_HOST_PORT ;
   //emisor del correo. cuenta del gmail
    private  String SMTP_AUTH_USER = null;
   //contraseña de la cuenta del emisor
    private  String SMTP_AUTH_PWD  =null;
    
  
    
    /**
     * 
     * @param servidorcorreo : Servidor de correo 
     * @param puerto: puerto del servidor de correo
     * @param cuenta : cuenta desde la que se manda el Email
     * @param password: contraseña de la cuenta de correo
     */
	 
	 public Email(String servidorcorreo,int  puerto,String cuenta,String password){
		 this.SMTP_HOST_NAME = servidorcorreo;
		 this. SMTP_HOST_PORT = puerto;
		   //emisor del correo. cuenta del gmail
		 this.SMTP_AUTH_USER = cuenta;
		   //contraseña de la cuenta del emisor
		 this.SMTP_AUTH_PWD  = password;
			 
		 
	 };
/**
 * 
 * @param destinatario
 * @param asunto
 * @param contenido
 * @throws MessagingException
 */
	
    public void enviarEmail(String destinatario, String asunto, String contenido)throws MessagingException {
	 
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");
        // props.put("mail.smtps.quitwait", "false");

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
       
        Transport transport;
		
			transport = mailSession.getTransport();
		

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(asunto);
        message.setContent(contenido, "text/plain");

       
			message.addRecipient(Message.RecipientType.TO,
			     new InternetAddress(destinatario));
		

        transport.connect
          (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
       
    	   
       
    }//fin metodo enviarEmail
   /**
    * 
    * @param destinatario
    * @param asunto
    * @param contenido
    * @param fichero que se adjunta(ruta)
    * @throws MessagingException
    */
    public void enviarEmail(String destinatario, String asunto, String contenido,String fichero) throws MessagingException{
   
    	Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");
        // props.put("mail.smtps.quitwait", "false");
        
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
       
        // asunto
        message.setSubject(asunto);
                
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(destinatario)); 
      
     // para contenido
        BodyPart mensageBP=new MimeBodyPart();
        mensageBP.setText(contenido);
        Multipart mPart= new MimeMultipart();
        mPart.addBodyPart(mensageBP);
        // para fichero adjunto
        mensageBP=new MimeBodyPart();
       // String fichero="C:/pasillo.jpg";
        DataSource src= new FileDataSource(fichero);
        mensageBP.setDataHandler(new DataHandler(src));
        mensageBP.setFileName(fichero);
        mPart.addBodyPart(mensageBP);
        message.setContent(mPart);
       
       
        transport.connect
          (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
    
    
    
    
    
    
    
    
    }//fin metodo enviarEmail
   /**
    * 
    * @param destinatario
    * @param asunto
    * @param contenido
    * @param fichero array de ficheros adjuntos(rutas)
    * @throws Exception
    */
    public void enviarEmail(String destinatario, String asunto, String contenido,String [] fichero) throws MessagingException{
    	   
    	Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");
        // props.put("mail.smtps.quitwait", "false");
        
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
       
        // asunto
        message.setSubject(asunto);
                
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(destinatario)); 
      
     // para contenido
        BodyPart mensageBP=new MimeBodyPart();
        mensageBP.setText(contenido);
        Multipart mPart= new MimeMultipart();
        mPart.addBodyPart(mensageBP);
        // para fichero adjunto
        DataSource src;
        for(int i=0;i<fichero.length;i++){
        mensageBP=new MimeBodyPart();
       // String fichero="C:/pasillo.jpg";
        src= new FileDataSource(fichero[i]);
        mensageBP.setDataHandler(new DataHandler(src));
        mensageBP.setFileName(fichero[i]);
        mPart.addBodyPart(mensageBP);
        }
        message.setContent(mPart);
       
       
        transport.connect
          (SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

        transport.sendMessage(message,
            message.getRecipients(Message.RecipientType.TO));
        transport.close();
       
    }//fin metodo enviarEmail
}//fin clase