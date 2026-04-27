package com.pluralsight;

public class Transaction {
    private String date;
    private String time;
    private String itemDescription;
    private String vendorName;
    private double amount;

    public Transaction() {

    }

    public Transaction(String date, String time, String itemDescription, String vendorName, double amount) {
        this.date = date;
        this.time = time;
        this.itemDescription = itemDescription;
        this.vendorName = vendorName;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
