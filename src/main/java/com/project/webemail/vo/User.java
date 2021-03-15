package com.project.webemail.vo;
/**
 * @Author:wzh
 * @Description:
 * @Date:Createed in 2020/6/20 17:08
 **/
public class User {
    private String userName;
    private String passWord;
    private String email;
    private String authCode;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public User(String userName, String passWord, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", Email='" + email + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
