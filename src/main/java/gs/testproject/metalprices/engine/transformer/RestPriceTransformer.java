package gs.testproject.metalprices.engine.transformer;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gs.testproject.metalprices.db.entity.PriceEntity;
import gs.testproject.metalprices.rest.dto.PriceDTO;

/**
 * Transformer of Price DTO of Rest API.
 */
@Component
public class RestPriceTransformer extends AbstractTransformer<PriceEntity, PriceDTO>
{
	private DateTimeFormatter apiDateFormatter;

	@Override
	public PriceDTO transformToDTO(PriceEntity entity)
	{
		PriceDTO dto = new PriceDTO();
		dto.setDate(apiDateFormatter.format(entity.getPriceDate()));
		dto.setPrice(entity.getValue());

		return dto;
	}

	@Override
	public PriceEntity transformToEntity(PriceDTO entity)
	{
		throw new UnsupportedOperationException();
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public DateTimeFormatter getApiDateFormatter()
	{
		return apiDateFormatter;
	}

	@Autowired
	public void setApiDateFormatter(DateTimeFormatter apiDateFormatter)
	{
		this.apiDateFormatter = apiDateFormatter;
	}
}