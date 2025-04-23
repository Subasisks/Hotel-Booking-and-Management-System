package com.demo.hotel.repository;

import com.demo.hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
}
