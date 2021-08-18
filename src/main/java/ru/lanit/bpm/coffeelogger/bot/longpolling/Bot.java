package ru.lanit.bpm.coffeelogger.bot.longpolling;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "helper";
    }

    @Override
    public String getBotToken() {
        return "test";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String incomingMessage = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            if (incomingMessage.equals("/start")) {
                var test_button = new KeyboardButton("test");
                var one_button = new KeyboardButton("1");
                var keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboardRow_List = new ArrayList<>();
                var keyboardRow = new KeyboardRow();
                keyboardRow.set(1,one_button);
                keyboardRow.set(2, test_button);
                keyboardRow_List.add(keyboardRow);
                keyboardMarkup.setKeyboard(keyboardRow_List);

                message.setReplyMarkup(keyboardMarkup);
            }
            try {
                this.execute(message);
            } catch (TelegramApiException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }
}
