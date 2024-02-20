package com.hotelrezervations.services;

import com.hotelrezervations.models.Auth;
import com.hotelrezervations.models.Booking;
import com.hotelrezervations.models.BookingDates;
import com.hotelrezervations.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest{
    public String generateToken() {
        Auth authBody = new Auth("admin","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");
    }

    public BookingResponse createBooking() {
        BookingDates bookingdates = new BookingDates("2024-04-01", "2024-05-01");
        Booking booking = new Booking("Yunus Emre", "Can", 1000, false,
                bookingdates, "Wi-Fi");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");

        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }

    public void deleteReservation(String token, int bookingid) {
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingid);

        response
                .then()
                .statusCode(201);
    }
}
