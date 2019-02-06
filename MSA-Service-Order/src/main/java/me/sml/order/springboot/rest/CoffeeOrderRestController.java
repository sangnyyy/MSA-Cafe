package me.sml.order.springboot.rest;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.sml.order.domain.model.Orders;
import me.sml.order.domain.model.OrdersRequestDTO;
import me.sml.order.domain.model.OrdersResponseDTO;
import me.sml.order.domain.repository.OrdersRepository;
import me.sml.order.springboot.messageq.KafkaProducer;
import me.sml.order.springboot.service.OrdersService;
import me.sml.order.util.ReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class CoffeeOrderRestController {

    private final OrdersService ordersService;
    private final KafkaProducer kafkaProducer;
    private final OrdersRepository ordersRepository;

    @PostMapping("/coffee")
    public ResponseEntity<OrdersResponseDTO> orderCoffee(@RequestBody OrdersRequestDTO ordersRequestDTO){
        long total = ordersRepository.count();
        int orderNumber = (int) total + 1;
        Orders savedOrder = ordersService.save(ordersRequestDTO.toEntity(), orderNumber);
        kafkaProducer.send("kafka-test", ordersRequestDTO);
        OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO(savedOrder);
        ordersResponseDTO.setStat("success");
        ordersResponseDTO.setMessage("Order Coffee Complete");
        ordersResponseDTO.setCode(ReturnType.CREATED.getValue());
        return new ResponseEntity<>(ordersResponseDTO, HttpStatus.CREATED);
    }
}
