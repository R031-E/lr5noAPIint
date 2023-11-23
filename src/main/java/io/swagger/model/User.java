package io.swagger.model;

public class User {
    private String username;
    private String passHash;

    User(String username, String passHash){
        this.username = username;
        this.passHash = passHash;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassHash(){
        return this.passHash;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassHash(String passHash){
        this.passHash = passHash;
    }

}


