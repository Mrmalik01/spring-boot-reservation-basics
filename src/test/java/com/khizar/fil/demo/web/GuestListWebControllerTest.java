package com.khizar.fil.demo.web;

import com.khizar.fil.demo.business.domain.Guest;
import com.khizar.fil.demo.business.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GuestListWebController.class)
public class GuestListWebControllerTest {

    @MockBean
    private ReservationService reservationService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getGuestList() throws Exception {
        Guest guest = new Guest();
        guest.setGuestId(1);
        guest.setFirstName("Khizar");
        guest.setLastName("Malik");
        guest.setEmailAddress("kmalik1122@gmail.com");
        List<Guest> guests = new ArrayList<>();
        guests.add(guest);
        given(this.reservationService.getGuestListSortedByLastName()).willReturn(guests);

        this.mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Khizar")));

    }
}
