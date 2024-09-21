package com.mkornaszewska.dto.request.partialUpdateBooking;

import lombok.Data;

@Data
public class PartialUpdateBookingBookingDatesDto {
    //@JsonIgnore
    private String checkin;
    //@JsonIgnore
    private String checkout;
}
