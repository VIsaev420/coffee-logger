package ru.lanit.bpm.coffeelogger.bot.longpolling.service;

import org.springframework.stereotype.Component;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.entity.Coffee;

import java.util.List;

@Component
public class MessageCreatorService {

    public String createStatsMessageText(List<Coffee> orderList) {
        var orderCount = orderList.size();
        var totalCoffeeSize = orderList.stream()
                .mapToDouble(Coffee::getSize)
                .sum();
        var totalPrise = orderList.stream()
                .mapToInt(Coffee::getPrice)
                .sum();
        return formStatsMessageText(orderCount, totalCoffeeSize, totalPrise);
    }

    public String createStartMessageText() {
        return "Скопируйте шаблон и заполните значения в <>:" +
                "\n[CoffeeOrder]: CoffeeType: <>, CoffeeSize: <> мл, CoffeePrise: <>, CafeName: <>";
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private String formStatsMessageText(int orderCount, double totalCoffeeSize, int totalPrise) {
        var messageTemplate = "Количество чашек кофе: %d\nКоличество выпитого кофе: %f литров\nПотраченная сумма: %d рублей";
        return String.format(
                messageTemplate,
                orderCount,
                totalCoffeeSize,
                totalPrise);
    }

}
