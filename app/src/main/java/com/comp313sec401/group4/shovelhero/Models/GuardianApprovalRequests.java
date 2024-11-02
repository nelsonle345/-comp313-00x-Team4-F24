package com.comp313sec401.group4.shovelhero.Models;

import com.google.firebase.database.PropertyName;

public class GuardianApprovalRequests {

    private int guardianId;
    private int propertyId;
    private int workorderId;
    private int youthId;
    private String status;

    public GuardianApprovalRequests () {}

    public GuardianApprovalRequests(int guardianId, int propertyId, int workorderId, int youthId, String status) {
        this.guardianId = guardianId;
        this.propertyId = propertyId;
        this.workorderId = workorderId;
        this.youthId = youthId;
        this.status = status;
    }

    @PropertyName("guardianId")
    public int getGuardianId() { return guardianId; }
    @PropertyName("guardianId")
    public void setGuardianId(int guardianId) { this.guardianId = guardianId; }
    @PropertyName("propertyId")
    public int getPropertyId() { return propertyId; }
    @PropertyName("propertyId")
    public void setPropertyId(int propertyId) { this.propertyId = propertyId; }
    @PropertyName("workorderId")
    public int getWorkorderId() { return workorderId; }
    @PropertyName("workorderId")
    public void setWorkorderId(int workorderId) { this.workorderId = workorderId; }
    @PropertyName("youthId")
    public int getYouthId() { return youthId; }
    @PropertyName("youthId")
    public void setYouthId(int youthId) { this.youthId = youthId; }
    @PropertyName("status")
    public String getStatus() { return status; }
    @PropertyName("status")
    public void setStatus(String status) { this.status = status; }
}
