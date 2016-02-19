package pumapass;

class Profile
{
	private String profileName;
	private String passKey;
	
	Profile(final String profileName, final String passKey)
	{
		this.profileName = profileName;
		this.passKey = passKey;
	}
	
	protected void setKey(final String passKey)
	{
		this.passKey = passKey;
	}
	
	protected String getKey()
	{
		return this.passKey;
	}
	
	protected void setName(final String profileName)
	{
		this.profileName = profileName;
	}
	
	protected String getName()
	{
		return this.profileName;
	}

}
