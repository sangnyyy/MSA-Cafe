package me.sml.order.springboot.service;

import lombok.RequiredArgsConstructor;
import me.sml.order.domain.model.Orders;
import me.sml.order.domain.repository.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Transactional
    public Orders save(Orders orders){
        return ordersRepository.save(orders);
    }
}
