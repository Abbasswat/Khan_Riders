package com.example.swat_riders.Model;

public class User {

    String profilePic,username, userFname,address,mobileNo, mail,password,userId,lastmessage;

    public User(String profilePic,String username,String userFname,String mobileNo,String address, String mail, String password, String userId, String lastmessage) {
        this.profilePic = profilePic;
        this.username= username;
        this.userFname=userFname;
        this.address=address;
        this.mobileNo=mobileNo;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastmessage = lastmessage;
    }
    public User(){}

    // signUp Constructer
    public User(String username,String userFname,String address,String mobileNo, String mail, String password) {
        this.username = username;
        this.userFname=userFname;
        this.address=address;
        this.mobileNo=mobileNo;
        this.mail = mail;
        this.password = password;
    }


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }
}
