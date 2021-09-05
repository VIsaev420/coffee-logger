package ru.lanit.bpm.coffeelogger.bot.longpolling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.OperatorRepository;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.entity.Operator;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Slf4j
@Component
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
            var delKeyboard = new ReplyKeyboardRemove();

            if (incomingMessage.equals("start")) {
                message.setText("Скопируйте шаблон и заполните значения в {}:" +
                    "\n[CoffeeOrder]: CoffeeType: <>, CoffeeSize: <>, CoffeePrise: <>, CafeName: <>" +
                    "\nЕсли было взято, кроме кофе, то заполни следующий шаблон:" +
                    "\n[CoffeeAndCustomOrder] CoffeeName: {}, CoffeeType: {}, CoffeeSize: {}, CoffeePrise: {}, CafeName: {}, Цена допника: {}, Описание допника: {}");
            } else if (incomingMessage.startsWith("[CoffeeOrder]")) {

                var keysPattern = Pattern.compile("(\\s\\w*):");
                var keysMatcher = keysPattern.matcher(incomingMessage);

                var valuePattern = Pattern.compile("<(\\S*)>");
                var valueMatcher = valuePattern.matcher(incomingMessage);

                var keysMatches = new ArrayList<>();
                var valueMatches = new ArrayList<>();

                while (keysMatcher.find()) {
                    keysMatches.add(keysMatcher.group(1).trim());
                }
                while (valueMatcher.find()) {
                    valueMatches.add(valueMatcher.group(1));
                }
                log.info("keys[{}], values[{}], first values={}", keysMatches, valueMatches, valueMatches.get(0));

                var operator = new Operator();
                operator.setId(update.getMessage().getFrom().getUserName());
                operator.setName(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName());
                OperatorSaver.save(operator);

                message.setText("see app logs");

            } else if (incomingMessage.startsWith("[CoffeeAndCustomOrder]")) {

            } else {
                message.setText("write start");
                message.setReplyMarkup(delKeyboard);
            }
            try {
                this.execute(message);
            } catch (TelegramApiException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }
}
