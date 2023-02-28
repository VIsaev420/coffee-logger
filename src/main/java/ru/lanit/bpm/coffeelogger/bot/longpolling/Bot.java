package ru.lanit.bpm.coffeelogger.bot.longpolling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.adapter.CoffeeOrderAdapter;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.adapter.OperatorAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {
    private final CoffeeOrderAdapter coffeeOrderAdapter;
    private final OperatorAdapter operatorAdapter;

    @Override
    public String getBotUsername() {
        return "helper";
    }

    @Override
    public String getBotToken() {
        try {
            return Files.readString(Path.of("bot_token.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String incomingMessage = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            var delKeyboard = new ReplyKeyboardRemove();

            if (incomingMessage.equals("start")) {
                message.setText("Скопируйте шаблон и заполните значения в <>:" +
                    "\n[CoffeeOrder]: CoffeeType: <>, CoffeeSize: <>, CoffeePrise: <>, CafeName: <>" +
                    "\nЕсли было взято, кроме кофе, то заполни следующий шаблон:" +
                    "\n[CoffeeAndCustomOrder] CoffeeName: {}, CoffeeType: {}, CoffeeSize: {}, CoffeePrise: {}, CafeName: {}, Цена допника: {}, Описание допника: {}");
            } else if (incomingMessage.startsWith("[CoffeeOrder]")) {

                var keysPattern = Pattern.compile("(\\s\\w*):");
                var keysMatcher = keysPattern.matcher(incomingMessage);

                var valuePattern = Pattern.compile("<(.*?)>");
                var valueMatcher = valuePattern.matcher(incomingMessage);

                var coffeeOrder = new HashMap<String, String>();

                while (keysMatcher.find() && valueMatcher.find()) {
                    coffeeOrder.put(keysMatcher.group(1).trim(), valueMatcher.group(1));
                }

                var operatorInstance = operatorAdapter.createOperator(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName(),
                    update.getMessage().getFrom().getUserName());

                var orderInstance = coffeeOrderAdapter.createOrder(coffeeOrder, operatorInstance);

                message.setText("Запись создана");
            } else if (incomingMessage.startsWith("[CoffeeAndCustomOrder]")) {
                message.setText("write custom order");
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
