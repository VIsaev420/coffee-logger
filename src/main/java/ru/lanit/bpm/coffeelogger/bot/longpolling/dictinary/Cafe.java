package ru.lanit.bpm.coffeelogger.bot.longpolling.dictinary;

import lombok.Getter;

@Getter
public enum Cafe implements Dictionary {

    SCURATOV("Скуратов"),
    LIVE("Живой кофе"),
    SURF("Surf"),
    ART("Кафетериус"),
    OTHER("Другое");

    private final String value;

    Cafe(String value) {
        this.value = value;
    }
}
