package com.backend.pharmacy_management.controller;

//import com.lowagie.text.Chunk;
//import com.lowagie.text.Document;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Properties;

@CrossOrigin(origins = "http://localhost:4200/")
@Component
@RestController
@EnableScheduling
public class EmailController {
    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("email/send/{name}/{email}")
    public void sendEmail(@PathVariable Optional<String> name,
                          @PathVariable Optional<String> email) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now();
        String day = formatter.format(dayPlusAWeek);

        String array = email.orElse("");
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(array);
        message.setSubject("Đơn thanh toán C0221G1-Pharma");
        message.setText("Chào " + name.orElse("") + "\n"
                + "Bạn đã thanh toán thành công đơn hàng ngày: " + day);
        this.emailSender.send(message);
    }
//
//    spring.mail.host=smtp.gmail.com
//    spring.mail.port=587
//    spring.mail.username=c0221g1drug@gmail.com
//    spring.mail.password=12345678c@
//            spring.mail.properties.mail.smtp.auth=true
//    spring.mail.properties.mail.smtp.starttls.enable=true
    //#region Email + PDF
//    @GetMapping("email/test")
//    public void email() {
//        String smtpHost = "yourhost.com"; //replace this with a valid host
//        int smtpPort = 587; //replace this with a valid port
//
//        String sender = "sender@yourhost.com"; //replace this with a valid sender email address
//        String recipient = "recipient@anotherhost.com"; //replace this with a valid recipient email address
//        String content = "dummy content"; //this will be the text of the email
//        String subject = "dummy subject"; //this will be the subject of the email
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", smtpHost);
//        properties.put("mail.smtp.port", smtpPort);
//        Session session = Session.getDefaultInstance(properties, null);
//
//        ByteArrayOutputStream outputStream = null;
//
//        try {
//            //construct the text body part
//            MimeBodyPart textBodyPart = new MimeBodyPart();
//            textBodyPart.setText(content);
//
//            //now write the PDF content to the output stream
//            outputStream = new ByteArrayOutputStream();
//            writePdf(outputStream);
//            byte[] bytes = outputStream.toByteArray();
//
//            //construct the pdf body part
//            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
//            MimeBodyPart pdfBodyPart = new MimeBodyPart();
//            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
//            pdfBodyPart.setFileName("test.pdf");
//
//            //construct the mime multi part
//            MimeMultipart mimeMultipart = new MimeMultipart();
//            mimeMultipart.addBodyPart(textBodyPart);
//            mimeMultipart.addBodyPart(pdfBodyPart);
//
//            //create the sender/recipient addresses
//            InternetAddress iaSender = new InternetAddress(sender);
//            InternetAddress iaRecipient = new InternetAddress(recipient);
//
//            //construct the mime message
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setSender(iaSender);
//            mimeMessage.setSubject(subject);
//            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
//            mimeMessage.setContent(mimeMultipart);
//
//            //send off the email
//            Transport.send(mimeMessage);
//
//            System.out.println("sent from " + sender +
//                    ", to " + recipient +
//                    "; server = " + smtpHost + ", port = " + smtpPort);
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            //clean off
//            if(null != outputStream) {
//                try { outputStream.close(); outputStream = null; }
//                catch(Exception ex) { }
//            }
//        }
//    }
//
//    public void writePdf(OutputStream outputStream) throws Exception {
//        Document document = new Document();
//        PdfWriter.getInstance(document, outputStream);
//        document.open();
//        Paragraph paragraph = new Paragraph();
//        paragraph.add(new Chunk("hello!"));
//        document.add(paragraph);
//        document.close();
//    }
}
