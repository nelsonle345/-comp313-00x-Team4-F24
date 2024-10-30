package com.comp313sec401.group4.shovelhero.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CalendarView;
import android.widget.TextClock;
import java.util.Date;
import java.util.List;
import android.media.Image;

import androidx.annotation.NonNull;

import com.google.firebase.database.PropertyName;

public class WorkOrder implements Parcelable {

    // attributes
    private int workorderid;
    private String status;
    private int squareFootage;
    private String area; // will be a areas divided by ';'. I.e. driveway;sidewalk

    private String requestDate;

    private String requestUser;

    private String urgency;
    private String specificdate; // Specific Time
    private String instructions;

    private double price;

    // foreign key from customer class
    private int userId;
    private String userAddress;

    // private CalendarView requestDatePicker;
    // private List<String> itemsRequested;
    // private Image completedImage;
    // private Image issueImage;

    public WorkOrder() {}

    // constructor
    public WorkOrder(int workorderid, String area, String instructions, double price, String requestDate, String requestUser,
                     String specificdate, int squareFootage, String status, String urgency,  int userId, String userAddress) {
        this.workorderid = workorderid;
        this.area = area;
        this.instructions = instructions;
        this.price = price;
        this.requestDate = requestDate;
        this.requestUser = requestUser;
        this.specificdate = specificdate;
        this.squareFootage = squareFootage;
        this.status = status;
        this.urgency = urgency;
        this.userId = userId;
        this.userAddress = userAddress;
    }

    protected WorkOrder(Parcel in) {
        this.workorderid = in.readInt();
        this.area = in.readString();
        this.instructions = in.readString();
        this.price = in.readDouble();
        this.requestDate = in.readString();
        this.requestUser = in.readString();
        this.specificdate = in.readString();
        this.squareFootage = in.readInt();
        this.status = in.readString();
        this.urgency = in.readString();
        this.userId = in.readInt();
        this.userAddress = in.readString();
    }

    @PropertyName("workorderId")
    public int getWorkOrderId() { return workorderid; }
    @PropertyName("workorderId")
    public void setWorkOrderId(int workorderid) { this.workorderid = workorderid; }

    @PropertyName("status")
    public String getStatus() { return status; }
    @PropertyName("status")
    public void setStatus(String status) { this.status = status; }

    @PropertyName("squarefoot")
    public int getSquareFootage() { return squareFootage; }
    @PropertyName("squarefoot")
    public void setSquareFootage(int squareFootage) { this.squareFootage = squareFootage; }

    @PropertyName("area")
    public String getArea() { return area; }
    @PropertyName("area")
    public void setArea(String area) { this.area = area; }

    @PropertyName("requestdate")
    public String getRequestDate() { return requestDate; }
    @PropertyName("requestdate")
    public void setRequestDate(String requestDate) { this.requestDate = requestDate; }

    @PropertyName("requestuser")
    public String getRequestUser() { return requestUser; }
    @PropertyName("requestuser")
    public void setRequestUser(String requestUser) { this.requestUser = requestUser; }
    @PropertyName("urgency")
    public String getUrgency() { return urgency; }
    @PropertyName("urgency")
    public void setUrgency(String urgency) { this.urgency = urgency; }
    @PropertyName("specificdate")
    public String getSpecificdate() { return specificdate; }
    @PropertyName("specificdate")
    public void setSpecificdate(String specificdate) { this.specificdate = specificdate; }

    @PropertyName("instructions")
    public String getInstructions() { return instructions; }
    @PropertyName("instructions")
    public void setInstructions(String instructions) { this.instructions = instructions; }
    @PropertyName("price")
    public double getPrice() { return price; }
    @PropertyName("price")
    public void setPrice(double price) { this.price = price; }

    @PropertyName("user_id")
    public int getUserId() { return userId; }
    @PropertyName("user_id")
    public void setUserId(int userId) { this.userId = userId; }

    @PropertyName("address")
    public String getUserAddress() { return userAddress; }

    @PropertyName("address")
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(workorderid);
        parcel.writeString(area);
        parcel.writeString(instructions);
        parcel.writeDouble(price);
        parcel.writeString(requestDate);
        parcel.writeString(requestUser);
        parcel.writeString(specificdate);
        parcel.writeInt(squareFootage);
        parcel.writeString(status);
        parcel.writeString(urgency);
        parcel.writeInt(userId);
        parcel.writeString(userAddress);
    }

    public static final Creator<WorkOrder> CREATOR = new Creator<WorkOrder>() {
        @Override
        public WorkOrder createFromParcel(Parcel in) {
            return new WorkOrder(in);
        }

        @Override
        public WorkOrder[] newArray(int size) {
            return new WorkOrder[size];
        }
    };
}
