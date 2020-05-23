package gs.testproject.metalprices.engine;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import gs.testproject.metalprices.consumer.ConsumerService;
import gs.testproject.metalprices.consumer.dto.PriceDTO;
import gs.testproject.metalprices.db.entity.PriceEntity;
import gs.testproject.metalprices.engine.transformer.ConsumerPriceTransformer;

/**
 * Scheduler class that loads latest Metal prices.
 * By default it runs once a day.
 */
@Component
public class ConsumerScheduler
{
	private static final Logger logger = Logger.getLogger(ConsumerScheduler.class.getName());

	private ConsumerService consumerService;
	private MetalPriceService metalPriceService;
	private ConsumerPriceTransformer consumerPriceTransformer;

	/**
	 * Loads today's Prices for all Metals once a day.
	 */
	@Scheduled(cron = "${cronExpressionPriceLoader}")
	public void scheduleTaskWithCronExpression()
	{
		logger.info("Loading current day prices...");

		LocalDate currentDate = LocalDate.now();

		boolean dateIsLoaded = getMetalPriceService().idDateLoaded(currentDate);

		if (!dateIsLoaded)
		{
			List<PriceDTO> prices = getConsumerService().getPrices(currentDate, currentDate);
			List<PriceEntity> priceEntities = getConsumerPriceTransformer().transformToEntityList(prices);

			getMetalPriceService().insertPriceList(priceEntities);

			logger.info("Current day prices loaded successfully.");
		}
		else
		{
			logger.info("Current day prices have already been loaded.");
		}
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