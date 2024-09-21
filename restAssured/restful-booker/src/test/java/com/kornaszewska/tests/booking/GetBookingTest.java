package com.kornaszewska.tests.booking;

import com.kornaszewska.requests.booking.GetBookingRequest;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GetBookingTest {

    @Test
    void getBookingTest(){
        String bookingId = CreateBookingTest.createBooking(true);
        final Response response = GetBookingRequest.getBookingRequest(bookingId);

        Assertions.assertThat(response.getStatusCode())
                .as("Check if the status code is 200")
                .isEqualTo(200);

        Assertions.assertThat(response.getContentType())
                .as("Check if Content-Type header is application/json")
                .isEqualTo("application/json; charset=utf-8");

        Assertions.assertThat(response.jsonPath().getString("lastname"))
                .as("Check if firstname property is not empty")
                .isNotEmpty();

    }
}
