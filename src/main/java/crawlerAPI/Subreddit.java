package crawlerAPI;

public class Subreddit {
	
	private int upvotes;
	private String subreddit;
	private String threadTitle;
	private String threadCommentsURL;
	private String threadURL;
	
	
	
	public Subreddit(int upvotes, String subreddit, String threadTitle, String threadCommentsURL, String threadURL){
		this.upvotes = upvotes;
		this.subreddit = subreddit;
		this.threadTitle = threadTitle;
		this.threadCommentsURL = threadCommentsURL;
		this.threadURL = threadURL;
	}
	
	public String getSubredditInfo(){
		String info = "";
		info += "Subreddit: " + this.subreddit + "\n";
		info += "Title of the thread: " + this.threadTitle + "\n";
		info += "Upvotes: " + this.upvotes + "\n";
		info += "URL da thread: " + this.threadURL + "\n";
		info += "URL to commment the thread: " + this.threadCommentsURL + "\n\n";		
		return info;
		
	}
	
	
	
}
