package gs.testproject.metalprices.engine.transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract transformer to have common methods.
 *
 * @param <E> - Entity
 * @param <D> - DTO
 */
public abstract class AbstractTransformer<E, D> implements Transformer<E, D>
{
	@Override
	public List<D> transformToDTOList(List<E> entityList)
	{
		List<D> result = new ArrayList<>();
		for (E entity : entityList)
		{
			result.add(transformToDTO(entity));
		}
		return result;
	}

	@Override
	public List<E> transformToEntityList(List<D> entityList)
	{
		List<E> result = new ArrayList<>();
		for (D entity : entityList)
		{
			result.add(transformToEntity(entity));
		}
		return result;
	}
}