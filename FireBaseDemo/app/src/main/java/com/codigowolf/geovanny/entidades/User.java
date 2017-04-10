package com.codigowolf.geovanny.entidades;

/**
 * Created by Geovanny on 10/04/2017.
 */

public class User {
    String userid;
    String username;
    String edad;

    public User() {
    }

    public User(String userid, String username, String edad) {
        this.userid = userid;
        this.username = username;
        this.edad = edad;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", edad='" + edad + '\'' +
                '}';
    }
}
