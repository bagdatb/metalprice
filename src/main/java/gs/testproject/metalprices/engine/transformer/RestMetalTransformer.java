package gs.testproject.metalprices.engine.transformer;

import org.springframework.stereotype.Component;

import gs.testproject.metalprices.db.entity.MetalEntity;
import gs.testproject.metalprices.rest.dto.MetalDTO;

/**
 * Transformer of Metal DTO of Rest API.
 */
@Component
public class RestMetalTransformer extends AbstractTransformer<MetalEntity, MetalDTO>
{
	@Override
	public MetalDTO transformToDTO(MetalEntity entity)
	{
		MetalDTO dto = new MetalDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());

		return dto;
	}

	@Override
	public MetalEntity transformToEntity(MetalDTO entity)
	{
		throw new UnsupportedOperationException();
	}
}
