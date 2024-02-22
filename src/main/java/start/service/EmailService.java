package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import start.entity.User;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMailTemplate(User user){


        try{
            Context context = new Context();

            context.setVariable("name", user.getName());
            context.setVariable("email", "http://mycremo.art/confirm-success?id="+user.getId());

            String text = templateEngine.process("emailtemplate", context);

            // Creating a simple mail message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // Setting up necessary details
            mimeMessageHelper.setFrom("admin@gmail.com");
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject("Verify Account");
            javaMailSender.send(mimeMessage);
        }catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
    }


}
