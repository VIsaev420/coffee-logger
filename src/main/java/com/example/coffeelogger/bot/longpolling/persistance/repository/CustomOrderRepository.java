package com.example.coffeelogger.bot.longpolling.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.coffeelogger.bot.longpolling.persistance.entity.Custom;

public interface CustomOrderRepository extends JpaRepository<Custom, String> {
}
