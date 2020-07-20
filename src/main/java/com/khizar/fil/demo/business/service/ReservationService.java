package com.khizar.fil.demo.business.service;

import com.khizar.fil.demo.business.domain.RoomReservation;
import com.khizar.fil.demo.data.entity.Guest;
import com.khizar.fil.demo.data.entity.Reservation;
import com.khizar.fil.demo.data.entity.Room;
import com.khizar.fil.demo.data.repository.GuestRepository;
import com.khizar.fil.demo.data.repository.ReservationRepository;
import com.khizar.fil.demo.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    // Define as a bean
    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getReservationId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long id: roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(id));
        }
        return roomReservations;
    }

    public List<com.khizar.fil.demo.business.domain.Guest> getGuestListSortedByLastName(){
        List<com.khizar.fil.demo.business.domain.Guest> guestList = new ArrayList<>();
        Iterable<com.khizar.fil.demo.data.entity.Guest> guests = this.guestRepository.findAll();
        Map<Long, com.khizar.fil.demo.business.domain.Guest> guestMap = new HashMap<>();
        guests.forEach(guest -> {
            com.khizar.fil.demo.business.domain.Guest guestModel = new com.khizar.fil.demo.business.domain.Guest();
            guestModel.setGuestId(guest.getGuestId());
            guestModel.setFirstName(guest.getFirstName());
            guestModel.setLastName(guest.getLastName());
            guestModel.setEmailAddress(guest.getEmailAddress());
            guestMap.put(guest.getGuestId(), guestModel);
        });
        // Adding inside the list
        for(long id: guestMap.keySet()){
            guestList.add(guestMap.get(id));
        }
        Collections.sort(guestList, (a, b) -> a.getLastName().compareToIgnoreCase(b.getLastName()));
        return guestList;
    }
}
