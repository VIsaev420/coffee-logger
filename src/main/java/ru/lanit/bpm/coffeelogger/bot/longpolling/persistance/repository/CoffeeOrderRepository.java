package ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity.Coffee;

public interface CoffeeOrderRepository extends JpaRepository<Coffee, String> {
}
