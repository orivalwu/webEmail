package com.project.webemail.vo;

import javafx.geometry.Pos;

/**
 * @Author:wzh
 * @Description:
 * @Date:Createed in 2020/6/21 15:47
 **/
public class Post implements Comparable<Post>{
    private String subject;
    private String sentDate;
    private String from;
    private String content;
    private String description;
    private String to;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post() {
    }

    public Post(String subject, String sentDate, String from, String content, String description) {
        this.subject = subject;
        this.sentDate = sentDate;
        this.from = from;
        this.content = content;
        this.description = description;
    }

    public Post(String subject, String sentDate, String from, String content, String description, String to, int id) {
        this.subject = subject;
        this.sentDate = sentDate;
        this.from = from;
        this.content = content;
        this.description = description;
        this.to = to;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "subject='" + subject + '\'' +
                ", sentDate='" + sentDate + '\'' +
                ", from='" + from + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", to='" + to + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Post o) {
        return -(this.getSentDate().compareTo(o.getSentDate()));
    }
}
