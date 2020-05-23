package gs.testproject.metalprices.consumer.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * DTO that is used by application to store Metal information retrieved from the consumer.
 */
public class MetalDTO
{
	private long id;
	private String name;

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	@JsonGetter("Id")
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@JsonGetter("NameEng")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Metal{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}