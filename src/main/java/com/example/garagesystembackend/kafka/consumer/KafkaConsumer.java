package com.example.garagesystembackend.kafka.consumer;

import com.example.garagesystembackend.models.Appointment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "appointments", groupId = "garage-system")
    public void listen(Appointment appointment) {
        LOGGER.info(String.format("Consumed message -> %s", appointment.toString()));
    }



}
