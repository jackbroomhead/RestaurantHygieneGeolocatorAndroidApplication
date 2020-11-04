package com.example.androidgeolocator;

public class Establishment {
    String BusinessName, PostCode, RatingValue, AddressLine1, AddressLine2;

    public Establishment(String BusinessName, String PostCode, String RatingValue, String AddressLine1, String AddressLine2) {
        this.BusinessName = BusinessName;
        this.PostCode = PostCode;
        this.RatingValue = RatingValue;
        this.AddressLine1 = AddressLine1;
        this.AddressLine2 = AddressLine2;
    }

    public String BusinessName() {
        return BusinessName;
    }
    public String PostCode() {
        return PostCode;
    }
    public String RatingValue(){
        return RatingValue;
    }
    public String AddressLine1(){return AddressLine1;}
    public String AddressLine2(){
        return AddressLine2;
    }

}
