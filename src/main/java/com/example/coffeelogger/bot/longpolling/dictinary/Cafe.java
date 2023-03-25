package com.example.coffeelogger.bot.longpolling.dictinary;

import lombok.Getter;

@Getter
public enum Cafe {

    SCURATOV("Скуратов"),
    CP("Кофе поинт"),
    LIVE("Живой кофе"),
    SURF("Surf"),
    ART("Кафетериус"),
    OTHER("Другое");

    private final String value;

    Cafe(String value) {
        this.value = value;
    }
}
