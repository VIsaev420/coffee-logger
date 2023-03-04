package ru.lanit.bpm.coffeelogger.bot.longpolling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.adapter.CoffeeOrderAdapter;

import java.util.HashMap;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class BotService {
    private final CoffeeOrderAdapter coffeeOrderAdapter;

    public SendMessage handleMessage(Update update) {
        var message = new SendMessage();
        var incomingMessage = update.getMessage().getText();
        message.setChatId(update.getMessage().getChatId().toString());

        if (incomingMessage.equals("start")) {
            message.setText(startMessageText());
        } else if (incomingMessage.startsWith("[CoffeeOrder]")) {
            coffeeOrderAdapter.createOrder(
                    fillOrder(incomingMessage),
                    update.getMessage().getFrom().getId());
            message.setText("Order saved");
        } else {
            message.setText("Write start");
        }
        return message;
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private HashMap<String, String> fillOrder(String incomingMessage) {
        var keysPattern = Pattern.compile("(\\s\\w*):");
        var keysMatcher = keysPattern.matcher(incomingMessage);

        var valuePattern = Pattern.compile("<(.*?)>");
        var valueMatcher = valuePattern.matcher(incomingMessage);

        var coffeeOrder = new HashMap<String, String>();
        while (keysMatcher.find() && valueMatcher.find()) {
            coffeeOrder.put(keysMatcher.group(1).trim(), valueMatcher.group(1));
        }
        return coffeeOrder;
    }

    private String startMessageText() {
        return "Скопируйте шаблон и заполните значения в <>:" +
                "\n[CoffeeOrder]: CoffeeType: <>, CoffeeSize: <> мл, CoffeePrise: <>, CafeName: <>";
    }
}
