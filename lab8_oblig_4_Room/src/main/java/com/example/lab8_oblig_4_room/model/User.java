package com.example.lab8_oblig_4_room.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String mUserId;

    @ColumnInfo(name = "name")
    public String mName;

    @ColumnInfo(name = "username")
    public String mUserName;

    @ColumnInfo(name = "email")
    public String mEmail;

//    @ColumnInfo(name = "address")
//    private Address mAddress;

    @ColumnInfo(name = "phone")
    public String mPhone;

    @ColumnInfo(name = "website")
    public String mWebsite;

//    @ColumnInfo(name = "company")
//    private Company mCompany;

//    public User(@NonNull String mUserId, String mName, String mUserName, String mEmail, Address mAddress, String mPhone, String mWebsite, Company mCompany) {
    public User(@NonNull String mUserId, String mName, String mUserName, String mEmail, String mPhone, String mWebsite) {
        this.mUserId = mUserId;
        this.mName = mName;
        this.mUserName = mUserName;
        this.mEmail = mEmail;
//        this.mAddress = mAddress;
        this.mPhone = mPhone;
        this.mWebsite = mWebsite;
//        this.mCompany = mCompany;
    }

    public static class Address {
        String street;
        String suite;
        String city;
        String zipCode;
//        @Embedded
//        Geo geo;

        public Address(String street, String suite, String city, String zipCode) {
            this.street = street;
            this.suite = suite;
            this.city = city;
            this.zipCode = zipCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

//    public class Geo {
//        double latitude;
//        double longitude;
//    }

    public static class Company {
        String name;
        String catchPhrase;
        String bs;

        public Company(String name, String catchPhrase, String bs) {
            this.name = name;
            this.catchPhrase = catchPhrase;
            this.bs = bs;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCatchPhrase() { return catchPhrase; }
        public void setCatchPhrase(String catchPhrase) { this.catchPhrase = catchPhrase; }
        public String getBs() { return bs; }
        public void setBs(String bs) { this.bs = bs; }
    }

    @NonNull
    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(@NonNull String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }
}