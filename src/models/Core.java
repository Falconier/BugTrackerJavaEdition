package models;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import extensionsAndHelpers.JSONDecoder;

import models.Project;


public class Core
{
	private static final boolean DEBUG = false;
	private static final boolean PRJ = false;
	private static final boolean TKT = true;

	public Core()
	{
	}

	public static Project GetProjectDetails(int projId)
	{
		String query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetProjectById?projectId="
				+ projId;
		String output = "";
		try
		{
			output = request(query);
		} catch ( Exception e )
		{
			System.out.println("Query Request Failed. Check Stack Trace for more information.");
			e.printStackTrace();
		}

		JSONDecoder d = new JSONDecoder(output);

		Project prj = new Project(d.decode());
		if( DEBUG )
		{
			System.out.println(prj.toString());
		}
		return prj;
	}

	public static Ticket GetTicketDetails(int tktId)
	{
		String query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetProjectById?projectId="
				+ tktId;
		String output = "";
		try
		{
			output = request(query);
		} catch ( Exception e )
		{
			System.out.println("Query Request Failed. Check Stack Trace for more information.");
			e.printStackTrace();
		}

		JSONDecoder d = new JSONDecoder(output);

		Ticket tkt = new Ticket(d.decode());
		if( DEBUG )
		{
			System.out.println(tkt.toString());
		}
		return tkt;
	}

	public static List<Project> GetProjectsList()
	public static List<ProjectMin> GetProjectsMinList()
	{
		String query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetAllProjectsMinDetails";
		String response = "";
		List<ProjectMin> Pm = new ArrayList<ProjectMin>();
		try
		{
			response += request(query);
		} catch ( IOException e )
		{
			System.out.println("Query Request Failed. Check Stack Trace for more information.");
			e.printStackTrace();
		}

		JSONDecoder d = new JSONDecoder(response);
		String[] a = d.decode();
		for(int i = 0; i<a.length; i++)
		{
			ProjectMin prj = new ProjectMin();
			prj.setId(Integer.parseInt(a[i].substring(a[i].indexOf(':')+1)));
			Pm.add(prj);
//			System.out.println(Pm.get(i));
		}
		return Pm;
	}

	public static List<Project> GetAllProjects()
	{
		String query = "";
		String response = "";
		List<ProjectMin> ml = Core.GetProjectsMinList();
		List<Project> prjs = new ArrayList<Project>();
		JSONDecoder d = new JSONDecoder();
		for (int i = 0; i<ml.size();i++)
		{
			int prjId = ml.get(i).getId();
			query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetProjectById?projectId=" + prjId;
			try
			{
				response = Core.request(query);
				d.setDecoderIn(response);
				Project prj = new Project(d.decode());
				prjs.add(prj);
			} catch ( IOException e )
			{
				System.out.println("Query Request Failed on project " + prjId + ". Check Stack Trace for more information.");
				e.printStackTrace();
			}
		}
		return prjs;
	}
	
	public static List<TicketMin> GetTicketsMinOnProjectList(int prjId)
	{
		String query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetTicketsMinOnProject?projId=" + prjId;
		String response = "";
		List<TicketMin> Tm = new ArrayList<TicketMin>();
		try
		{
			response += request(query);
		} catch ( IOException e )
		{
			System.out.println("Query Request Failed. Check Stack Trace for more information.");
			e.printStackTrace();
		}

		JSONDecoder d = new JSONDecoder(response);
		String[] a = d.decode();
		for(int i = 0; i<a.length; i++)
		{
			TicketMin tkt = new TicketMin();
			tkt.setId(Integer.parseInt(a[i].substring(a[i].indexOf(':')+1)));
			Tm.add(tkt);
//			System.out.println(Pm.get(i));
		}
		return Tm;
	}
	
	public static List<Ticket> GetTicketsOnProject(int prjId)
	{
		String query = "";
		String response = "";
		List<TicketMin> ml = Core.GetTicketsMinOnProjectList(prjId);
		List<Ticket> tkts = new ArrayList<Ticket>();
		JSONDecoder d = new JSONDecoder();
		for (int i = 0; i<ml.size();i++)
		{
			int tktId = ml.get(i).getId();
			query = "http://jebbugtrackerservice.azurewebsites.net:80/Api/BugTracker/GetTicketById?tktId=" + tktId;
			try
			{
				response = Core.request(query);
				d.setDecoderIn(response);
				Ticket tkt = new Ticket(d.decode());
				tkts.add(tkt);
			} catch ( IOException e )
			{
				System.out.println("Query Request Failed on project " + prjId + ". Check Stack Trace for more information.");
				e.printStackTrace();
			}
		}
		return tkts;
	}
	
	public static String request(String query) throws IOException
	{
		URL url = new URL(query);
		HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
		urlc.setRequestMethod("GET");
		int responseCode = urlc.getResponseCode();
		if( DEBUG )
		{
			System.out.println("\nSending 'GET' request to URL : " + query);
			System.out.println("Response Code : " + responseCode);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
		String s = null;
		String response = "";
		while( (s = br.readLine()) != null )
		{
			response += s;
			System.out.println(s);
		}
		if( DEBUG )
		{
			System.out.println(response);
		}
		br.close();
		return response;
	}

	public static void main(String[] args) throws IOException
	{
		List<Ticket> a = Core.GetTicketsOnProject(10);
		for( int i = 0; i<a.size(); i++)
		{
			System.out.println(a.get(i));
		}
	}
}

// TODO set up string array or arraylist to parse the json output via a while
// loop
