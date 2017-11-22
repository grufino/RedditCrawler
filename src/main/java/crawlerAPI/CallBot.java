package crawlerAPI;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;



public class CallBot {
	public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new RedCrawlerBot(null, null));//SUAS CREDENCIAIS AQUI
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}