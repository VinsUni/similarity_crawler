package pages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import models.WSResult;
import models.WSResults;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import utils.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.luxmedien.googlecustomsearch.GoogleCustomSearch;

public class GoogleSearcher {
	
	public WSResults getResults(String query) throws URISyntaxException, IOException {
		URL request;
		WSResults WSreturn = new WSResults();
		String req = "key=" + Utils.GOOGLE_API_KEY;
		req += "&cx=" + Utils.GOOGLE_PROJECT_ID;
		req += "&q=" + query;
		req += "&alt=json";
		req += "&lr=lang_pt";
		req += "&excludeTerms=youtube.com";
		
		URI uri = new URI("https", "www.googleapis.com", "/" +"customsearch/v1", req, null);
		System.out.println("[Google Request]: " + uri);
		request = uri.toURL();
		
		String json = Utils.readFromWS(request);
		
		Gson gson = utils.GsonSingleton.getGson();
		
		JsonParser p = new JsonParser();
		JsonObject j = (JsonObject) p.parse(json);
		JsonArray jData = j.getAsJsonArray("items");
		
		WSreturn.setItems(gson.fromJson(jData.toString(), WSResult[].class)); 
		
		return WSreturn;
	}

  public static void main(String[] args) throws URISyntaxException, IOException {
	  GoogleSearcher s = new GoogleSearcher();
	  for (WSResult result: s.getResults("mombojo").getItems()) {
		  System.out.println(result.getLink());
	  }
    
  }
}