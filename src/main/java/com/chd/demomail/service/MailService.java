package com.chd.demomail.service;

/**
 * 邮件发送接口
 */
public interface MailService {
    /**
     * 发送简单的邮件
     * @param to
     * @param subject
     * @param context
     * @return
     */
    public boolean sendSimpleMail(String to, String subject, String context);

    /**
     * 发送简单的邮件 并转发
     * @param to
     * @param subject
     * @param context
     * @param cc
     * @return
     */
    public boolean sendSimpleMail(String to, String subject, String context,String... cc);

    /**
     * 发送HTML邮件
     * @param to
     * @param subject
     * @param context
     * @return
     * @throws Exception
     */
    public boolean sendHtmlMail(String to, String subject, String  context ) throws Exception;

    /**
     * 发送HTML邮件 并转发
     * @param to
     * @param subject
     * @param context
     * @param cc
     * @return
     * @throws Exception
     */
    public boolean sendHtmlMail(String to, String subject, String  context, String... cc) throws Exception;

    /**
     * 发送附件邮件
     * @param to
     * @param subject
     * @param context
     * @param filePath
     * @return
     * @throws Exception
     */
    public boolean sendAttachmentsMail(String to, String subject, String  context,String filePath) throws  Exception;

    /**
     * 发送附件邮件 并转发
     * @param to
     * @param subject
     * @param context
     * @param filePath
     * @param cc
     * @return
     * @throws Exception
     */
    public boolean sendAttachmentsMail(String to, String subject, String  context,String filePath, String... cc) throws  Exception;

    /**
     * 发送静态资源邮件
     * @param to
     * @param subject
     * @param context
     * @param rscPath
     * @param rscId
     * @return
     * @throws Exception
     */
    public boolean sendStaticResourcesMail(String to, String subject, String  context,String rscPath, String rscId)throws  Exception;

    /**
     * 发送静态资源邮件并转发
     * @param to
     * @param subject
     * @param context
     * @param rscPath
     * @param rscId
     * @param cc
     * @return
     * @throws Exception
     */
    public boolean sendStaticResourcesMail(String to, String subject, String  context,String rscPath, String rscId, String... cc)throws  Exception;
}
