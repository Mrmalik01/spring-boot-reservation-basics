package com.khizar.fil.demo.web;

import com.khizar.fil.demo.business.domain.Guest;
import com.khizar.fil.demo.business.service.ReservationService;
import com.khizar.fil.demo.data.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/guests")
public class GuestListWebServiceController {
    private ReservationService reservationService;

    @Autowired
    public GuestListWebServiceController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Guest> getGuests(){
        return this.reservationService.getGuestListSortedByLastName();
    }
}
