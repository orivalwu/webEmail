package com.project.webemail.service;

import java.util.ArrayList;
import java.util.List;

//利用多线程实现邮件的群发功能
public class GroupPush implements Runnable {
    private List<String>to = new ArrayList<>(); //发送人作为共享资源
    private String content;
    private String subject;
    @Override
    public void run() {
        //实现多线程去群发信息
        for (String s : to) {
            synchronized (this) {
                PushService.sendTextEmail(s, subject, content);
            }
        }
    }

    //构造方法
    public GroupPush(List<String> to, String content, String subject) {
        this.to = to;
        this.content = content;
        this.subject = subject;
    }
}
