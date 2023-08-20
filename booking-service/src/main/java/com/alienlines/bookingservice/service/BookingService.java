package com.alienlines.bookingservice.service;

import com.alienlines.bookingservice.dto.BookingRequestDTO;
import com.alienlines.bookingservice.dto.PassengerRequestDTO;
import com.alienlines.bookingservice.model.Booking;
import com.alienlines.bookingservice.model.Passenger;
import com.alienlines.bookingservice.repository.BookingRepository;
import com.alienlines.bookingservice.repository.PassengerRepository;
import com.alienlines.bookingservice.util.BookingNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + bookingId + " not found"));
    }

    @Transactional
    public void deleteBookingsByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);

        for (Booking booking : bookings) {
            List<Passenger> passengers = booking.getPassengers();
            passengerRepository.deleteAll(passengers);
            bookingRepository.delete(booking);
        }
    }

    public void deleteBookingById(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new BookingNotFoundException("Booking with ID " + bookingId + " not found");
        }
        bookingRepository.deleteById(bookingId);
    }

    public List<Passenger> getAllPassengersByBookingId(Long bookingId) {
        Booking booking = getBookingById(bookingId);
        return booking.getPassengers();
    }

    public void createBooking(BookingRequestDTO bookingRequestDTO) {
        Booking booking = new Booking();
        booking.setUserId(bookingRequestDTO.getUser_id());
        booking.setShuttle_name(bookingRequestDTO.getShuttle_name());
        booking.setShuttle_type(bookingRequestDTO.getShuttle_type());
        booking.setNo_passengers(bookingRequestDTO.getNo_passengers());
        booking.set_from(bookingRequestDTO.get_from());
        booking.set_to(bookingRequestDTO.get_to());
        booking.setDep_date(bookingRequestDTO.getDep_date());
        booking.setReturn_date(bookingRequestDTO.getReturn_date());
        booking.setJourney_rate(bookingRequestDTO.getJourney_rate());
        booking.setPrice_extrabaggage(bookingRequestDTO.getPrice_extrabaggage());
        booking.setSeats(bookingRequestDTO.getSeats());
        booking.setMeals(bookingRequestDTO.getMeals());
        booking.setAssistance(bookingRequestDTO.getAssistance());
        booking.setTotal_price(bookingRequestDTO.getTotal_price());

        Booking bookingDb = bookingRepository.save(booking);

        List<Passenger> passengers = bookingRequestDTO.getPassengers().stream()
                .map(passengerRequestDTO -> createPassenger(passengerRequestDTO, bookingDb.getId()))
                .collect(Collectors.toList());
        booking.setPassengers(passengers);

        bookingRepository.save(booking);
    }

    private Passenger createPassenger(PassengerRequestDTO passengerRequestDTO, Long bookingId) {
        Passenger passenger = new Passenger();
        passenger.setName(passengerRequestDTO.getName());
        passenger.setSurename(passengerRequestDTO.getSurename());
        passenger.setDob(passengerRequestDTO.getDob());
        passenger.setNationality(passengerRequestDTO.getNationality());
        passenger.setDoc_type(passengerRequestDTO.getDoc_type());
        passenger.setDoc_no(passengerRequestDTO.getDoc_no());
        passenger.setEmail(passengerRequestDTO.getEmail());
        passenger.setPhone_no(passengerRequestDTO.getPhone_no());

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + bookingId + " not found"));


        passenger.setBooking(booking);  // Associate the passenger with the booking
        return passengerRepository.save(passenger); // Save and return the passenger instance
    }

}
