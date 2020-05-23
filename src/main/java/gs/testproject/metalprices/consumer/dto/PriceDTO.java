package gs.testproject.metalprices.consumer.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * DTO that is used by application to store Price information retrieved from the consumer.
 */
public class PriceDTO
{
	private String date;
	private int metalID;
	private BigDecimal value;

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	@JsonGetter("Date")
	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	@JsonGetter("MetalId")
	public int getMetalID()
	{
		return metalID;
	}

	public void setMetalID(int metalID)
	{
		this.metalID = metalID;
	}

	@JsonGetter("Value")
	public BigDecimal getValue()
	{
		return value;
	}

	public void setValue(BigDecimal value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "Metal{" +
				"date='" + date + '\'' +
				", metalID=" + metalID +
				", value=" + value +
				'}';
	}
}
