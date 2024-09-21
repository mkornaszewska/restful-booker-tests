package com.kornaszewska.tests.booking;

import com.kornaszewska.requests.booking.UpdateBookingRequest;
import com.mkornaszewska.dto.request.createBooking.CreateBookingBookingDatesDto;
import com.mkornaszewska.dto.request.createBooking.CreateBookingRequestDto;
import com.mkornaszewska.helpers.AdditionalNeedsHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;

import static com.kornaszewska.tests.booking.CreateBookingTest.createCustomer;

class UpdateBookingTest {
    String bookingId = CreateBookingTest.createBooking(true);
    static Random random = new Random();
    static AdditionalNeedsHelper additionalNeedsHelper = new AdditionalNeedsHelper();

    @Test
    void updateBookingTest(){
        updateBooking(bookingId);
    }

    public static void updateBooking(String bookingId){

        CreateBookingRequestDto bookingDto = new CreateBookingRequestDto();
        CreateBookingBookingDatesDto bookingDates = new CreateBookingBookingDatesDto();

        bookingDates.setCheckIn(String.valueOf(LocalDate.now().minusDays(1)));
        bookingDates.setCheckOut(String.valueOf(LocalDate.now().plusDays(7)));
        bookingDto.setFirstName(createCustomer().getFirstName());
        bookingDto.setLastName(createCustomer().getLastName());
        bookingDto.setDepositPaid(random.nextBoolean());
        bookingDto.setTotalPrice(new BigDecimal(BigInteger.valueOf(Random().nextInt(100000)), 2));
        bookingDto.setBookingDates(bookingDates);
        bookingDto.setAdditionalNeeds(additionalNeedsHelper.getAdditionalNeed());

        final Response response = UpdateBookingRequest.updateBookingRequest(bookingDto, bookingId);

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
