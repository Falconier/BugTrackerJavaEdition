package models;

public class ProjectMin
{
	public int Id;

	public ProjectMin()
	{
	}

	public ProjectMin(String[] st)
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
					break;
				}
			}
		}
	}
	
	public int getId()
	{
		return Id;
	}
	
	public void setId(int Id)
	{
		this.Id = Id;
	}
	
	@Override
	public String toString()
	{
		return "Project Id : " + Id;
	}
}
