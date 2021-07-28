package ru.lanit.bpm.coffeelogger.bot.longpolling;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
            SendMessage message = new SendMessage(chatId.toString(),"asd");
            try {
                this.execute(message);
            } catch (TelegramApiException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }
}
