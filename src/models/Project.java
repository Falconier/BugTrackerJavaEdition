package models;

public class Project
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

	public Project(String[ ] st)
	{
		int len = st.length;
		for( int i = 0; i < len; i++ )
		{
			int col = st[i].indexOf(':');
			int slen = st[i].length();
			String param = st[i].substring(0, col + 1).replace(':', ' ').trim();
			switch( param )
			{
				case "Id" :
				{
					Id = Integer.parseInt(st[i].substring(col + 1, slen));
				}
				case "Name" :
				{
					Name = st[i].substring(col + 2, slen);
				}
				case "Manager" :
				{
					Manager = st[i].substring(col + 1, slen).trim();
				}
				case "Deleted" :
				{
					Deleted = Boolean.parseBoolean(st[i].substring(col + 1, slen));
				}
				case "Description" :
				{
					Description = st[i].substring(col, slen);
				}
				case "Created" :
				{
					Created = st[i].substring(col + 1, slen).trim();
				}
			}
		}
	}

	@Override
	public String toString()
	{
		return "\nPrj Id - " + Id + ", \nPrj Name - " + Name + ", \nManager's GUID - " + Manager + ", \nIs Deleted - "
				+ Deleted + ", \nCreated On - " + Created;
	}

}
