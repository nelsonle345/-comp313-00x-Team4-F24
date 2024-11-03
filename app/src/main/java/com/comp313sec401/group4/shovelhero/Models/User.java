package com.comp313sec401.group4.shovelhero.Models;
import android.media.Image;

import com.google.firebase.database.PropertyName;

public class User {

    private int userId;
    private String username;
    private String password;
    // private String userType;
    private String firstname;
    private String lastname;
    private String birthDate;
    private String email;
    private String phonenumber;
    private String profilePic;
    private String idProof;
    private String accounttype;

    private String  guardianIdUrl;
    private String address;
    private int age;

    public User() {}

    public User(String address, int userId, String userName, String password, String accountType, String firstName, String lastName, String birthDate, String email, String phoneNumber, int age) {
        this.userId = userId;
        this.username = userName;
        this.password = password;
        this.accounttype = accountType;
        this.firstname = firstName;
        this.lastname = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phonenumber = phoneNumber;
        this.age = age;
        this.address=address;
    }

    @PropertyName("accounttype")
    public String getAccountType() { return accounttype; }
    @PropertyName("accounttype")
    public void setAccountType(String accountType) { this.accounttype = accountType; }

    @PropertyName("address")
    public String getAddress() { return address; }
    @PropertyName("address")
    public void setAddress(String address) { this.address = address; }

    @PropertyName("userId")
    public int getUserId() { return userId; }
    @PropertyName("userId")
    public void setUserId(int userId) { this.userId = userId; }

    @PropertyName("username")
    public String getUserName() { return username; }
    @PropertyName("username")
    public void setUserName(String userName) { this.username = userName; }

    @PropertyName("password")
    public String getPassword() { return password; }
    @PropertyName("password")
    public void setPassword(String password) { this.password = password; }

    @PropertyName("firstname")
    public String getFirstName() { return firstname; }
    @PropertyName("firstname")
    public void setFirstName(String firstName) { this.firstname = firstName; }

    @PropertyName("lastname")
    public String getLastName() { return lastname; }

    @PropertyName("lastname")
    public void setLastName(String lastName) { this.lastname = lastName; }

//    @PropertyName("accounttype")
//    public String getUserType() { return userType; }
//    @PropertyName("accounttype")
//    public void setUserType(String userType) { this.userType = userType; }

    @PropertyName("birthdate")
    public String getBirthDate() { return birthDate; }
    @PropertyName("birthdate")
    public void setBirthDate(String birthDate) {this.birthDate = birthDate;}

    @PropertyName("email")
    public String getEmail() { return email; }
    @PropertyName("email")
    public void setEmail(String email) { this.email = email; }

    @PropertyName("phonenumber")
    public String getPhoneNumber() { return phonenumber; }
    @PropertyName("phonenumber")
    public void setPhoneNumber(String phoneNumber) { this.phonenumber = phoneNumber; }

    @PropertyName("age")
    public int getAge() { return age; }
    @PropertyName("age")
    public void setAge(int age) { this.age = age; }

    @PropertyName("profilePic")
    public String getProfilePic() { return profilePic; }
    @PropertyName("profilePic")
    public void setProfilePic(String profilePic) { this.profilePic = profilePic; }

    @PropertyName("idProof")
    public String getIdProof() { return idProof; }
    @PropertyName("idProof")
    public void setIdProof(String idProof) { this.idProof = idProof; }

    @PropertyName("guardianIdUrl")
    public String getGuardianIdUrl() { return guardianIdUrl; }
    @PropertyName("guardianIdUrl")
    public void setGuardianIdUrl(String guardianIdUrl) { this.guardianIdUrl = guardianIdUrl; }
}
