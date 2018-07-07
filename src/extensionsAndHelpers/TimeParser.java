package extensionsAndHelpers;

public class TimeParser
{
	private static boolean DEBUG = true;
	
	public String timeIn ="";
	
	public int length;
	
	public int tLoc;
	
	public TimeParser()
	{
	}
	
	public TimeParser(String time)
	{
		timeIn = time;
		length = time.length();
		tLoc = time.indexOf('T');
	}
	
	public String decode()
	{
		String date = "";
		String timeCode = "";
		
		
		
		return "";
	}

	public static void main(String[] args)
	{
	}

}
