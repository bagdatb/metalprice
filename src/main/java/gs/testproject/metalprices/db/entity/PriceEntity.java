package gs.testproject.metalprices.db.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Price Entity class.
 */
public class PriceEntity
{
	private long id;
	private LocalDate priceDate;
	private long metalID;
	private BigDecimal value;

	public PriceEntity()
	{
	}

	public PriceEntity(long id, LocalDate priceDate, long metalID, BigDecimal value)
	{
		this.id = id;
		this.priceDate = priceDate;
		this.metalID = metalID;
		this.value = value;
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

	public LocalDate getPriceDate()
	{
		return priceDate;
	}

	public void setPriceDate(LocalDate priceDate)
	{
		this.priceDate = priceDate;
	}

	public long getMetalID()
	{
		return metalID;
	}

	public void setMetalID(long metalID)
	{
		this.metalID = metalID;
	}

	public BigDecimal getValue()
	{
		return value;
	}

	public void setValue(BigDecimal value)
	{
		this.value = value;
	}
}