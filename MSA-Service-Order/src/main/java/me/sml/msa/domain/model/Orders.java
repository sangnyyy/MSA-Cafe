package me.sml.msa.domain.model;

import lombok.*;

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

    @Setter
    private int orderNumber;

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
