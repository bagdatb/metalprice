package gs.testproject.metalprices.engine.transformer;

import org.springframework.stereotype.Component;

import gs.testproject.metalprices.consumer.dto.MetalDTO;
import gs.testproject.metalprices.db.entity.MetalEntity;

/**
 * Transformer of Metal DTO of external consumer.
 */
@Component
public class ConsumerMetalTransformer extends AbstractTransformer<MetalEntity, MetalDTO>
{
	@Override
	public MetalDTO transformToDTO(MetalEntity entity)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public MetalEntity transformToEntity(MetalDTO dto)
	{
		MetalEntity entity = new MetalEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		return entity;
	}
}