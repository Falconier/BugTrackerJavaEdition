package extensionsAndHelpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JSONDecoder
{

	private static boolean DEBUG = true;

	public String json = "";

	// TODO make private
	public String parsing = "";

	private int length;

	private int firstComma;

	private int lastComma;

	private int numComma;

	private String[] pass1;

	public JSONDecoder()
	{
	}

	public JSONDecoder(String JSON)
	{
		json = JSON;
		parsing = JSON;
		length = json.length();
		firstComma = json.indexOf(",");
		lastComma = json.lastIndexOf(",");

		numComma = count();
		pass1 = new String[ numComma + 1 ];
	}

	private int count()
	{
		int counter = 0;
		for( int i = 0; i < json.length(); i++ )
		{
			if( json.charAt(i) == ',' )
			{
				counter++;
			}
		}
		return counter;
	}

	public String[] decode()
	{
		// TODO add ability to recognize repeating information. such as when
		// 'GetAllUsers' or 'GetAllProjects' is called. NOTE THE ENDING '}' STILL
		// EXISTS. CAN I MAKE USE OF THAT???????
		int fC = firstComma;
		String subs = "";

		for( int i = 0; i < numComma; i++ )
		{
			subs = parsing.substring(0, fC + 1).replace('"', ' ').replace('{', ' ').replace(',', ' ');
			pass1[i] = subs.trim();
			parsing = parsing.substring(fC + 1, parsing.length());
			fC = parsing.indexOf(",");
		}
		pass1[numComma] = parsing.replace('"', ' ').replace('}', ' ').trim();

		return pass1;
	}

	@Override
	public String toString()
	{
		return "\n json " + "\nLength : " + length + " fist comman @ " + firstComma + " last comma @ " + lastComma;
	}

	/*
	 * ORIGINAL CODE JSONParser parser = new JSONParser(); String s =
	 * "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
	 * 
	 * try{ Object obj = parser.parse(s); JSONArray array = (JSONArray)obj;
	 * 
	 * System.out.println("The 2nd element of array");
	 * System.out.println(array.get(1)); System.out.println();
	 * 
	 * JSONObject obj2 = (JSONObject)array.get(1);
	 * System.out.println("Field \"1\""); System.out.println(obj2.get("1"));
	 * 
	 * s = "{}"; obj = parser.parse(s); System.out.println(obj);
	 * 
	 * s = "[5,]"; obj = parser.parse(s); System.out.println(obj);
	 * 
	 * s = "[5,,2]"; obj = parser.parse(s); System.out.println(obj);
	 * }catch(ParseException pe){
	 * 
	 * System.out.println("position: " + pe.getPosition()); System.out.println(pe);
	 * }
	 */
	public static void main(String[] args) throws IOException
	{
		int projId = 14;
		String query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetProjectById?projectId="
				+ projId;
		URL url = new URL(query);
		// make the connection
		HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
		// use get mode
		urlc.setRequestMethod("GET");
		// urlc.setRequestProperty("projId", "10");
		int responseCode = urlc.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + query);
		System.out.println("Response Code : " + responseCode);
		// get the results
		BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
		String s = null;
		String response = "";
		while( (s = br.readLine()) != null )
		{
			response += s;
			System.out.println(s);
		}
		System.out.println(response);
		br.close();

		JSONDecoder m = new JSONDecoder(response);
		System.out.println("\n" + m.toString());
		String[] a = m.decode();
		// System.out.println(m.decode());
		// System.out.println(m.parsing.substring(0,8).replace('"', ' '));
		System.out.println();
		for( int i = 0; i < a.length; i++ )
		{
			System.out.println(a[i]);
		}
	}

}
