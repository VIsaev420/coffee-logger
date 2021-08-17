package ru.lanit.bpm.coffeelogger.bot.longpolling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity.CoffeeLog;

public interface LogRepository extends JpaRepository<CoffeeLog, String> {
}
