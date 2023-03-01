package com.example.redisspring.wheather.controller;

import com.example.redisspring.wheather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("wheather")
public class WheatherController {

    private final WeatherService weatherService;

    @GetMapping("/{zip}")
    public Mono<Integer> get(@PathVariable int zip) {
        return Mono.fromSupplier(() -> weatherService.getWeather(zip));
    }
}
