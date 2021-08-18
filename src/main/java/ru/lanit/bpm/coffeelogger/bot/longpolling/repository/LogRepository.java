package ru.lanit.bpm.coffeelogger.bot.longpolling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity.Log;

public interface LogRepository extends JpaRepository<Log, String> {
}
