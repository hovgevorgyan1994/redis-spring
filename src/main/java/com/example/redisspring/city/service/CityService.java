package com.example.redisspring.city.service;

import static java.util.stream.Collectors.toMap;

import java.util.function.Function;

import com.example.redisspring.city.client.CityClient;
import com.example.redisspring.city.dto.City;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CityService {

    private final RMapReactive<String, City> cityMap;
    private final CityClient cityClient;

    public CityService(RedissonReactiveClient reactiveClient, CityClient cityClient) {
        this.cityMap = reactiveClient.getMap("city", new TypedJsonJacksonCodec(String.class, City.class));
        this.cityClient = cityClient;
    }

    /**
     * get from cache \\ if empty get from db /source \\ then put it in the cache \\ then return
     */
    public Mono<City> getCity(String zip) {
        return cityMap.get(zip)
            .onErrorResume(ex -> cityClient.getCity(zip));
    }

    @Scheduled(fixedRate = 10000)
    public void updateCityInfo() {
        cityClient.getAll()
            .collectList()
            .map(l -> l.stream().collect(toMap(City::getZip, Function.identity())))
            .flatMap(cityMap::putAll)
            .subscribe();
    }
}

