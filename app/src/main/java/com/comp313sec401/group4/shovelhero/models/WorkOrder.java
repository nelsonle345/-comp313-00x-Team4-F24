package com.comp313sec401.group4.shovelhero.models;

import android.widget.CalendarView;
import android.widget.TextClock;
import java.util.Date;
import java.util.List;
import android.media.Image;
public class WorkOrder {


    // attributes
    private String workOrderId;
    private String status;
    private int squareFootage;
    private boolean isDerivewayChecked;
    private boolean isWalkwayChecked;
    private boolean isSidewayChecked;
    private List<String> itemsRequested;
    private Date requestDate;

    private CalendarView requestDatePicker;
    private TextClock requestTimePicker;
    private String requestedDateTime;
    private String specialInstructions;
    private Image completedImage;
    private Image issueImage;
    private double price;

    // foreign key from customer class
    private String customerId;
    private String customerAddressId;

    // constructor
    public WorkOrder(String workOrderId, Date requestDate, String status, int squareFootage, List<String> itemsRequested, String customerId, String customerAddressId) {
        this.workOrderId = workOrderId;
        this.requestDate = requestDate;
        this.status = status;
        this.squareFootage = squareFootage;
        this.itemsRequested = itemsRequested;
        this.customerId = customerId;
        this.customerAddressId = customerAddressId;
    }


    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
    }

    public boolean isDerivewayChecked() {
        return isDerivewayChecked;
    }

    public void setDerivewayChecked(boolean derivewayChecked) {
        isDerivewayChecked = derivewayChecked;
    }

    public boolean isWalkwayChecked() {
        return isWalkwayChecked;
    }

    public void setWalkwayChecked(boolean walkwayChecked) {
        isWalkwayChecked = walkwayChecked;
    }

    public boolean isSidewayChecked() {
        return isSidewayChecked;
    }

    public void setSidewayChecked(boolean sidewayChecked) {
        isSidewayChecked = sidewayChecked;
    }

    public List<String> getItemsRequested() {
        return itemsRequested;
    }

    public void setItemsRequested(List<String> itemsRequested) {
        this.itemsRequested = itemsRequested;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public CalendarView getRequestDatePicker() {
        return requestDatePicker;
    }

    public void setRequestDatePicker(CalendarView requestDatePicker) {
        this.requestDatePicker = requestDatePicker;
    }

    public TextClock getRequestTimePicker() {
        return requestTimePicker;
    }

    public void setRequestTimePicker(TextClock requestTimePicker) {
        this.requestTimePicker = requestTimePicker;
    }

    public String getRequestedDateTime() {
        return requestedDateTime;
    }

    public void setRequestedDateTime(String requestedDateTime) {
        this.requestedDateTime = requestedDateTime;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Image getCompletedImage() {
        return completedImage;
    }

    public void setCompletedImage(Image completedImage) {
        this.completedImage = completedImage;
    }

    public Image getIssueImage() {
        return issueImage;
    }

    public void setIssueImage(Image issueImage) {
        this.issueImage = issueImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(String customerAddressId) {
        this.customerAddressId = customerAddressId;
    }




}
