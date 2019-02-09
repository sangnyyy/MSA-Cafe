package me.sml.msa.springboot.rest;

import lombok.RequiredArgsConstructor;
import me.sml.msa.domain.model.Orders;
import me.sml.msa.domain.model.OrdersRequestDTO;
import me.sml.msa.domain.model.OrdersResponseDTO;
import me.sml.msa.domain.repository.OrdersRepository;
import me.sml.msa.springboot.messageq.KafkaProducer;
import me.sml.msa.springboot.service.IMSAServiceMember;
import me.sml.msa.springboot.service.OrdersService;
import me.sml.msa.util.ReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoffeeOrderRestController {

    private final OrdersService ordersService;
    private final KafkaProducer kafkaProducer;
    private final OrdersRepository ordersRepository;
    private final IMSAServiceMember imsaServiceMember;

    @PostMapping("/coffeeOrder")
    public ResponseEntity<OrdersResponseDTO> orderCoffee(@RequestBody OrdersRequestDTO ordersRequestDTO){
        if(imsaServiceMember.cafeMember(ordersRequestDTO.getCustomerName())){
            System.out.println(ordersRequestDTO.getCustomerName() + " is a msa!!!");
        }
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
