package com.ranapplications.rcam.global;

/**
 * The User information
 */
public class User{
    private String email, imageUrl, uid, userFirstName, userLastName;

    public void setup(String email, String imageUrl, String uid, String userFirstName, String userLastName) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.uid = uid;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    //ToDo - It has to be implemented
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    //ToDo - It has to be implemented
    public String getUserLastName() {
        return userLastName;
    }

    //ToDo - It has to be implemented
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public User(){}

    //ToDo - It has to be implemented
    public String getEmail() {
        return email;
    }

    //ToDo - It has to be implemented
    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    //ToDo - It has to be implemented
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    //ToDo - It has to be implemented
    public void setUid(String uid) {
        this.uid = uid;
    }
}
