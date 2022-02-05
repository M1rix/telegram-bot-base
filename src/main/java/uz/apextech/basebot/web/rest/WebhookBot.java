package uz.apextech.basebot.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
@RequestMapping("/api/v1/bot")
public class WebhookBot {
    private final Logger log = LoggerFactory.getLogger(WebhookBot.class);

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    private final TelegramWebhookBot telegramBotsApi = new TelegramWebhookBot() {
        @Override
        public String getBotToken() {
            return token;
        }

        @Override
        public BotApiMethod onWebhookUpdateReceived(Update update) {
            try {
                botUpdate(update);
            } catch (Exception ex) {
                return null;
            }
            return null;
        }

        @Override
        public String getBotUsername() {
            return username;
        }

        @Override
        public String getBotPath() {
            return username;
        }
    };

    public WebhookBot() {
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public void polloBotUpdate(@RequestBody Update update) {
        try {
            botUpdate(update);
        } catch (Exception ex) {
            return;
        }
    }

    @Async
    public void botUpdate(Update update) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            if (update.hasMyChatMember()) {
                if (update.getMyChatMember().getNewChatMember().getStatus().equalsIgnoreCase("kicked")) {
//                    blockProfile(update.getMyChatMember().getChat().getId());
                }
                if (update.getMyChatMember().getNewChatMember().getStatus().equalsIgnoreCase("member")) {
//                    unBlockProfile(update.getMyChatMember().getChat().getId());
                }
            }
            if (update.hasMessage() && update.getMessage().hasSticker()){
                SendSticker sendSticker = new SendSticker();
                sendSticker.setChatId(update.getMessage().getChatId().toString());
                sendSticker.setSticker(new InputFile("CAACAgQAAxkBAANlYf7MAR4nP_UQh3K8hb49BW78egIAAiMAA1_GARm5TehK9dhg5CME"));
                telegramBotsApi.execute(sendSticker);
            }
            if (update.hasMessage() && update.getMessage().hasText()) {
                String data = update.getMessage().getText();
                sendMessage.setText(data);
                telegramBotsApi.execute(sendMessage);
            }
            if (update.hasMessage() && update.getMessage().hasVoice()) {
                sendMessage.setText("voice");
                telegramBotsApi.execute(sendMessage);
            }
            if (update.hasMessage() && update.getMessage().hasAudio()) {
                sendMessage.setText("audio");
                telegramBotsApi.execute(sendMessage);
            }
            if (update.hasInlineQuery()) {
                sendMessage.setText("inlineQuery");
                telegramBotsApi.execute(sendMessage);
            }
            if (update.hasMessage() && update.getMessage().hasDocument()) {
                sendMessage.setText("document");
                telegramBotsApi.execute(sendMessage);

            }
            if (update.hasMessage() && (update.getMessage().hasPhoto())) {
                sendMessage.setText("photo");
                telegramBotsApi.execute(sendMessage);
            }
            if (update.hasMessage() && (update.getMessage().hasVideo())) {
                sendMessage.setText("video");
                telegramBotsApi.execute(sendMessage);
            }
            if (update.hasCallbackQuery()) {
                sendMessage.setText("callback");
                telegramBotsApi.execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            log.error("Error in messageDocument : {}", e.getMessage());
        }
    }
}
