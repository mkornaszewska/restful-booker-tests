package com.kornaszewska.tests.booking;

import com.github.javafaker.Faker;
import com.kornaszewska.requests.booking.CreateBookingRequest;
import com.mkornaszewska.dto.request.createBooking.CreateBookingBookingDatesDto;
import com.mkornaszewska.dto.request.createBooking.CreateBookingRequestDto;
import com.mkornaszewska.helpers.AdditionalNeedsHelper;
import com.mkornaszewska.helpers.CustomerHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;

class CreateBookingTest {
    static AdditionalNeedsHelper additionalNeedsHelper = new AdditionalNeedsHelper();

    @Test
    void createBookingFullBodyTest(){
        createBooking(true);
    }
    @Test
    @Disabled("Returns internal error, should return 400 Bad Request")
    void createBookingNotFullBodyTest(){
        createBooking(false);
    }

    public static CustomerHelper createCustomer(){
        Faker faker = new Faker();
        return new CustomerHelper(faker.name().firstName(), faker.name().lastName());
    }

    public static String createBooking(boolean isFullRequest){
        Random random = new Random();

        CreateBookingRequestDto bookingDto = new CreateBookingRequestDto();
        CreateBookingBookingDatesDto bookingDates = new CreateBookingBookingDatesDto();

        if (!isFullRequest) {
            bookingDto.setFirstName(createCustomer().getFirstName());
            bookingDto.setLastName(createCustomer().getLastName());
            bookingDto.setDepositPaid(random.nextBoolean());
            bookingDto.setTotalPrice(new BigDecimal(BigInteger.valueOf(new Random().nextInt(100000)), 2));
            bookingDto.setBookingDates(bookingDates);
            bookingDto.setAdditionalNeeds(additionalNeedsHelper.getAdditionalNeed());
        } else {
            bookingDates.setCheckIn(String.valueOf(LocalDate.now().minusDays(2)));
            bookingDates.setCheckOut(String.valueOf(LocalDate.now().plusDays(9)));

            bookingDto.setFirstName(createCustomer().getFirstName());
            bookingDto.setLastName(createCustomer().getLastName());
            bookingDto.setDepositPaid(random.nextBoolean());
            bookingDto.setTotalPrice(new BigDecimal(BigInteger.valueOf(new Random().nextInt(100000)), 2));
            bookingDto.setBookingDates(bookingDates);
            bookingDto.setAdditionalNeeds(additionalNeedsHelper.getAdditionalNeed());
        }

        final Response response = CreateBookingRequest.createBookingRequest(bookingDto);

        JsonPath jsonData = response.jsonPath();

        Assertions.assertThat(response.getStatusCode())
                .as("Check if the status code is 200")
                .isEqualTo(200);

        Assertions.assertThat(response.getContentType())
                .as("Check if Content-Type header is application/json")
                .isEqualTo("application/json; charset=utf-8");

        String bookingId = jsonData.getString("bookingid");
        return bookingId;
    }
}
