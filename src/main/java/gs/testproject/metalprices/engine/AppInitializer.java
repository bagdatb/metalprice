package gs.testproject.metalprices.engine;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import gs.testproject.metalprices.consumer.ConsumerService;
import gs.testproject.metalprices.consumer.dto.MetalDTO;
import gs.testproject.metalprices.consumer.dto.PriceDTO;
import gs.testproject.metalprices.db.entity.MetalEntity;
import gs.testproject.metalprices.db.entity.PriceEntity;
import gs.testproject.metalprices.engine.transformer.ConsumerMetalTransformer;
import gs.testproject.metalprices.engine.transformer.ConsumerPriceTransformer;

/**
 * Application Initializer class.
 * Right after application is started, this initializer loads initial records from consumer.
 */
@Component
public class AppInitializer
{
	private static final Logger logger = Logger.getLogger(AppInitializer.class.getName());

	private ConsumerService consumerService;
	private MetalPriceService metalPriceService;
	private ConsumerMetalTransformer consumerMetalTransformer;
	private ConsumerPriceTransformer consumerPriceTransformer;

	@Value("${apiDaysToLoad}")
	protected int daysToLoad;

	@EventListener(ApplicationReadyEvent.class)
	public void startApp()
	{
		logger.info("Loading initial data...");

		loadMetals();
		loadPrices();

		logger.info("Initial data load completed.");
	}

	/**
	 * Loads all available Metals from consumer and saves them in the local database.
	 */
	protected void loadMetals()
	{
		List<MetalDTO> metalDTOList = getConsumerService().getAvailableMetals();
		List<MetalEntity> metalEntityList = getConsumerMetalTransformer().transformToEntityList(metalDTOList);
		getMetalPriceService().insertMetalList(metalEntityList);
	}

	/**
	 * Loads all available Prices from consumer and saves them in the local database.
	 * Not all Price history is loaded, but only certain amount of days.
	 * Number of days that should be loaded when application is started is configurable.
	 */
	protected void loadPrices()
	{
		LocalDate currentDate = LocalDate.now();
		LocalDate earlierDate = currentDate.minusDays(daysToLoad);

		List<PriceDTO> priceDTOS = getConsumerService().getPrices(earlierDate, currentDate);
		List<PriceEntity> priceEntities = getConsumerPriceTransformer().transformToEntityList(priceDTOS);
		getMetalPriceService().insertPriceList(priceEntities);
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public ConsumerService getConsumerService()
	{
		return consumerService;
	}

	@Autowired
	public void setConsumerService(ConsumerService consumerService)
	{
		this.consumerService = consumerService;
	}

	public MetalPriceService getMetalPriceService()
	{
		return metalPriceService;
	}

	@Autowired
	public void setMetalPriceService(MetalPriceService metalPriceService)
	{
		this.metalPriceService = metalPriceService;
	}

	public ConsumerMetalTransformer getConsumerMetalTransformer()
	{
		return consumerMetalTransformer;
	}

	@Autowired
	public void setConsumerMetalTransformer(ConsumerMetalTransformer consumerMetalTransformer)
	{
		this.consumerMetalTransformer = consumerMetalTransformer;
	}

	public ConsumerPriceTransformer getConsumerPriceTransformer()
	{
		return consumerPriceTransformer;
	}

	@Autowired
	public void setConsumerPriceTransformer(ConsumerPriceTransformer consumerPriceTransformer)
	{
		this.consumerPriceTransformer = consumerPriceTransformer;
	}
}