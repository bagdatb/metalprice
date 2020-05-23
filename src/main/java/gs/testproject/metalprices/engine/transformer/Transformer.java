package gs.testproject.metalprices.engine.transformer;

import java.util.List;

/**
 * Transformer interface to convert entity to DTO.
 *
 * @param <E> - Entity
 * @param <D> - DTO
 */
public interface Transformer<E, D>
{
	/**
	 * Transform the entity to the DTO.
	 *
	 * @param entity
	 * @return DTO
	 */
	D transformToDTO(E entity);

	/**
	 * Transform the list of entities to the list of DTOs.
	 *
	 * @param entityList
	 * @return DTO List
	 */
	List<D> transformToDTOList(List<E> entityList);

	/**
	 * Transform the DTO to the entity.
	 *
	 * @param dto
	 * @return Entity
	 */
	E transformToEntity(D dto);

	/**
	 * Transform the list of DTOs to the list of entities.
	 *
	 * @param dtoList
	 * @return Entity List
	 */
	List<E> transformToEntityList(List<D> dtoList);
}