package com.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@WebServlet(value = "/mail")
public class MailControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thông tin tài khoản Gmail
        String senderEmail = "hoatq4@fpt.edu.vn";
        String senderPassword = "tgqxtmidisuytfpf";

        // Thông tin người nhận
        String recipientEmail = "hoatq4@fpt.edu.vn";

        // Cấu hình kết nối
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tạo đối tượng Session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            // Đặt thông tin người gửi
            message.setFrom(new InternetAddress(senderEmail));
            // Đặt thông tin người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            // Đặt tiêu đề
            message.setSubject("Test Email from Servlet");
            // Đặt nội dung email
            message.setText("This is a test email from your Java Servlet.");

            // Gửi email
            Transport.send(message);

            resp.getWriter().println("Email sent successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
