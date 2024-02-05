package com.example.garagesystembackend.kafka.consumer;

import com.example.garagesystembackend.DTO.responses.AppointmentStatusResponseDTO;
import com.example.garagesystembackend.models.Appointment;
import com.example.garagesystembackend.services.AppointmentService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @KafkaListener(topics = {"appointments", "appointment-status"}, groupId = "garage-system")
    public void listen(ConsumerRecord<String, Object> record) {
        try {
            Object message = record.value();

            if (message instanceof Appointment) {
                LOGGER.info("Consumed appointment -> {}", message);
            } else if (message instanceof AppointmentStatusResponseDTO) {
                appointmentService.updateAppointmentStatus((AppointmentStatusResponseDTO) message);
                LOGGER.info("Consumed appointment status -> {}", message);
                webSocketMessagingTemplate.convertAndSend("/topic/appointment-status", message);
            } else {
                LOGGER.warn("Unsupported message type: {}", message.getClass());
            }
        } catch (Exception e) {
            LOGGER.error("Error processing message", e);
        }
    }

}
