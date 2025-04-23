package com.demo.hotel.service.interfac;

import com.demo.hotel.entity.Booking;
import com.demo.hotel.model.Response;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}
