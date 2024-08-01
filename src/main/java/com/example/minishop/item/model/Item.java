package com.example.minishop.item.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Double price;

}
