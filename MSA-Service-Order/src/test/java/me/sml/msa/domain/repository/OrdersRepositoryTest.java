package me.sml.msa.domain.repository;

import me.sml.msa.domain.model.Orders;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersRepositoryTest {

    @Autowired
    OrdersRepository ordersRepository;

    @After
    public void cleanUp() {
        ordersRepository.deleteAll();
    }

    @Test
    public void 주문을저장() {
        //given
        ordersRepository.save(Orders.builder()
                .coffeeCount(3)
                .coffeeName("아메리카노")
                .customerName("SML")
                .build());
        //when
        List<Orders> orders = ordersRepository.findAll();

        //then
        assertThat(orders.get(0).getCoffeeCount()).isEqualTo(3);
        assertThat(orders.get(0).getCoffeeName()).isEqualTo("아메리카노");
        assertThat(orders.get(0).getCustomerName()).isEqualTo("SML");
    }

}