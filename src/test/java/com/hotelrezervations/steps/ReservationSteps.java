package com.hotelrezervations.steps;

import com.hotelrezervations.models.BookingResponse;
import com.hotelrezervations.services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;


public class ReservationSteps {

    ReservationService reservationService;
    String authKey;
    BookingResponse bookingResponse;

    @Given("The user creates a new reservation.")
    public void callStart() {
        reservationService = new ReservationService();
    }

    @Given("The user provides the information required for the reservation.")
    public void createAuth() {
        authKey = reservationService.generateToken();
    }

    @When("The user creates a hotel reservation.")
    public void createReservation() {
        bookingResponse = reservationService.createBooking();
    }

    @Then("The reservation has been created successfully.")
    public void reservationAssertions() {
        Assertions.assertEquals("Yunus Emre",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Can",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(1000,bookingResponse.getBooking().getTotalprice());
        Assertions.assertFalse(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("Wi-Fi",bookingResponse.getBooking().getAdditionalneeds());
    }

    @Then("The user cancels the created reservation.")
    public void cancelReservation() {
        reservationService.deleteReservation(authKey, bookingResponse.getBookingid());
    }
}
