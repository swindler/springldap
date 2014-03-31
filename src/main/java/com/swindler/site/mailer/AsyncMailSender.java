package com.swindler.site.mailer;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Created by Artyom_Borkowsky on 3/27/14.
 */
@Service
public class AsyncMailSender extends JavaMailSenderImpl {
    @Override
    @Async
    public void send(MimeMessage simpleMessage) throws MailException {
        super.send(simpleMessage);
    }

    @Override
    @Async
    public void send(MimeMessage[] simpleMessages) throws MailException {
        super.send(simpleMessages);
    }
}
