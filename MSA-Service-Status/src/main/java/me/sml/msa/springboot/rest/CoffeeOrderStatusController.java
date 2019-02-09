package me.sml.msa.springboot.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import me.sml.msa.domain.model.OrderStatus;
import me.sml.msa.domain.model.OrderStatusResponseDTO;
import me.sml.msa.domain.repository.OrderStatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
public class CoffeeOrderStatusController {

    private final OrderStatusRepository orderStatusRepository;

    @HystrixCommand
    @GetMapping("/")
    public ResponseEntity<OrderStatusResponseDTO> coffeeOrderStatus(){
        List<OrderStatus> orderStatuses = orderStatusRepository.findAll();
        OrderStatusResponseDTO orderStatusResponseDTO = new OrderStatusResponseDTO();
        orderStatusResponseDTO.setData(orderStatuses);
        orderStatusResponseDTO.setMessage("Find All Status");
        orderStatusResponseDTO.setStat("Success");
        orderStatusResponseDTO.setCode(200);
        return new ResponseEntity<>(orderStatusResponseDTO,HttpStatus.OK);
    }
}
