package ru.lanit.bpm.coffeelogger.bot.longpolling.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.lanit.bpm.coffeelogger.bot.longpolling.persistance.adapter.CoffeeOrderAdapter;

import java.util.HashMap;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class BotService {
    private final CoffeeOrderAdapter coffeeOrderAdapter;
    private final MessageCreatorService messageCreatorService;

    @Transactional
    public SendMessage handleMessage(Update update) {
        var message = new SendMessage();
        var incomingMessage = update.getMessage().getText();
        var userId = update.getMessage().getFrom().getId();
        message.setChatId(update.getMessage().getChatId().toString());

        if (incomingMessage.equals("start") || incomingMessage.equals("/start")) {
            message.setText(messageCreatorService.createStartMessageText());
        } else if (incomingMessage.startsWith("[CoffeeOrder]")) {
            coffeeOrderAdapter.createOrder(
                    fillOrder(incomingMessage),
                    userId);
            message.setText("Order saved");
        } else if (incomingMessage.equals("/week")) {
            var orderList = coffeeOrderAdapter.getWeekStats(userId);
            var messageText = messageCreatorService.createStatsMessageText(orderList);
            message.setText(messageText);
        } else if (incomingMessage.equals("/month")) {
            var orderList = coffeeOrderAdapter.getMontStats(userId);
            var messageText = messageCreatorService.createStatsMessageText(orderList);
            message.setText(messageText);
        } else if (incomingMessage.equals("/repeat")) {
            coffeeOrderAdapter.repeatLastOrder(userId);
            message.setText("Order repeated");
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
}
