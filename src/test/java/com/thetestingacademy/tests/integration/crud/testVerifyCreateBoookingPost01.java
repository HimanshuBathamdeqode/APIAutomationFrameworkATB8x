package com.thetestingacademy.tests.integration.crud;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.BookingResponse;
import com.thetestingacademy.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;





public class testVerifyCreateBoookingPost01 extends BaseTest {


    @Owner("Himanshu")    // owner of test case
    @Link(name = "Link to TC", url = "https://linear.app/txt2create/issue/TXT-287/audio-voice-over-new-page")
    @Issue("Jira_ticket0023")   // we can give ticket name associated with it.
    @Description  ("Verify that the post request is working")
    @Test
    public void testVerifyCreateBoookingPost01(){

        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);    // first we are setting up the base path

        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();  //To create booking we need to give payload

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        // Three ways to validate
        // 1: Default Rest assured specifications

        validatableResponse.body("booking.firstname", Matchers.equalTo("James"));
        // De serialization converting string to booking response class.
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        //Then verify using AssertJ

        //2: AssertJ   -- We use this by the way.

     //   assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"James");   --Generic form. We can either use this or below one.

        assertThat(bookingResponse.getBooking()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("James");

        //3:TestNG Assertions
      assertActions.verifyStatusCode(response,200);
      assertActions.verifyResponseBody(bookingResponse.getBooking().getFirstname(),"James","Verify the First Name");




    }
}
