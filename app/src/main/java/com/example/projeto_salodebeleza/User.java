package com.example.projeto_salodebeleza;

public class User {
    private final String userid;
    private final String profileurl;

    public User(String userid, String profileurl) {
        this.userid = userid;
        this.profileurl = profileurl;
    }

    public String getUserid() {
        return userid;
    }

    public String getProfileurl() {
        return profileurl;
    }
}
