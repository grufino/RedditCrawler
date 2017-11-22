package crawlerAPI;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
public class RedCrawlerBot extends TelegramLongPollingBot {
	
	public RedCrawlerBot(String username, String key){
		this.key = key;
		this.username = username;
	}
	private String defaultError = "Escreva o Subreddit que deseja para encontrar as threads que estão bombando no momento! \nExemplo de formato esperado: /Nadaprafazer askreddit;worldnews;cats \n";
	private Controller cont = new Controller();
	private String key;
	private String username;
    @Override
    public void onUpdateReceived(Update update) {
    	
    	SendMessage message = null;
    	
        if (update.hasMessage() && update.getMessage().hasText()) {
			try {
				
				message = new SendMessage()
				        .setChatId(update.getMessage().getChatId())
				        .setText(getAnswer(update.getMessage().getText()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            try {
                execute(message);
            } catch (Exception e) {
            	message.setText(defaultError);
            	try {
					execute(message);
				} catch (TelegramApiException e1) {
					e1.printStackTrace();
				}
            }
        }	
    }

    @Override
    public String getBotUsername() {
        return this.username;
    }

    @Override
    public String getBotToken() {
        return this.key;
    }
    
    public String getAnswer(String request) {    	
        try{
        	if(request.toLowerCase().startsWith("/nadaprafazer")){
        	String[] arg = null;
        	arg = request.split(" ");
        	String output = cont.getResponse(arg[1]);
        	
        	return (output.length() > 4095) ? output.substring(0, 4096) : output; 
        	
        	}
        	else return defaultError;
        }        
        catch(Exception e){
        	return defaultError;
        }
    }
    
    

}
