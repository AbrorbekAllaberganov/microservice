package com.abror.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqService {
    private final RabbitMqProducer rabbitMqProducer;

    public void rabbitMqPublisher(String message){
        rabbitMqProducer.sendMessage(message);
    }
}
