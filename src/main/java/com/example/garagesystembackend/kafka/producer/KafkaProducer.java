package com.example.garagesystembackend.kafka.producer;

import com.example.garagesystembackend.DTO.requests.BookAppointmentRequestDTO;
import com.example.garagesystembackend.models.Appointment;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Appointment> kafkaTemplate;

    public void sendMessage(String topic,Appointment appointment) {
        LOGGER.info(String.format("Producing message -> %s", appointment.toString()));
        Message<Appointment> message = MessageBuilder
                .withPayload(appointment)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);
        System.out.println(message);
    }




}
