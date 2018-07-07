package models;

public class Ticket
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

	public Ticket()
	{
	}

	public Ticket(String[] st)
	{
		int len = st.length;
		for (int i = 0; i < len; i++)
		{
			int col = st[i].indexOf(':');
			int slen = st[i].length();
			String param = st[i].substring(0, col + 1);
			switch (param)
				{
				case "Id":
				{
					Id = Integer.parseInt(st[i].substring(col + 1, slen).replace(':', ' ').trim());
				}
				case "Title":
				{
					Title = st[i].substring(col + 1, slen);
				}
				case "Description":
				{
					Description = st[i].substring(col, slen);
				}
				case "ProjectId":
				{
					ProjectId = Integer.parseInt(st[i].substring(col + 1, slen));
				}
				case "Created":
				{
					Created = st[i].substring(col + 1, slen).trim();
				}
				case "Updated":
				{
					Updated = st[i].substring(col + 1, slen).trim();
				}
				case "TicketPriorityId":
				{
					TicketPriorityId = Integer.parseInt(st[i].substring(col + 1, slen));
				}
				case "TicketTypeId":
				{
					TicketTypeId = Integer.parseInt(st[i].substring(col + 1, slen));
				}
				case "TicketStatusId":
				{
					TicketStatusId = Integer.parseInt(st[i].substring(col + 1, slen));
				}
				case "OwnerUserId":
				{
					OwnerUserId = st[i].substring(col + 1, slen).trim();
				}
				case "AssignedToUserId":
				{
					String stuff = st[i].substring(col + 1, slen);
					AssignedToUserId = stuff;
				}
				case "isResolved":
				{
					isResolved = Boolean.parseBoolean(st[i].substring(col + 1, slen));
				}
				}
		}
	}

	@Override
	public String toString()
	{
		return "\nTkt Id - " + Id + "\nTitle - " + Title + "\nOn Project - " + ProjectId + "\nCreated On - " + Created
				+ "\nPriority - " + TicketPriorityId + " \nType - " + TicketTypeId + "\nStatus - " + TicketStatusId
				+ "\nAssigned To GUID : " + AssignedToUserId + "\nOwned By GUID : " + OwnerUserId + "\nIs Resolved - "
				+ isResolved;
	}
}
