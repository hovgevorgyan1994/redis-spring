package com.example.redisspring.wheather.service;

import java.util.stream.IntStream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final ExternalServiceClient client;

    @Cacheable("wheather")
    public int getWeather(int zip) {
        return 0;
    }

    @Scheduled(fixedRate = 10000)
    public void update() {
        log.info("Updating wheather info");
        IntStream.rangeClosed(1, 5)
            .forEach(client::getInfo);
    }
}
