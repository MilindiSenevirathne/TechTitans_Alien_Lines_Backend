package com.alienlines.bookingservice.controller;

import com.alienlines.bookingservice.dto.BookingRequestDTO;
import com.alienlines.bookingservice.model.Booking;
import com.alienlines.bookingservice.model.Passenger;
import com.alienlines.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    //Get all bookings by user Id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }

    //Get All bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    //Get booking by booking_id
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }

    //delete bookings by user_id
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteBookingsByUserId(@PathVariable Long userId) {
        bookingService.deleteBookingsByUserId(userId);
        return ResponseEntity.ok("Bookings for user with ID " + userId + " deleted successfully.");
    }

    //delete booking by booking_id
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBookingById(@PathVariable Long bookingId) {
        bookingService.deleteBookingById(bookingId);
        return ResponseEntity.ok("Booking with ID " + bookingId + " deleted successfully.");
    }

    //Get all passengers by booking_id
    @GetMapping("/{bookingId}/passengers")
    public ResponseEntity<List<Passenger>> getAllPassengersByBookingId(@PathVariable Long bookingId) {
        List<Passenger> passengers = bookingService.getAllPassengersByBookingId(bookingId);
        return ResponseEntity.ok(passengers);
    }



    //Submit Booking
    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity.ok("Booking created successfully.");
    }




}
