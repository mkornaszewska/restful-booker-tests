package com.kornaszewska.tests.booking;

import com.kornaszewska.requests.booking.DeleteBookingRequest;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DeleteBookingTest {


    @Test
    void deleteBookingTest(){
        String bookingId = CreateBookingTest.createBooking(true);

        final Response response = DeleteBookingRequest.deleteBookingRequest(bookingId);

        Assertions.assertThat(response.getStatusCode())
                .as("Check if the status code is 201")
                .isEqualTo(201);

        String responseBody = response.getBody().asString();
        Assertions.assertThat(responseBody)
                .as("Check if the response contains a string")
                .contains("Created");
    }
}
