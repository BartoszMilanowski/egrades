package pl.coderslab.egrades.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.coderslab.egrades.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(User user, String password) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setFrom("info.egrades@gmail.com");
            helper.setSubject("Rejestracja w eGrades");
            helper.setText(user.getName() + "/n" + user.getEmail() + "/n" + password);

        } catch (MessagingException e){
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
