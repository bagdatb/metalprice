package gs.testproject.metalprices.rest.dto;

/**
 * DTO that is used by application to provide Metal information to the requester.
 */
public class MetalDTO
{
	private long id;
	private String name;

	public MetalDTO()
	{
	}

	public MetalDTO(long id, String name)
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