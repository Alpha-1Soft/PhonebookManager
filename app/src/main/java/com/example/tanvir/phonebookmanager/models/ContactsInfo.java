package com.example.tanvir.phonebookmanager.models;

public class ContactsInfo {

    private int id;
    private String contactName;
    private String contactNumber;
    private String contactEmail;
    private String contactDescription;
    private String contactRating;
    private int ratingImage;

    public ContactsInfo(String contactName,int ratingImage) {
        this.contactName = contactName;
        this.ratingImage = ratingImage;
    }


    public ContactsInfo(int id,String contactName,int ratingImage) {
        this.id = id;
        this.contactName = contactName;
        this.ratingImage = ratingImage;
    }

    public ContactsInfo(int id, String contactName, String contactNumber, String contactEmail, String contactDescription, String contactRating,int ratingImage) {
        this.id = id;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.contactDescription = contactDescription;
        this.contactRating = contactRating;
        this.ratingImage = ratingImage;
    }

    public ContactsInfo(int id, String contactName, String contactNumber, String contactEmail, String contactDescription, String contactRating) {
        this.id = id;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.contactDescription = contactDescription;
        this.contactRating = contactRating;
    }
    public ContactsInfo(String contactName, String contactNumber, String contactEmail, String contactDescription, String contactRating ) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.contactDescription = contactDescription;
        this.contactRating = contactRating;

    }

    public int getId() {
        return id;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactDescription() {
        return contactDescription;
    }
    public String getContactRating() {
        return contactRating;
    }

    public int getRatingImage() {
        return ratingImage;
    }
}
