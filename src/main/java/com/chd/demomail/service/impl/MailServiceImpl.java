package com.chd.demomail.service.impl;

import com.chd.demomail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送简单的文本邮件
     * @param to
     * @param subject
     * @param context
     * @return
     */
    public boolean sendSimpleMail(String to, String subject, String context){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(context);
            simpleMailMessage.setFrom(mailProperties.getUsername());

            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    /**
     *发送简单的文本邮件 并抄送
     * @param to
     * @param subject
     * @param context
     * @param cc
     * @return
     */
    @Override
    public boolean sendSimpleMail(String to, String subject, String context, String... cc) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setCc(cc);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(context);
            simpleMailMessage.setFrom(mailProperties.getUsername());

            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }


    @Override
    public boolean sendHtmlMail(String to, String subject, String context) throws Exception {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context, true);
            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendHtmlMail(String to, String subject, String context, String... cc) throws Exception {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(context, true);
            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendAttachmentsMail(String to, String subject, String context, String filePath) throws Exception {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            // 截取附件名
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            helper.addAttachment(fileName, file);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean sendAttachmentsMail(String to, String subject, String context, String filePath, String... cc) throws Exception {
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(context,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            // 截取附件名
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            helper.addAttachment(fileName, file);
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean sendStaticResourcesMail(String to, String subject, String context, String rscPath, String rscId) throws Exception {
        MimeMessage message = null;
        try {
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
//            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(context,true);

            FileSystemResource res  = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res );
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendStaticResourcesMail(String to, String subject, String context, String rscPath, String rscId, String... cc) throws Exception {
        MimeMessage message = null;
        try {
            // true 表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(context,true);

            FileSystemResource res  = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res );
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
