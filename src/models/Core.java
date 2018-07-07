package models;
import java.io.*;
import java.net.*;

import extensionsAndHelpers.JSONDecoder;

import models.Project;
public class Core
{
	
	
	private static final boolean DEBUG = false;
	private static Project prj;

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
		String response = "";
		while((s=br.readLine()) != null )
		{
			response += s;
			System.out.println(s);
		}
		if(DEBUG)
		{
			System.out.println(response);
		}
		br.close();
		
		JSONDecoder d = new JSONDecoder(response);
		Project prj = new Project(d.decode());
		
		System.out.println(prj.toString());
		
	}
}

//TODO set up string array or arraylist to parse the json output via a while loop
