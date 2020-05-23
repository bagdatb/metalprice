package gs.testproject.metalprices.engine.transformer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import gs.testproject.metalprices.consumer.dto.PriceDTO;
import gs.testproject.metalprices.db.entity.PriceEntity;

/**
 * Transformer of Price DTO of external consumer.
 */
@Component
public class ConsumerPriceTransformer extends AbstractTransformer<PriceEntity, PriceDTO>
{
	@Override
	public PriceDTO transformToDTO(PriceEntity entity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public PriceEntity transformToEntity(PriceDTO dto)
	{
		PriceEntity entity = new PriceEntity();

		entity.setPriceDate(LocalDateTime.parse(dto.getDate()).toLocalDate());
		entity.setMetalID(dto.getMetalID());
		entity.setValue(dto.getValue());

		return entity;
	}
}
