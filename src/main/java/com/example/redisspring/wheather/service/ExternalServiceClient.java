package com.example.redisspring.wheather.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class ExternalServiceClient {

    @CachePut(value = "wheather", key = "#zip")
    public int getInfo(int zip) {
        return ThreadLocalRandom.current().nextInt(60, 100);
    }
}
