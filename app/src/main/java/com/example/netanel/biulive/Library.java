package com.example.netanel.biulive;

/**
 * Created by dell on 08/06/2016.
 */
public class Library {
    private String location;
    private String openingHours;
    private String phoneNumber;
    private String website;
    private String mailAddress;

    public String getLocation() {
        return this.location;
    }

    public String getOpeningHours() {
        return this.openingHours;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getMailAddress() {
        return this.mailAddress;
    }

    public Library(String location, String openingHours,
                   String phoneNumber, String website, String mailAddress) {
        this.location = location;
        this.openingHours = openingHours;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.mailAddress = mailAddress;

    }
}
