package ru.lanit.bpm.coffeelogger.bot.longpolling.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.lanit.bpm.coffeelogger.bot.longpolling.dictinary.Cafe;
import ru.lanit.bpm.coffeelogger.bot.longpolling.dictinary.Dictionary;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateKeyboardService {

    public ReplyKeyboardMarkup createKeyboardByDict(boolean resizeKeyboard) {
        List<KeyboardRow> keyboardRow_List = new ArrayList<>();
        var keyboardRow = new KeyboardRow();

        for (Cafe cafe : Cafe.values()) {
            var button = new KeyboardButton();
            button.setText(cafe.getValue());
            keyboardRow.add(button);
        }

        keyboardRow_List.add(keyboardRow);
        return new ReplyKeyboardMarkup().setResizeKeyboard(resizeKeyboard).setKeyboard(keyboardRow_List);
    }
}
