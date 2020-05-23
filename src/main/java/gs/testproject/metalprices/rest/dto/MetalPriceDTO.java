package gs.testproject.metalprices.rest.dto;

import java.util.List;

/**
 * DTO that is used by application to provide Metal and Price information to the requester.
 */
public class MetalPriceDTO
{
	private long metal;
	private List<PriceDTO> prices;

	public MetalPriceDTO()
	{
	}

	public MetalPriceDTO(long metal, List<PriceDTO> prices)
	{
		this.metal = metal;
		this.prices = prices;
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public long getMetal()
	{
		return metal;
	}

	public void setMetal(long metal)
	{
		this.metal = metal;
	}

	public List<PriceDTO> getPrices()
	{
		return prices;
	}

	public void setPrices(List<PriceDTO> prices)
	{
		this.prices = prices;
	}
}