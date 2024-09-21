package com.kornaszewska.tests.booking;

import com.kornaszewska.requests.booking.PartialUpdateBookingRequest;
import com.mkornaszewska.dto.request.partialUpdateBooking.PartialUpdateBookingBookingDatesDto;
import com.mkornaszewska.dto.request.partialUpdateBooking.PartialUpdateBookingRequestDto;
import com.mkornaszewska.helpers.AdditionalNeedsHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;


class PartialUpdateBookingTest {
    String bookingId = CreateBookingTest.createBooking(true);
    static Random random = new Random();
    static AdditionalNeedsHelper additionalNeedsHelper = new AdditionalNeedsHelper();

    @Test
    void partialUpdateBookingTest(){
        partialUpdateBooking(bookingId);
    }

    public static void partialUpdateBooking(String bookingId){

        PartialUpdateBookingRequestDto bookingDto = new PartialUpdateBookingRequestDto();
        PartialUpdateBookingBookingDatesDto bookingDates = new  PartialUpdateBookingBookingDatesDto();

        bookingDates.setCheckin(String.valueOf(LocalDate.now().minusDays(2)));
        bookingDates.setCheckout(String.valueOf(LocalDate.now().plusDays(8)));
        bookingDto.setDepositpaid(random.nextBoolean());
        bookingDto.setTotalprice(new BigDecimal(BigInteger.valueOf(Random().nextInt(100000)), 2));
        bookingDto.setBookingdates(bookingDates);
        bookingDto.setAdditionalneeds(additionalNeedsHelper.getAdditionalNeed());

        final Response response = PartialUpdateBookingRequest.partialUpdateBookingRequest(bookingDto, bookingId);

        JsonPath jsonData = response.jsonPath();

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
