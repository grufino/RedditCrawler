package crawlerAPI;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ControllerTest extends TestCase{
	
	Controller testController = new Controller();
	
    public ControllerTest( String testName ){
        super( testName );
    }

    public static Test suite(){
        return new TestSuite( ControllerTest.class );
    }

    public void testConnection() throws IOException{
    	assertEquals(200, testController.getConnection("https://google.com").getResponseCode());
    	
    }
    
    public void testgetRedditUrl(){
    	assertEquals(testController.getRedditUrl("cats"), "https://www.reddit.com/r/cats/top.json?sort=top&t=all");
    	
    }
    
    public void testSendGet() throws Exception{
    	assertTrue(testController.sendGet("cats").contains("Subreddit"));
    	
    }
    
    
}
