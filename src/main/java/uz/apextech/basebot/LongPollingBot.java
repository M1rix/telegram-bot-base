package uz.apextech.basebot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class LongPollingBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(LongPollingBot.class);

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
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
                execute(sendSticker);
            }
            if (update.hasMessage() && update.getMessage().hasText()) {
                String data = update.getMessage().getText();
                sendMessage.setText(data);
                execute(sendMessage);
            }
            if (update.hasMessage() && update.getMessage().hasVoice()) {
                sendMessage.setText("voice");
                execute(sendMessage);
            }
            if (update.hasMessage() && update.getMessage().hasAudio()) {
                sendMessage.setText("audio");
                execute(sendMessage);
            }
            if (update.hasInlineQuery()) {
                sendMessage.setText("inlineQuery");
                execute(sendMessage);
            }
            if (update.hasMessage() && update.getMessage().hasDocument()) {
                sendMessage.setText("document");
                execute(sendMessage);

            }
            if (update.hasMessage() && (update.getMessage().hasPhoto())) {
                sendMessage.setText("photo");
                execute(sendMessage);
            }
            if (update.hasMessage() && (update.getMessage().hasVideo())) {
                sendMessage.setText("video");
                execute(sendMessage);
            }
            if (update.hasCallbackQuery()) {
                sendMessage.setText("callback");
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            log.error("Error in messageDocument : {}", e.getMessage());
        }
    }
}
