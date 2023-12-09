package io.swagger.service;

import io.swagger.repository.ConsumptionRepository;
import io.swagger.model.Consumption;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Profile("consumer")
public class KafkaConsumer {
    private final ConsumptionRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public KafkaConsumer(ConsumptionRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "myTopic")
    public void listen(String message) {
        System.out.println(message);
        // Преобразование сообщения обратно в ItemObj и сохранение его в базе данных
        Consumption consumption = convertMessageToItemObj(message);
        repository.save(consumption);
    }

    private Consumption convertMessageToItemObj(String message) {
        try {
            return objectMapper.readValue(message, Consumption.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при преобразовании сообщения в запись о потреблении", e);
        }
    }
}
