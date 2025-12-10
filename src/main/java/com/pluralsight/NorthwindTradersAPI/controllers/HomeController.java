package com.pluralsight.NorthwindTradersAPI.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    private static final String ID_PATH = "/{id}";

    @GetMapping
    public String index(@RequestParam(defaultValue="World") String country) {
        return "Hello " + country;
    }

    @GetMapping(ID_PATH)
    public String idPath(@PathVariable String id) {
        return "Hello " + id;
    }


}
