package com.example.athletedirectory.controller;

import com.example.athletedirectory.model.Athlete;
import com.example.athletedirectory.model.Country;
import com.example.athletedirectory.service.CountryJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class CountryController {
    @Autowired
    private CountryJpaService countryJpaService;

    @GetMapping("/countries")
    public ArrayList<Country> getCountries() {
        return countryJpaService.getCountries();
    }

    @GetMapping("/countries/{countryId}")
    public Country getCountryById(@PathVariable("countryId") int countryId) {
        return countryJpaService.getCountryById(countryId);
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return countryJpaService.addCountry(country);
    }

    @PutMapping("/countries/{countryId}")
    public Country updateCountry(@PathVariable("countryId") int countryId, @RequestBody Country country) {
        return countryJpaService.updateCountry(countryId, country);
    }

    @DeleteMapping("/countries/{countryId}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("countryId") int countryId) {
        try {
            countryJpaService.deleteCountry(countryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
        }
    }

    @GetMapping("/countries/{countryId}/athletes")
    public List<Athlete> getCountryAthletes(@PathVariable("countryId") int countryId) {
        return countryJpaService.getCountryAthletes(countryId);
    }

}
