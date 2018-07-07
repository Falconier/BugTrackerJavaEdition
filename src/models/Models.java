package models;

import java.sql.Time;

public class Models
{

	class Project
	{
		public int Id;

		public String Name;

		public String Manager;

		public boolean Deleted;

		public String Description;

		public String Created;

		public Project()
		{
		}
		
		public Project(String[] st)
		{
			int len = st.length;
			for(int i = 0; i<len; i++)
			{
				int col = st[i].indexOf(':');
				String param = st[i].substring(0,col+1).replace(':', ' ').trim();
				switch(param)
				{
				case "Id" :  
				{
					Id = Integer.parseInt(st[i].substring(col+1,len));
				}
				case "Name" : 
				{
					Name = st[i].substring(col+1, len);
				}
				case "Manager" :
				{
					Manager = st[i].substring(col+1, len);
				}
				case "Deleted" :
				{
					Deleted = Boolean.parseBoolean(st[i].substring(col+1, len));
				}
				case "Description":
				{
					Description = st[i].substring(col+1, len);
				}
				case "Created":
				{
					Created = st[i].substring(col+1, len);
				}
				}
			}
		}
		
		@Override
		public String toString()
		{
			return "Prj Id : " + Id + ", Prj Name : " + Name + ", Manager's GUID : " + Manager + ", Is Deleted : "
					+ Deleted + ", Created On : " + Created;
		}
	}

	class Ticket
	{
		public int Id;

		public String Title;

		public String Description;

		public int ProjectId;

		public String Created;

		public String Updated;

		public int TicketPriorityId;

		public int TicketTypeId;

		public int TicketStatusId;

		public String AssignedToUserId;

		public String OwnerUserId;

		public boolean isResolved;
	}

	class BugTrackerUser
	{
		public String FullName;

		public String Id;
	}

	class BugTrackerUserDetails
	{
		public String FirstName;

		public String LastName;

		public String Id; // GUID

		public String UserName;

		public String Email;
	}
}
