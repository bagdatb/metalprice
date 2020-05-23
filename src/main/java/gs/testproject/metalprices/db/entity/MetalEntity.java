package gs.testproject.metalprices.db.entity;

/**
 * Metal Entity class.
 */
public class MetalEntity
{
	private long id;
	private String name;

	public MetalEntity()
	{
	}

	public MetalEntity(long id, String name)
	{
		this.id = id;
		this.name = name;
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}