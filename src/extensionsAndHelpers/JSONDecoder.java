package extensionsAndHelpers;

import org.json.simple.*;
import org.json.simple.parser.*;

public class JSONDecoder
{

	private static boolean DEBUG = true;

	public JSONDecoder()
	{
	}

	public Object Decode(String Json)
	{
		Object obj = null;

		JSONParser p = new JSONParser();

		try
		{
			obj = p.parse(Json);
		} catch (ParseException e)
		{
			System.out.println("Failed To Parse");
//			obj = "Failed To Parse";
			if (DEBUG)
			{
				e.printStackTrace();
			}
		}
		return obj;
	}

	/*
	 * JSONParser parser = new JSONParser(); String s =
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
	// TODO make a json deserializer for the api Core
	public static void main(String[] args)
	{

	}

}
