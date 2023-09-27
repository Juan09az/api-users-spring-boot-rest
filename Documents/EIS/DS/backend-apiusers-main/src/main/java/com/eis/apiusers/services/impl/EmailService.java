package com.eis.apiusers.services.impl;

import com.eis.apiusers.dto.EmailValuesDto;
import com.eis.apiusers.exceptions.AttributeException;
import com.eis.apiusers.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Value("${mail.urlFront}")
    private String urlFront;

    public void sendEmailRestartPassword(EmailValuesDto dto) {
        MimeMessage message= javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            Context context=new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("name", dto.getName());
            model.put("lastname", dto.getLastname());
            model.put("url",urlFront + dto.getTokenPassword());
            context.setVariables(model);
            String htmltext= templateEngine.process("email-template",context);
            helper.setFrom(dto.getMailFrom());
            helper.setTo(dto.getMailTo());
            helper.setSubject(dto.getSubject());
            helper.setText(htmltext,true);
            javaMailSender.send(message);

        }catch (MessagingException e){e.printStackTrace();}
    }
}
