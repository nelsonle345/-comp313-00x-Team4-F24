package com.comp313sec401.group4.shovelhero.Models;

import com.google.firebase.database.PropertyName;

public class Property {
    private String id;
    private String address;
    private String instructions;

    public Property(){}

    public Property(String id, String address, String instructions){
        this.id = id;
        this.address=address;
        this.instructions = instructions;
    }

    @PropertyName("id")
    public String getId() {
        return id;
    }
    @PropertyName("id")
    public void setId(String id) {
        this.id = id;
    }
    @PropertyName("address")
    public String getAddress() {
        return address;
    }
    @PropertyName("address")
    public void setAddress(String address) {
        this.address = address;
    }
    @PropertyName("instructions")
    public String getInstructions() {
        return instructions;
    }
    @PropertyName("instructions")
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
