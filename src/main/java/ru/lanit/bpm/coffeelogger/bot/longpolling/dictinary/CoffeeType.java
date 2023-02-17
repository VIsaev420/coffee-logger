package ru.lanit.bpm.coffeelogger.bot.longpolling.dictinary;

import lombok.Getter;

@Getter
public enum CoffeeType {

    ESPRESSO("Эспрессо"),
    AMERICANO("Американо"),
    CAPPUCCINO("Капучино"),
    LATTE("Латте"),
    PEANUT_LATTE("Арахисовый латте"),
    RAF("Раф"),
    BUMBLE("Бамбл"),
    FLAT("Флэт Уайт"),
    TONIC("Эспрессо тоник"),
    OTHER("Другое");

    private final String value;

    CoffeeType(String value) {
        this.value = value;
    }
}
