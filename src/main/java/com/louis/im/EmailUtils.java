package com.louis.im;


import org.apache.commons.mail.*;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 2017/4/5.
 * 发送邮件
 */
public class EmailUtils {

    //发送简单邮件
    public boolean SendSimpleEmail(MailInfo mailInfo) {
        boolean isSendSuccess = false;
        SimpleEmail email = new SimpleEmail();
        try {
            DefaultAuthenticator auth = new DefaultAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
            email = (SimpleEmail) initSend(auth, "org.apache.commons.mail.SimpleEmail", mailInfo.getChartSet(), mailInfo.getHost(), mailInfo.getFrom(),
                    mailInfo.getTo(), mailInfo.getSubject(), mailInfo.isEnabledTLS(), mailInfo.isEnabledSSL(), mailInfo.isEnabledDebug(),
                    mailInfo.getSmtpPort(), mailInfo.getSslPort());
            email.setMsg(mailInfo.getMessage());
            email.send();
            isSendSuccess = true;
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return isSendSuccess;
    }


    /*
     * 发送html邮件
     */
    public boolean sendHtmlEmail(MailInfo mailInfo) {
        try {
            DefaultAuthenticator auth = new DefaultAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
            HtmlEmail email = (HtmlEmail) initSend(auth, "org.apache.commons.mail.HtmlEmail", mailInfo.getChartSet(), mailInfo.getHost(), mailInfo.getFrom(),
                    mailInfo.getTo(), mailInfo.getSubject(), mailInfo.isEnabledTLS(), mailInfo.isEnabledSSL(), mailInfo.isEnabledDebug(),
                    mailInfo.getSmtpPort(), mailInfo.getSslPort());
            File file = mailInfo.getFile();
            String mUrl = mailInfo.getUrl();
            String cid = "";
            String did = "";
            if (mUrl != null) {
                URL url = new URL(mUrl);
                System.out.println(mUrl);
                String name = mUrl.substring(mUrl.lastIndexOf("//") + 1, mUrl.lastIndexOf("."));
                System.out.println(name);
                cid = email.embed(url, "logo");
            }
            if (file != null) {
                cid = email.embed(file, file.getName());
            }
            StringBuffer msg = new StringBuffer();

            msg.append("<html><body>");
            if (!cid.equals(""))
                msg.append("<img src=cid:").append(cid).append(">");
            else if (!did.equals(""))
                msg.append("<img src=cid:").append(did).append(">");
            msg.append("</body></html>");
            email.setHtmlMsg(msg.toString());  //"<html>Surprise <img src=\"cid:"+cid+"\"></html>"
            // 图片失效显示的文字
            email.setTextMsg("Load failed,try to load again.");

            MailcapCommandMap mc = (MailcapCommandMap) CommandMap
                    .getDefaultCommandMap();
            mc
                    .addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc
                    .addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc
                    .addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc
                    .addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc
                    .addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);

            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return false;
    }

    //发送带附件的邮件
    public boolean sendAttachmentEmail(MailInfo mailInfo) {
        try {
            DefaultAuthenticator auth = new DefaultAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
            MultiPartEmail email = (MultiPartEmail) initSend(auth, "org.apache.commons.mail.MultiPartEmail", mailInfo.getChartSet(), mailInfo.getHost(), mailInfo.getFrom(),
                    mailInfo.getTo(), mailInfo.getSubject(), mailInfo.isEnabledTLS(), mailInfo.isEnabledSSL(), mailInfo.isEnabledDebug(),
                    mailInfo.getSmtpPort(), mailInfo.getSslPort());
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(mailInfo.getPath());
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("wish a pleasant stay!");
            attachment.setName("smile.jpg");
            email.attach(attachment);

            MailcapCommandMap mc = (MailcapCommandMap) CommandMap
                    .getDefaultCommandMap();
            mc
                    .addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc
                    .addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc
                    .addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc
                    .addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc
                    .addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);

            email.send();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return false;
    }

    //初始化email
    private Object initSend(DefaultAuthenticator auth, String className, String chartSet, String host, String from, String to,
                            String subject, boolean enableTLS, boolean eabledSSL, boolean eabledDebug, int smtpPort, String sslProt) throws EmailException, InstantiationException,
            IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, AddressException {
        Class<?> clsTemp = Class.forName(className);
        Object obj = clsTemp.newInstance();
        Class<?> cls = clsTemp.getSuperclass();
        if (className.equals("org.apache.commons.mail.HtmlEmail"))
            cls = cls.getSuperclass();
        Method m01 = cls.getDeclaredMethod("setStartTLSEnabled", boolean.class);
        m01.invoke(obj, enableTLS);
        Method m02 = cls.getDeclaredMethod("setDebug", boolean.class);
        m02.invoke(obj, eabledDebug);
        Method m03 = cls.getDeclaredMethod("setSSLOnConnect", boolean.class);
        m03.invoke(obj, eabledSSL);
        if (eabledSSL) {
            Method m12 = cls.getDeclaredMethod("setSslSmtpPort", String.class); // setSslSmtpPort
            m12.invoke(obj, sslProt);
        }
        Method m13 = cls.getDeclaredMethod("setSmtpPort", int.class); // SmtpPort
        m13.invoke(obj, smtpPort);

        Method m04 = cls.getDeclaredMethod("setHostName", String.class);
        m04.invoke(obj, host);
        Method m05 = cls.getDeclaredMethod("setAuthenticator", Authenticator.class);
        m05.invoke(obj, auth);
        Method m06 = cls.getDeclaredMethod("setFrom", String.class);
        m06.invoke(obj, from);
        Method m07 = cls.getDeclaredMethod("setTo", Class.forName("java.util.Collection"));
        Collection<InternetAddress> inter = new ArrayList<>();
        InternetAddress mTo = new InternetAddress(to);
        inter.add(mTo);
        m07.invoke(obj, inter);
//      Method m08 = cls.getDeclaredMethod("addCc", String.class); //抄送方
//      Method m09 = cls.getDeclaredMethod("addBcc", String.class); //密送方
        Method m10 = cls.getDeclaredMethod("setCharset", String.class);
        m10.invoke(obj, chartSet);
        Method m11 = cls.getDeclaredMethod("setSubject", String.class);
        m11.invoke(obj, subject);

        return obj;
    }

}
