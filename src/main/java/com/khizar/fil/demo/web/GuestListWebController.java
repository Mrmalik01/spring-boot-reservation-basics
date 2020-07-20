package com.khizar.fil.demo.web;

import com.khizar.fil.demo.business.domain.Guest;
import com.khizar.fil.demo.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestListWebController {
    private final ReservationService reservationService;

    @Autowired
    public GuestListWebController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getGuests(Model model){
        List<Guest> guests = this.reservationService.getGuestListSortedByLastName();
        model.addAttribute("guests", guests);
        return "guests";
    }

}
