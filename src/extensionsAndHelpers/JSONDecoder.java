package extensionsAndHelpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.*;


public class JSONDecoder
{

	private static boolean DEBUG = false;

	public String json = "";

	// TODO make private
	public String parsing = "";

	private int length;

	private int firstComma;

	private int lastComma;

	private int numComma;

	private String[] pass1;

	private String[] pass2;

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
	
	public void setDecoderIn(String JSON)
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

	private int countBrace()
	{
		int counter = 0;
		for( int i = 0; i < json.length(); i++ )
		{
			if( json.charAt(i) == '}' )
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
			subs = parsing.substring(0, fC + 1).replace('"', ' ').replace('{', ' ').replace(',', ' ').replace('}', ' ').replace('[', ' ').replace(']', ' ');
			pass1[i] = subs.trim();
			parsing = parsing.substring(fC + 1, parsing.length());
			fC = parsing.indexOf(",");
		}
		pass1[numComma] = parsing.replace('"', ' ').replace('{', ' ').replace(',', ' ').replace('}', ' ').replace('[', ' ').replace(']', ' ').trim();

		return pass1;
	}

	/**
	 * Not really sure that this is a logical idea
	 * 
	 * @return
	 */
	public String[] doublePassDecode()
	{

		int fB = countBrace();
		String[] a = decode();
		String subs = "";
		int p1l = a.length;
		pass2 = new String[ p1l + 1 ];

		int count = (int) p1l / fB;

		for( int i = 0; i < a.length; i += count )
		{
			for( int j = 0; j < count; j++ )
			{
				subs = a[i + j].substring(0, a[i + j].indexOf(':') + 1);
				pass2[i] = subs.trim();
			}
		}

		return pass2;
	}
	
	
	// this is really illogical
	// what am I doing
//	public String[] regexPass()
//	{
//		String re1="(\\[)";	// Any Single Character 1
//
//	    Pattern p = Pattern.compile(re1,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
//	    Matcher m = p.matcher(txt);
//	    if (m.find())
//	    {
//	        String c1=m.group(1);
//	        System.out.print("("+c1.toString()+")"+"\n");
//	    }
//	}

	@Override
	public String toString()
	{
		return "\n json " + "\nLength : " + length + " fist comman @ " + firstComma + " last comma @ " + lastComma;
	}

	public static void main(String[] args) throws IOException
	{
		int projId = 14;
		//String query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetAllProjects";
		// + projId;
		String query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetProjectById?projectId=" + projId;
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

		JSONDecoder d = new JSONDecoder(response);
		System.out.println("\n" + d.toString());
		// Decode the response from the 'GET' request
		String[] a = d.decode();
		// System.out.println(m.decode());
		// System.out.println(m.parsing.substring(0,8).replace('"', ' '));
		System.out.println();
		for( int i = 0; i < a.length; i++ )
		{
			System.out.println(a[i]);
		}
		System.out.println(a.length);
		
//		String re1="(\\[)";	// Any Single Character 1
//
//	    Pattern p = Pattern.compile(re1,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
//	    Matcher m = p.matcher(response);
//	    if (m.find())
//	    {
//	        String c1=m.group(1);
//	        System.out.print("("+c1.toString()+")"+"\n");
//	    }
	}

}
