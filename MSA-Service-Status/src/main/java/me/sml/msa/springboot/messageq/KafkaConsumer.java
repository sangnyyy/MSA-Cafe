package me.sml.msa.springboot.messageq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sml.msa.domain.model.OrderStatus;
import me.sml.msa.domain.repository.OrderStatusRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final OrderStatusRepository orderStatusRepository;

    @KafkaListener(topics = "kafka-test")
    public void processMessage(String kafkaMessage) {
        log.info("kafkaMessage : =====>" + kafkaMessage);
        orderStatusRepository.save(OrderStatus.builder()
                .orderHistory(kafkaMessage)
                .build()
        );
    }
}
