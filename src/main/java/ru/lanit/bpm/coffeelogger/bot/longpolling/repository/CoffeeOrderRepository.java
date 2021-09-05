package ru.lanit.bpm.coffeelogger.bot.longpolling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity.Coffee;

public interface CoffeeOrderRepository extends JpaRepository<Coffee, String> {
}
