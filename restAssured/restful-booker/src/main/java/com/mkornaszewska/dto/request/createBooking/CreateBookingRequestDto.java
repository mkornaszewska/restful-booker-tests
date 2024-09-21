package com.mkornaszewska.dto.request.createBooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBookingRequestDto {
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("totalprice")
    private BigDecimal totalPrice;
    @JsonProperty("depositpaid")
    private boolean depositPaid;
    @JsonProperty("bookingdates")
    private CreateBookingBookingDatesDto bookingDates;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;
}
