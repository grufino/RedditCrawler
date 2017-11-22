package crawlerAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Controller {

	private LinkedList<Subreddit> list = new LinkedList<Subreddit>();

	public HttpURLConnection getConnection(String url) {

		try {
			HttpURLConnection con = null;
			URL obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			return con;
		} catch (IOException e) {
			System.err.println("Não foi possível obter conexão, verifique a disponibilidade da URL e sua conexão.");

		}
		return null;
	}

	public String sendGet(String Subreddit) throws Exception {

		String url = getRedditUrl(Subreddit);

		HttpURLConnection con = getConnection(url);
		con.setRequestMethod("GET");

		return parseResponse(parseInput(con.getInputStream()));

	}
	
	public String parseResponse(String response){
		JSONObject myObject = new JSONObject(response.toString());
		myObject = myObject.getJSONObject("data");

		JSONArray arr = new JSONArray();
		arr = myObject.getJSONArray("children");

		if (arr.isNull(0)) {
			System.err.println(
					"Opss! Não conseguimos encontrar a Subreddit desejada, por favor certifique-se da existência da mesma ou corrija o input.");
		}

		for (int i = 0; i < arr.length(); i++) {
			JSONObject curObject = new JSONObject(arr.getJSONObject(i).toString());
			curObject = (JSONObject) curObject.get("data");
			if ((Integer) curObject.get("score") > 5000) {
				try {
					list.add(new Subreddit((Integer) curObject.get("ups"), curObject.getString("subreddit"), curObject.getString("title"),
							"https://reddit.com" + curObject.getString("permalink"), curObject.getString("url")));
				} catch (Exception e) {
					System.err.println(
							"Ops! Não conseguimos encontrar a Subreddit desejada, por favor certifique-se da existência da mesma ou corrija o input.");
				}
			}

		}

		String output = "";
		for (int i = 0; i < list.size(); i++) {
			output += list.get(i).getSubredditInfo();

		}
		return output;
	}

	public String getResponse(String list) {
		String responseString = "";
		if (list.contains(";")) {
			String[] arr = list.split(";");
			for (int i = 0; i < arr.length; i++) {
				try {
					responseString += sendGet(arr[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else
			try {
				return sendGet(list);
			} catch (Exception e) {
				System.err.println(
						"Ops! Não conseguimos encontrar a Subreddit desejada, por favor certifique-se da existência da mesma ou corrija o input.");
			}
		if (responseString == "") {
			System.err.println(
					"Ops! Não conseguimos encontrar a Subreddit desejada, por favor certifique-se da existência da mesma ou corrija o input.");
		}
		return responseString;
	}

	public String getRedditUrl(String Subreddit) {
		return "https://www.reddit.com/r/" + Subreddit.trim() + "/top.json?sort=top&t=all";
	}

	public String parseInput(InputStream input) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(input));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
			return response.toString();

		} catch (Exception e) {
			System.err.println(
					"Ops! Não conseguimos encontrar a Subreddit desejada, por favor certifique-se da existência da mesma ou corrija o input.");
			System.exit(0);
		}

		return null;
	}

}
