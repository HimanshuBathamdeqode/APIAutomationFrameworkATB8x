package com.thetestingacademy.base;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest   {
    //Base test will contain the common code which we will run before the test case and after the test case.

    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp(){
      // Base URl and Content type JSON. Firstly we set base url.

        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

//        requestSpecification = RestAssured.given()
//                                          .baseUri(APIConstants.BASE_URL)
//                                          .contentType(ContentType.JSON)
//                                          .log().all();

        // or we can use request spec builder class also. Same thing
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();




    }

    public String getToken(){

        requestSpecification = RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);

        // Setting the payload
        String payload = payloadManager.setAuthPayload();

        // GET The Token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();

        // String Extraction
        String token = payloadManager.getTokenFromJSON(response.asString());

        return token;

    }


}



