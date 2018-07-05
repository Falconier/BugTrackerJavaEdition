package models;

import java.sql.Time;

public class Models
{

	class Projects
	{
		public int Id;

		public String Name;

		public String Manager;

		public boolean Deleted;

		public String Description;

		public Time Created;

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

		public Time Created;

		public Time Updated;

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
