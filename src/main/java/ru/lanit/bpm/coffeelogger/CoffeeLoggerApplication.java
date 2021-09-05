package ru.lanit.bpm.coffeelogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.lanit.bpm.coffeelogger.bot.longpolling.Bot;
import ru.lanit.bpm.coffeelogger.bot.longpolling.repository.OperatorRepository;

@SpringBootApplication
public class CoffeeLoggerApplication {

    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi botApi = new TelegramBotsApi();
        try {
            botApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        SpringApplication.run(CoffeeLoggerApplication.class, args);

    }
}
