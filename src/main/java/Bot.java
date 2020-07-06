import exception.groupNoInputInfoException;
import exception.haveNoTextException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import java.io.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot extends TelegramLongPollingBot {

    private static final Logger logger = Logger.getLogger(Bot.class.getName());
    private static String bot_token;
    private static String username;


    public static void main(String[] args){
        try {
            loadSettings();
            loadValutes();
            Text_message.load();
        } catch (IOException e) {
            logger.log(Level.WARNING,"Lose file", e);
        }
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            logger.log(Level.WARNING,"Error bot", e);
        }
    }

    public synchronized void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            logger.log(Level.WARNING,"Error sendMsg", e);
        }
    }


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message != null){
            try {
                sendMsg(message, Text_message.getMessage(message));
            } catch (IOException | ParseException e) {
                logger.log(Level.WARNING,"Error loading", e);
            }
            catch (HaveNoTextException e) {
                sendMsg(message, "Напишите мне что-нибудь нормальное");
            } catch (GroupNoInputInfoException ignored) {}
        }
    }

    public String getBotUsername() {
        return username;
    }

    public String getBotToken() {
        return bot_token;
    }

    public static void loadValutes() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("./src/main/resources/valute.txt")));

        String line = reader.readLine();
        while (line != null){
            Valute.valutes.add(line);
            City_course.valutes.add(line);
            line = reader.readLine();
        }
        reader.close();
    }
    public static void loadSettings() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("./src/main/resources/Token.txt")));

        String line = reader.readLine();
        bot_token = line;
        username = reader.readLine();
        reader.close();
    }
}
