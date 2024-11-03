package com.comp313sec401.group4.shovelhero;

public class WorkOrderModel {
    private int workorderId;
    private String address;
    private String area;
    private String instructions;
    private int price;
    private int propertyId;
    private String requestdate;
    private String requestuser;
    private String specificdate;
    private int squarefoot;
    private String status;
    private String urgency;
    private int user_id;

    // Default constructor required for Firebase
    public WorkOrderModel() {}

    // Constructor with parameters
    public WorkOrderModel(int workorderId, String address, String area, String instructions,
                          int price, int propertyId, String requestdate, String requestuser,
                          String specificdate, int squarefoot, String status, String urgency, int user_id) {
        this.workorderId = workorderId;
        this.address = address;
        this.area = area;
        this.instructions = instructions;
        this.price = price;
        this.propertyId = propertyId;
        this.requestdate = requestdate;
        this.requestuser = requestuser;
        this.specificdate = specificdate;
        this.squarefoot = squarefoot;
        this.status = status;
        this.urgency = urgency;
        this.user_id = user_id;
    }

    // Getters and setters for each field
    public int getWorkorderId() { return workorderId; }
    public void setWorkorderId(int workorderId) { this.workorderId = workorderId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getPropertyId() { return propertyId; }
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }

    public String getRequestdate() { return requestdate; }
    public void setRequestdate(String requestdate) { this.requestdate = requestdate; }

    public String getRequestuser() { return requestuser; }
    public void setRequestuser(String requestuser) { this.requestuser = requestuser; }

    public String getSpecificdate() { return specificdate; }
    public void setSpecificdate(String specificdate) { this.specificdate = specificdate; }

    public int getSquarefoot() { return squarefoot; }
    public void setSquarefoot(int squarefoot) { this.squarefoot = squarefoot; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }

    public int getUser_id() { return user_id; }
    public void setUser_id(int user_id) { this.user_id = user_id; }
}
