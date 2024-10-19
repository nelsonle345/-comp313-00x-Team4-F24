package com.comp313sec401.group4.shovelhero.models;
import android.media.Image;
import java.util.HashMap;

public class User {

    // attributes
  private String userId;
  private String userName;
  private String password;
  private String userType;

  private String firstName;
  private String lastName;

  private String birthDate;
  private String email;
  private String phoneNumber;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
  private int age;

    //construcutor
    public User(String address,String userId, String userName, String password, String userType, String firstName, String lastName, String birthDate, String email, String phoneNumber, int age) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address=address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    public Image getIdProof() {
        return idProof;
    }

    public void setIdProof(Image idProof) {
        this.idProof = idProof;
    }

    public String getGuardianIdUrl() {
        return guardianIdUrl;
    }

    public void setGuardianIdUrl(String guardianIdUrl) {
        this.guardianIdUrl = guardianIdUrl;
    }

    public boolean isGuardianIdValidated() {
        return guardianIdValidated;
    }

    public void setGuardianIdValidated(boolean guardianIdValidated) {
        this.guardianIdValidated = guardianIdValidated;
    }

    public int getShovellerRadius() {
        return shovellerRadius;
    }

    public void setShovellerRadius(int shovellerRadius) {
        this.shovellerRadius = shovellerRadius;
    }

    private Image profilePic;
  private Image idProof;

    private String  guardianIdUrl; //on Guardian view only
    private boolean guardianIdValidated; // --> only available to app team // I don't think Firebase works with complex Android UI (boolean)?
    private int shovellerRadius; // --> how far is shoveller willing to walk



    // getters and setters







}
