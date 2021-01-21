package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {
    @Autowired
    private ItemRepository itemRepository;

    public void create() {
        Item item = new Item();
        item.setName("macbook");
        item.setPrice(1500000);
        item.setContent("macbook 16 inch");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
    }

    @Test
    public void read() {
        Long id = 2L;
        Optional<Item> item = itemRepository.findById(id);
        Assertions.assertTrue((item.isPresent()));
    }

    public void update() {

    }
    public void delete() {

    }
}
