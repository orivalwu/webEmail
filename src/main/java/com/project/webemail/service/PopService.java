package com.project.webemail.service;


import javax.mail.*;
import java.util.Properties;

//该类实现了邮件的接受
public class PopService {
    private Session session;
    private Store store;
    private Folder folder;
    private String protocol = "imaps";
    private String file = "INBOX";
    private String host = "imap.qq.com";

    public PopService() {
    }
    /*
     * @param: []
     * @return: boolean
     * @description:判断是否连接到服务器
     */
    public boolean isLogin(){
        return store.isConnected();
    }

    public void login(String username, String password) throws MessagingException {
        URLName url = new URLName(protocol,host,993,file,username,password);
        if(session == null){
            Properties props = null;
            try{
                props = System.getProperties();
            }catch (SecurityException sex){
                props = new Properties();
            }
            session = Session.getInstance(props,null);
        }
        store = session.getStore(url);
        store.connect();
        folder = store.getFolder(url);
        folder.open(Folder.READ_WRITE);
    }

    public void logout() throws MessagingException {
        folder.close(false);
        store.close();
        store = null;
        session = null;
    }

    public int getMessageCount(){
        int messageCount = 0;
        try {
            messageCount = folder.getMessageCount();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return messageCount;
    }

    public Message[] getMessages() throws MessagingException {
        return folder.getMessages();
    }
}
