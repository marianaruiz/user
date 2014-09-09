/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paquete.un;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mariana
 */
public class javamail {
    public  void enviarmail(String correoDestinatario,String Asunto,String textoCorreo) {
        try {
            //propediades de la conexion
            Properties props=new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user","pruebasweb99@gmail.com");
            props.setProperty("mail.smtp.auth", "true");
            // props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
           // props.setProperty("mail.smtp.ssl.trust", "smtpserver");
            //iniciar la sesion
            Session session=Session.getDefaultInstance(props);
            /*,
            new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("pruebasweb99@gmail.com","pruebasweb");
                    }
                });*/
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pruebasweb99@gmail.com"));
            //InternetAddress[] para={new InternetAddress(correoDestinatario)};
            message.addRecipient(Message.RecipientType.TO ,new InternetAddress(correoDestinatario));
            message.addRecipient(Message.RecipientType.BCC,new InternetAddress("pruebasweb99@gmail.com"));
            message.setSubject(Asunto);
            message.setText(textoCorreo);
            //envio del mensaje
            Transport trasporte=session.getTransport("smtp");
            trasporte.connect("pruebasweb99@gmail.com","pruebasweb");
            trasporte.sendMessage(message,message.getAllRecipients());
            trasporte.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
}
