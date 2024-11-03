package com.comp313sec401.group4.shovelhero.Models;

import com.google.firebase.database.PropertyName;

import java.util.HashMap;

public class User {
    private String userId;
    private String fname, lname, email, username, password, phonenumber;
    private String addedDate;
    private String birthdate;
    private String accountType;
    private HashMap<String, User> linkedUsers;

    public User() {
        fname = "";
        lname = "";
        email = "";
        username = "";
        password = "";
        phonenumber = "";
        addedDate = "";
        birthdate = "";
        accountType = "";
        linkedUsers = new HashMap<>();
    }

    public User(String userId, String accountType, String username, String password, String fname, String lname, String birthdate, String email, String phonenumber) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        //this.addedDate = addedDate;
        this.birthdate = birthdate;
        this.accountType = accountType;
    }

    @PropertyName("userId")
    public String getUserId() {
        return userId;
    }

    @PropertyName("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @PropertyName("firstname")
    public String getFname() { return fname; }
    @PropertyName("firstname")
    public void setFname(String fname) { this.fname = fname; }
    @PropertyName("lastname")
    public String getLname() { return lname; }
    @PropertyName("lastname")
    public void setLname(String lname) { this.lname = lname; }
    @PropertyName("email")
    public String getEmail() { return email; }
    @PropertyName("email")
    public void setEmail(String email) { this.email = email; }
    @PropertyName("username")
    public String getUsername() { return username; }
    @PropertyName("username")
    public void setUsername(String username) { this.username = username; }

    @PropertyName("password")
    public String getPassword() { return password; }
    @PropertyName("password")
    public void setPassword(String password) { this.password = password; }

    @PropertyName("phonenumber")
    public String getPhonenumber() { return phonenumber; }
    @PropertyName("phonenumber")
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

    @PropertyName("date-added")
    public String getAddedDate() { return addedDate; }
    @PropertyName("date-added")
    public void setAddedDate(String addedDate) { this.addedDate = addedDate; }

    @PropertyName("birthdate")
    public String getBirthdate() { return addedDate; }
    @PropertyName("birthdate")
    public void setBirthdate(String birthDate) { this.birthdate = birthDate; }

    @PropertyName("accounttype")
    public String getAccountType() { return accountType; }
    @PropertyName("accounttype")
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public void addLinkedUser(String id, User user) {
        linkedUsers.put(id, user);
    }

    @PropertyName("linkedusers")
    public HashMap<String, User> getLinkedUsers() {
        return linkedUsers;
    }

    @PropertyName("linkedusers")
    public void setLinkedUsers(HashMap<String, User> linkedUsers) {
        this.linkedUsers = linkedUsers;
    }
}
