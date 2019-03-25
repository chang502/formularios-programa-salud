/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

//import controller.DBManager;
import java.sql.ResultSet;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Andres
 */
public class SendEmail {

    private static String user;
    private static String password;

    private static void loadProperties() {
        if (user != null) {
            return;
        }
        try {
            Properties prop = new Properties();
            String conf_path = System.getenv("PROSALUD_CONFIG");
            String db_conf_file = conf_path + java.io.File.separator + "email.properties";
            prop.load(new java.io.FileInputStream(db_conf_file));
            user = prop.getProperty("user");
            password = prop.getProperty("password");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }


    public boolean sendAssignationConfirmationEmail(String id_asignacion_deportes) {
        try {
            loadProperties();
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });

            java.util.Map<String, String> params = new java.util.HashMap<>();
            String fields[] = {"id_asignacion_deportes"};
            params.put("id_asignacion_deportes", id_asignacion_deportes);

            DBManager dm = new DBManager();
            ResultSet rs = dm.callGetProcedure("get_assignment_info_for_email", params, fields);

            String persona = "", email = "", disciplina = "";

            while (rs.next()) {
                persona = rs.getString("persona");
                email = rs.getString("email");
                disciplina = rs.getString("disciplina");
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("saludfiusac@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Programa Salud: Confirmación de asignación de disciplina");
            message.setText("Hola, " + persona
                    + "\n\nSe creó una asignación para la disciplina "+disciplina+". Si deseas hacer un cambio a tu asignación, dirígete al departamento de deportes.\n"
                    + "\n\nNo respondas a este email.");

            Transport.send(message);

            return true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return false;

    }

}
