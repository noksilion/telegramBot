package com.kasianov.telegramBot.api.bots;



import com.kasianov.telegramBot.buisnesLogic.services.TelegramBotServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


@Component
public class CityInfoBot extends TelegramLongPollingBot {

    private final TelegramBotServices telegramBotServices;
    private static final Logger logger = LoggerFactory.getLogger(CityInfoBot.class);

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    public CityInfoBot(TelegramBotServices telegramBotServices) {
        this.telegramBotServices = telegramBotServices;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(chatId);
            String messageText = message.getText();
            response.setText(telegramBotServices.processMessage(messageText));
            try {
                execute(response);
                logger.info("Sent message \"{}\" to {}", messageText, chatId);
            } catch (TelegramApiException e) {
                logger.error("Failed to send message \"{}\" to {} due to error: {}", messageText, chatId, e.getMessage());
            }
        }
    }
}
