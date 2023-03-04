package com.example.coffeelogger.bot.longpolling.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coffeelogger.bot.longpolling.persistance.entity.Coffee;

import java.time.LocalDateTime;
import java.util.List;

public interface CoffeeOrderRepository extends JpaRepository<Coffee, String> {
    List<Coffee> findCoffeesByUserIdAndCreateDateTimeAfter(Long userId, LocalDateTime createDateTime);
    Coffee findDistinctFirstByUserId(Long userId);
}
