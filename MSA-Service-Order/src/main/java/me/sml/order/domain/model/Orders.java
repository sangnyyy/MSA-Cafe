package me.sml.order.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;

    private String coffeeName;

    private int coffeeCount;

    private String customerName;

    @Builder
    public Orders(String coffeeName, int coffeeCount, String customerName) {
        this.coffeeName = coffeeName;
        this.coffeeCount = coffeeCount;
        this.customerName = customerName;
    }
}
