package gs.testproject.metalprices.rest.dto;

import java.math.BigDecimal;

/**
 * DTO that is used by application to provide Price information to the requester.
 */
public class PriceDTO
{
	private String date;
	private BigDecimal price;

	public PriceDTO()
	{
	}

	public PriceDTO(String date, BigDecimal price)
	{
		this.date = date;
		this.price = price;
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
}