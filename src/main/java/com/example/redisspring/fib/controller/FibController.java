package com.example.redisspring.fib.controller;

import com.example.redisspring.fib.service.FibService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fib")
@RequiredArgsConstructor
public class FibController {

    private final FibService fibService;

    @GetMapping("/{index}/")
    public Mono<Integer> get(@PathVariable int index) {
        return Mono.fromSupplier(() -> fibService.getFib(index));
    }

    @GetMapping("/{index}/clear")
    public Mono<Void> clear(@PathVariable int index) {
        return Mono.fromRunnable(() -> fibService.clearCache(index));
    }
}
