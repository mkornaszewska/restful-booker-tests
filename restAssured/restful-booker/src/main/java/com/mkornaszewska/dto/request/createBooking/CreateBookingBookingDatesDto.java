package com.mkornaszewska.dto.request.createBooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateBookingBookingDatesDto {
    @JsonProperty("checkin")
    private String checkIn;
    @JsonProperty("checkout")
    private String checkOut;
}
