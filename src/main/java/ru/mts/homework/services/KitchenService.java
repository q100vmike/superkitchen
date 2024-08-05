package ru.mts.homework.services;

import org.springframework.stereotype.Service;
import ru.mts.homework.entity.Order;
import ru.mts.homework.repository.KitchenRepository;

@Service
public class KitchenService {

    private KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public void save(Order order) {
        kitchenRepository.save(order);
    }

}
