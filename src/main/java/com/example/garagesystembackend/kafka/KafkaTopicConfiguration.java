package com.example.garagesystembackend.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

//    @Bean
//    public NewTopic appointmentsTopic() {
//        return TopicBuilder.name("appointments").build();
//    }

    @Bean
    public NewTopic appointmentsTopic() {
        return TopicBuilder.name("appointments").build();
    }
}

