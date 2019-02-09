package me.sml.msa.springboot.messageq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.sml.msa.domain.model.OrdersRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public OrdersRequestDTO send(String kafkaTopic, OrdersRequestDTO ordersRequestDTO){
        ObjectMapper mapper = new ObjectMapper();
        String jsonToString = "";
        try {
            jsonToString = mapper.writeValueAsString(ordersRequestDTO);
        } catch (JsonProcessingException e) {
            log.error("[JsonToString][Exception] : {}", e);
        }

        kafkaTemplate.send(kafkaTopic, jsonToString);

        log.info("KafkaProducer send data from MSA-Service-Order : {}", ordersRequestDTO);

        return ordersRequestDTO;
    }
}
