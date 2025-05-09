package com.abror.rabbitMQ;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rabbit-mq")
@RequiredArgsConstructor
@Slf4j
public class RabbitMqController {
    private final RabbitMqService rabbitMqService;

    @PostMapping
    public ResponseEntity<?> putMessage(@RequestBody String message){
        log.info(message);
        rabbitMqService.rabbitMqPublisher(message);
        return ResponseEntity.ok("Message is put on QUEUE");
    }
}
