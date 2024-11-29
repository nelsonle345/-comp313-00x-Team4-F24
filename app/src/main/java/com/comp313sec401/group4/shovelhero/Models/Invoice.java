package com.comp313sec401.group4.shovelhero.Models;

public class Invoice {
    private String invoiceId;
    private String jobId;
    private String jobDescription;
    private String paymentAmount;
    private String paymentStatus;

    public Invoice() {
        // Default constructor required for Firebase
    }

    public Invoice(String invoiceId, String jobId, String jobDescription, String paymentAmount, String paymentStatus) {
        this.invoiceId = invoiceId;
        this.jobId = jobId;
        this.jobDescription = jobDescription;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}

