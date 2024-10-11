package com.comp313sec401.group4.shovelhero;

import com.google.firebase.database.PropertyName;

public class User {
    private String fname, lname, email, username, password, phonenumber;
    private String addedDate;
    private String accountType;

    public User() {
        fname = "";
        lname = "";
        email = "";
        username = "";
        password = "";
        phonenumber = "";
        addedDate = "";
        accountType = "";
    }

    public User(String fname, String lname, String email, String username, String password, String phonenumber, String addedDate, String accountType) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.addedDate = addedDate;
        this.accountType = accountType;
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

    @PropertyName("accounttype")
    public String getAccountType() { return accountType; }
    @PropertyName("accounttype")
    public void setAccountType(String accountType) { this.accountType = accountType; }
}
