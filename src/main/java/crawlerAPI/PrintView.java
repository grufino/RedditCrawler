package crawlerAPI;

import java.util.Scanner;


public class PrintView{ 

	
	public static void main (String[] args){
	 Controller test = new Controller();
	 System.out.println("Escreva o Subreddit que deseja para encontrar as threads que estão bombando no momento! \n");
	 System.out.println("Exemplo de formato esperado: askreddit;worldnews;cats \n");
	 Scanner in = new Scanner(System.in);
	 String output = null;
	 try {
		 output = test.getResponse(in.nextLine());
		 System.out.println(output);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 in.close();
	 
	} 
}
