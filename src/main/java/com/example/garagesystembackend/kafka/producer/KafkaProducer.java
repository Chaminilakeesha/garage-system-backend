package com.example.garagesystembackend.kafka.producer;

import com.example.garagesystembackend.DTO.requests.BookAppointmentRequestDTO;
import com.example.garagesystembackend.DTO.responses.AppointmentStatusResponseDTO;
import com.example.garagesystembackend.models.Appointment;
import com.example.garagesystembackend.services.AppointmentService;
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
    private KafkaTemplate<String, Object> kafkaTemplate;

    private <T> void sendMessage(String topic, T payload) {
        LOGGER.info("Producing message -> {}", payload.toString());
        Message<T> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);
        System.out.println(message);
    }

    public void sendAppointment(String topic, Appointment appointment) {
        sendMessage(topic, appointment);
    }

    public void sendAppointmentStatus(String topic, AppointmentStatusResponseDTO appointmentStatusResponseDTO) {
        sendMessage(topic, appointmentStatusResponseDTO);
    }

}
