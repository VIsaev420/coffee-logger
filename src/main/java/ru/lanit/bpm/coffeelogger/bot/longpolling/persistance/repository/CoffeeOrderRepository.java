package ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity.Coffee;

import java.time.LocalDateTime;
import java.util.List;

public interface CoffeeOrderRepository extends JpaRepository<Coffee, String> {
    List<Coffee> findCoffeesByUserIdAndCreateDateTimeAfter(Long userId, LocalDateTime createDateTime);
}
