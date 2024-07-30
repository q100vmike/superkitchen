package ru.mts.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.homework.entity.Order;

@Repository
public interface KitchenRepository extends JpaRepository<Order, Long> {
}
