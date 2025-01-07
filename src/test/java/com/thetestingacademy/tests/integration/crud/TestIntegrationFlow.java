package com.thetestingacademy.tests.integration.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static io.restassured.RestAssured.when;

public class TestIntegrationFlow extends BaseTest {

    // Creating A Booking , create a token
    // Get booking
    // Update the booking
    // Delete the booking

    @Test(groups ={ "integration","PO"}, priority = 1)
    @Owner("Promode")
    @Description("TC#INT1 - Step 1. Verify that the booking can be created")
    public void testCreateBooking(ITestContext iTestContext) {                        // 1.Post request


       requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
       response = RestAssured
               .given(requestSpecification)
               .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());  //"ITestContext" is used to share booking id to another testcase.

        
    }

    @Test(groups = "integration", priority = 2)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2 . Verify  the booking by ID")
    public void testVerifyBookingID(ITestContext iTestContext) {                 // 2. Get request

        Integer bookingid = (Integer)iTestContext.getAttribute("bookingid");

        // Get Req
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        //Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJson(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("James");


    }

    @Test(groups = "integration", priority = 3 )
    @Owner("Promode")
    @Description("TC#INT1 - Step 3 . Verify updated booking by ID")
    public void testUpdateBookingById(ITestContext iTestContext) {
        String token = getToken();
        iTestContext.setAttribute("token", token);
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatepayloadAsString()).put();

        validatableResponse = response.then().log().all();

        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJson(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Pramod");
        assertThat(booking.getLastname()).isEqualTo("Dutta");

         }

    @Test(groups = "integration", priority = 4 )
    @Owner("Promode")
    @Description("TC#INT1 - Step 4 . Delete the booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {

        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");



        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token",token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }





}