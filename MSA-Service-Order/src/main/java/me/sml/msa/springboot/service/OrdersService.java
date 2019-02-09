package me.sml.msa.springboot.service;

import lombok.RequiredArgsConstructor;
import me.sml.msa.domain.model.Orders;
import me.sml.msa.domain.repository.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Transactional
    public Orders save(Orders orders, int orderNumber){
        orders.setOrderNumber(orderNumber);
        return ordersRepository.save(orders);
    }
}
