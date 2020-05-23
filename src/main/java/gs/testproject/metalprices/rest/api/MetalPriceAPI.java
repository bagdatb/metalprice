package gs.testproject.metalprices.rest.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gs.testproject.metalprices.db.entity.MetalEntity;
import gs.testproject.metalprices.db.entity.PriceEntity;
import gs.testproject.metalprices.engine.MetalPriceService;
import gs.testproject.metalprices.engine.transformer.RestMetalTransformer;
import gs.testproject.metalprices.engine.transformer.RestPriceTransformer;
import gs.testproject.metalprices.rest.dto.MetalDTO;
import gs.testproject.metalprices.rest.dto.MetalPriceDTO;
import gs.testproject.metalprices.rest.dto.PriceDTO;

@RestController
@RequestMapping("api")
@Validated
public class MetalPriceAPI
{
	private MetalPriceService metalPriceService;
	private RestMetalTransformer restMetalTransformer;
	private RestPriceTransformer restPriceTransformer;

	@GetMapping("/metals")
	public List<MetalDTO> metals()
	{
		List<MetalEntity> entityList = metalPriceService.getMetals();
		List<MetalDTO> result = restMetalTransformer.transformToDTOList(entityList);

		return result;
	}

	@GetMapping("/prices")
	public List<MetalPriceDTO> prices(
			@RequestParam(name = "metalID", defaultValue = "-1") long metalID,
			@RequestParam(name = "size", defaultValue = "${apiDefaultLimit}") @Min(1) @Max(90) int size)
	{
		final List<MetalPriceDTO> result = new ArrayList<>();

		// Metal ID by default is equal to -1 when no specific metal ID is provided in the API
		if (metalID == -1)
		{
			List<MetalEntity> entityList = metalPriceService.getMetals();

			entityList.forEach(metalEntity -> result.add(getMetalPriceDTO(metalEntity.getId(), 1)));
		}
		else
		{
			result.add(getMetalPriceDTO(metalID, size));
		}

		return result;
	}

	/**
	 * Retrieves {@link MetalPriceDTO} object.
	 *
	 * @param metalId
	 * @param size
	 * @return
	 */
	protected MetalPriceDTO getMetalPriceDTO(long metalId, int size)
	{
		List<PriceEntity> priceEntityList = metalPriceService.getPrices(metalId, size);
		List<PriceDTO> priceDTOList = restPriceTransformer.transformToDTOList(priceEntityList);

		MetalPriceDTO metalPriceDTO = new MetalPriceDTO();
		metalPriceDTO.setMetal(metalId);
		metalPriceDTO.setPrices(priceDTOList);

		return metalPriceDTO;
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public MetalPriceService getMetalPriceService()
	{
		return metalPriceService;
	}

	@Autowired
	public void setMetalPriceService(MetalPriceService metalPriceService)
	{
		this.metalPriceService = metalPriceService;
	}

	public RestMetalTransformer getRestMetalTransformer()
	{
		return restMetalTransformer;
	}

	@Autowired
	public void setRestMetalTransformer(RestMetalTransformer restMetalTransformer)
	{
		this.restMetalTransformer = restMetalTransformer;
	}

	public RestPriceTransformer getRestPriceTransformer()
	{
		return restPriceTransformer;
	}

	@Autowired
	public void setRestPriceTransformer(RestPriceTransformer restPriceTransformer)
	{
		this.restPriceTransformer = restPriceTransformer;
	}
}