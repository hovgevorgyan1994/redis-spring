package com.example.redisspring.fib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FibService {

    @Cacheable(value = "math:fib", key = "#index")
    public int getFib(int index) {
        System.out.println("calculating fib for " + index);
        return fib(index);
    }

    @CacheEvict(value = "math:fib", key = "#index")
    public void clearCache(int index) {
        System.out.println("clearing hash key");
    }

    @Scheduled(fixedDelay = 10000)
    @CacheEvict(value = "math:fib", allEntries = true)
    public void clearCache() {
        System.out.println("clearing all the cache");
    }

    //intentional 2^N
    private int fib(int index) {
        if (index < 2) {
            return index;
        }
        return fib(index - 1) + fib(index - 2);
    }
}
