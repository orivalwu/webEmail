package com.project.webemail.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @Author:wzh
 * @Description:这个类实现了邮件的发送，利用smtp协议，需要自己的qq邮箱号和授权码
 * @Date:Createed in 2020/6/22 12:10
 **/
public class PushService {
    public static Properties properties;
    public static String email; //邮箱
    public static String authCode; //授权码
    public static void init(String _email, String _authCode){
        properties = new Properties();
        properties.setProperty("mail.transport.protocol","smtp");
        properties.setProperty("mail.smtp.host","smtp.qq.com");
        properties.setProperty("mail.smtp.port","25");
        properties.setProperty("mail.debug","true");
        email = _email; //这里是自己的邮箱和授权码
        authCode = _authCode;
    }
    public static String sendTextEmail(String to,String subject,String context)  {
        Session session =  Session.getDefaultInstance(properties);
        //创建MimeMessage对象
        MimeMessage message = new MimeMessage(session);
        //发件人
        try {
            message.setFrom(new InternetAddress(email));
            message.setSubject(subject);
            //设置收件人
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            //发送时间
            message.setSentDate(new Date());
            //发送正文
            message.setText(context);
            //保存最终生成的邮件内容
            message.saveChanges();
            //获得Transport对象
            Transport transport = session.getTransport();
            transport.connect(email,authCode);
            //将Message对象传递给transport对象发送出去
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
            return "发送成功";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "发送失败";
        }

    }

}
