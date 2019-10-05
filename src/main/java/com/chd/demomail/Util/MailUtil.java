package com.chd.demomail.Util;

import com.chd.demomail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

@Component
@Slf4j
public class MailUtil {

    @Autowired
    private MailService mailService;

    private static MailUtil mailUtil;

    @PostConstruct
    public void  init(){
        mailUtil = this;
        mailUtil.mailService = this.mailService;
    }

    /**
     * 发送简单的文本邮件
     * @param to
     * @param subject
     * @param context
     */
    public static void sendSimpleMail(String to, String subject, String context,String... cc){
        try {
            if (StringUtils.isEmpty(cc)){
                mailUtil.mailService.sendSimpleMail(to,subject,context);
            }else {
                mailUtil.mailService.sendSimpleMail(to,subject,context,cc);
            }
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.info("邮件发送失败");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param to
     * @param subject
     * @param context
     * @param cc
     */
    public static void sendHtmlMail (String to, String subject, String  context, String... cc) {
        try {
            if(StringUtils.isEmpty(cc)){
                mailUtil.mailService.sendHtmlMail(to,subject,context);
            }else {
                mailUtil.mailService.sendHtmlMail(to,subject,context,cc);
            }
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.info("邮件发送失败");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param to
     * @param subject
     * @param context
     * @param filePath
     * @param cc
     */
    public static void sendAttachmentsMail(String to, String subject, String  context,String filePath, String... cc){
        try {
            if(StringUtils.isEmpty(cc)){
                mailUtil.mailService.sendAttachmentsMail(to,subject,context,filePath);
            }else {
                mailUtil.mailService.sendAttachmentsMail(to,subject,context,filePath,cc);
            }
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.info("邮件发送失败");
            e.printStackTrace();
        }

    }

    /**
     *
     * @param to
     * @param subject
     * @param context
     * @param rscPath
     * @param rscId
     * @param cc
     */
    public static void sendStaticResourcesMail(String to, String subject, String  context,String rscPath, String rscId, String... cc){
        try {
            if(StringUtils.isEmpty(cc)){
                mailUtil.mailService.sendStaticResourcesMail(to,subject,context,rscPath,rscId);
            }else {
                mailUtil.mailService.sendStaticResourcesMail(to,subject,context,rscPath,rscId,cc);
            }log.info("邮件发送成功");
        } catch (Exception e) {
            log.info("邮件发送失败");
            e.printStackTrace();
        }

    }

    /**
     * 发送带模板的邮件，未完成，禁用
     * @param to
     * @param subject
     * @param context
     * @param cc
     */
    public static void sendTemplateMail(String to, String subject, String  context, String... cc){
        try {
//            Context context = new Context();
//            context.setVariable("github_url", "https://github.com/Folgerjun");
//            context.setVariable("blog_url", "http://putop.top/");
//            String emailContent = templateEngine.process("mailTemplate", context);

//            mailUtil.mailService.sendHtmlMail(MAIL_TO, "这是模板邮件", emailContent);

            if(StringUtils.isEmpty(cc)){
                mailUtil.mailService.sendHtmlMail(to,subject,context);
            }else {
                mailUtil.mailService.sendHtmlMail(to,subject,context,cc);
            }log.info("邮件发送成功");
        } catch (Exception e) {
            log.info("邮件发送失败");
            e.printStackTrace();
        }
    }

    /**
     * 读取邮件操作
     * @param protocol
     * @param host
     * @param port
     * @param userName
     * @param pwd
     * @throws Exception
     */
    public static void  readMail(String protocol , String host, String port ,String userName ,String pwd) throws  Exception{
        Folder folder = null;
        Store store = null;
        try {
            // 准备连接服务器的会话信息
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol",protocol ); //"imap"
            properties.setProperty("mail.imap.host", host); //"imap.163.com"
            properties.setProperty("mail.imap.port", port);//"143"

            // 创建Session实例对象
            Session session = Session.getInstance(properties);

            // 创建IMAP协议的Store对象
            store = session.getStore(protocol);//"imap"

            // 连接邮件服务器
            store.connect(userName, pwd);

            // 获得收件箱
            folder = store.getFolder("INBOX");
            // 以读写模式打开收件箱
            folder.open(Folder.READ_WRITE);
            // 获得收件箱的邮件列表
            Message[] messages = folder.getMessages();
            // 打印不同状态的邮件数量
            System.out.println("收件箱中共" + messages.length + "封邮件!");
            System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
            System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
            System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");
            System.out.println("------------------------开始解析邮件----------------------------------");
            int total = folder.getMessageCount();
            System.out.println("-----------------您的邮箱共有邮件：" + total + " 封--------------");
            // 得到收件箱文件夹信息，获取邮件列表
            Message[] msgs = folder.getMessages();
            System.out.println("\t收件箱的总邮件数：" + msgs.length);
            for (int i = 0; i < total; i++) {
                Message a = msgs[i];
                //   获取邮箱邮件名字及时间
                System.out.println(a.getReplyTo());

                System.out.println("==============");
//                System.out.println(a.getSubject() + "   接收时间：" + a.getReceivedDate().toLocaleString()+"  contentType()" +a.getContentType());
            }
            System.out.println("\t未读邮件数：" + folder.getUnreadMessageCount());
            System.out.println("\t新邮件数：" + folder.getNewMessageCount());
            System.out.println("----------------End------------------");




            log.info("邮件读取成功");
        } catch (Exception e) {
            log.info("邮件读取失败");
            e.printStackTrace();
        }finally {
            // 关闭资源
            folder.close(false);
            store.close();
        }
    }

}
