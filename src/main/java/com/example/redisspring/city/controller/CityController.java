package com.example.redisspring.city.controller;

import com.example.redisspring.city.dto.City;
import com.example.redisspring.city.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("{zip}")
    public Mono<City> getCity(@PathVariable String zip) {
        return cityService.getCity(zip);
    }
}
