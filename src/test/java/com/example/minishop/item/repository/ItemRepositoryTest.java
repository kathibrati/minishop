package com.example.minishop.item.repository;

import com.example.minishop.item.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;


    @BeforeEach
    void setUp() {
        List<Item> itemList = List.of(
                Item.builder()
                        .description("Tolle Tüten")
                        .name("Luwi Wittong")
                        .price(999.99)
                        .build(),
                Item.builder()
                        .description("Tolle Tassen")
                        .name("Teetasse")
                        .price(12.34)
                        .build(),
                Item.builder()
                        .description("Coole Hüte")
                        .name("Keppi Cap")
                        .price(16.99)
                        .build()
        );
        itemRepository.saveAll(itemList);
    }

    @Test
    void findAllByOrderByPrice() {
        List<Item> sortedByPriceItemList = itemRepository.findAllByOrderByPrice();
        assertThat(sortedByPriceItemList.get(0).getPrice()).isEqualTo(12.34);
        assertThat(sortedByPriceItemList.get(1).getPrice()).isEqualTo(16.99);
        assertThat(sortedByPriceItemList.get(2).getPrice()).isEqualTo(999.99);
    }

    @Test
    void findAllPageable() {
        Page<Item> itemPage2 = itemRepository.findAll(PageRequest.of(1, 1));
        List<Item> items = itemPage2.stream().toList();

        assertThat(items).hasSize(1);
        assertThat(items.get(0).getId()).isEqualTo(2L);

        assertThat(itemPage2.stream().toList()).containsExactly(itemRepository.findById(2L).orElseThrow());
    }
}