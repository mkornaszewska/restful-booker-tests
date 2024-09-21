package com.mkornaszewska.dto.request.partialUpdateBooking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PartialUpdateBookingRequestDto {

    @JsonIgnore
    private String firstname;
    @JsonIgnore
    private String lastname;
    //@JsonIgnore
    private BigDecimal totalprice;
    //@JsonIgnore
    private boolean depositpaid;
    //@JsonIgnore
    private PartialUpdateBookingBookingDatesDto bookingdates;
    //@JsonIgnore
    private String additionalneeds;
}
