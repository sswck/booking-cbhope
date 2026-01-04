package org.hope.booking.controller;

import lombok.RequiredArgsConstructor;
import org.hope.booking.service.FacilityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final FacilityService facilityService;

    @GetMapping("/new")
    public String createBookingForm(Model model) {
        model.addAttribute("facilities", facilityService.findFacilities());

        return "booking/booking-form";
    }
}
