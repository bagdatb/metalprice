package gs.testproject.metalprices.consumer;

import java.time.LocalDate;
import java.util.List;

import gs.testproject.metalprices.consumer.dto.MetalDTO;
import gs.testproject.metalprices.consumer.dto.PriceDTO;

/**
 * Consumer Service Interface to get data from the external resource.
 */
public interface ConsumerService
{
	/**
	 * Get all available Metals from the external resource provider.
	 *
	 * @return
	 */
	List<MetalDTO> getAvailableMetals();

	/**
	 * Get Price information for all available Metals from the external resource provider,
	 * within the specific time period.
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<PriceDTO> getPrices(LocalDate startDate, LocalDate endDate);
}