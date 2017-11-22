package crawlerAPI;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
public class RedCrawlerBot extends TelegramLongPollingBot {
	
	public RedCrawlerBot(String key, String username){
		this.key = key;
		this.username = username;
	}
	
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
                e.printStackTrace();
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
    	String error = "Escreva o Subreddit que deseja para encontrar as threads que estão bombando no momento! \nExemplo de formato esperado: /Nadaprafazer askreddit;worldnews;cats \n";
        try{
        	if(request.toLowerCase().startsWith("/nadaprafazer")){
        	String[] arg = null;
        	arg = request.split(" ");
        	return cont.getResponse(arg[1]).substring(0,4096);
        	}
        	else return error;
        }        
        catch(Exception e){
        	return error;
        }
    }
    
    

}
