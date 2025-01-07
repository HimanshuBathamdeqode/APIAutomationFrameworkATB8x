package com.thetestingacademy.modules;

import com.google.gson.Gson;
import com.thetestingacademy.pojos.*;
import com.google.gson.Gson;


public class PayloadManager {

    // convert the Java object to string.
    // GSON lib help us to do that.
    Gson gson;

    public String createPayloadBookingAsString() {

        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Brown");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("BreakFast");   //Till here we are creating payload then we will convert it into string

        System.out.println(booking);   // but booking is not string its object. So we need to convert this using GSON.
        Gson gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }     // Object to string

    //public String createpayloadBookingAsStringFromExcel(){}  --In future will not use hardcoded payload

    // String to object

    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }


    //public String createPayloadBookingAsStringFromExcel(){}   //Above one is hardcoded but in future we wil use this func.

    public Booking getResponseFromJson(String getResponse){        // whatever you are getting it will deserialize to json

        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }

    //getToken

    public  String setAuthPayload(){
        // Auth Object -> json String

        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the ->" + jsonPayloadString);
        return jsonPayloadString;


    }

    public String getTokenFromJSON(String tokenResponse){
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();
    }


    public String fullUpdatepayloadAsString(){
        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setDepositpaid(true);
        booking.setTotalprice(112);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }
}
