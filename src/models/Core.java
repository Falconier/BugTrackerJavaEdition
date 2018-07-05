package models;
import java.io.*;
import java.net.*;

import extensionsAndHelpers.JSONDecoder;

public class Core
{
	
	
	public static void main(String[] args) throws IOException
	{		
		int projId = 10;
		String query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetProjectById?projectId=" + projId;
		URL url = new URL(query);
		//make the connection
		HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
		//use get mode
		urlc.setRequestMethod("GET");
		//urlc.setRequestProperty("projId", "10");
		int responseCode = urlc.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + query);
		System.out.println("Response Code : " + responseCode);
		//get the results
		BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
		String s = null;
		while((s=br.readLine()) != null )
		{
			System.out.println(s);
		}
		br.close();
		
//		JSONObject jo = new JSONObject(s.toString());
		JSONDecoder j = new JSONDecoder();
		Project prj = (Project) j.Decode(s);
		prj.toString();
		
	}
}

//TODO set up string array or arraylist to parse the json output via a while loop
