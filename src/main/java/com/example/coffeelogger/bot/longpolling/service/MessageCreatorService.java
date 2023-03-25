package com.example.coffeelogger.bot.longpolling.service;

import org.springframework.stereotype.Component;
import com.example.coffeelogger.bot.longpolling.persistance.entity.Coffee;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Component
public class MessageCreatorService {

    public String createStatsMessageText(List<Coffee> orderList) {
        var orderCount = orderList.size();
        var totalCoffeeSize = computeTotalSize(orderList);
        var totalPrise = computeTotalPrise(orderList);
        return formStatsMessageText(orderCount, totalCoffeeSize, totalPrise);
    }

    public String createStartMessageText() {
        return "Скопируйте шаблон и заполните значения в <>:" +
            "\n[CoffeeOrder]: CoffeeType: <>, CoffeeSize: <> мл, CoffeePrise: <>, CafeName: <>";
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private int computeTotalPrise(List<Coffee> orderList) {
        return orderList.stream()
            .mapToInt(Coffee::getPrice)
            .sum();
    }

    private String computeTotalSize(List<Coffee> orderList) {
        var sum = orderList.stream()
            .mapToDouble(Coffee::getSize)
            .sum();
        return getDecimalFormat().format(sum);
    }

    private DecimalFormat getDecimalFormat() {
        var df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        return df;
    }

    //todo add logic for convert milliliters to liters
    private String formStatsMessageText(int orderCount, String totalCoffeeSize, int totalPrise) {
        var messageTemplate = "Количество чашек кофе: %d\nКоличество выпитого кофе: %s милилитров\nПотраченная сумма: %d рублей";
        return String.format(
            messageTemplate,
            orderCount,
            totalCoffeeSize,
            totalPrise
        );
    }
}
