package com.thetestingacademy.tests.integration.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationFlow {

    // Creating A Booking , create a token
    // Get booking
    // Update the booking
    // Delete the booking

    @Test(groups = "integration", priority = 1)
    @Owner("Promode")
    @Description("TC#INT1 - Step 1. Verify that the booking can be created")
    public void testCreateBooking() {
        Assert.assertTrue(true);

    }

    @Test(groups = "integration", priority = 2)
    @Owner("Promode")
    @Description("TC#INT1 - Step 2 . Verify  the booking by ID")
    public void testVerifyBookingID() {
        Assert.assertTrue(true);

    }

    @Test(groups = "integration", priority = 3 )
    @Owner("Promode")
    @Description("TC#INT1 - Step 3 . Verify updated booking by ID")
    public void testUpdateBookingById() {
        Assert.assertTrue(true);

    }
    @Test(groups = "integration", priority = 4 )
    @Owner("Promode")
    @Description("TC#INT1 - Step 4 . Delete the booking by ID")
    public void testDeleteBookingById() {
        Assert.assertTrue(true);

    }





}