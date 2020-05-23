package gs.testproject.metalprices.consumer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import gs.testproject.metalprices.consumer.dto.MetalDTO;
import gs.testproject.metalprices.consumer.dto.PriceDTO;

/**
 * Default implementation of {@link ConsumerService}.
 */
@Component
public class DefaultConsumerService implements ConsumerService
{
	private static final Logger logger = Logger.getLogger(ConsumerService.class.getName());

	private RestTemplate restTemplate;
	private DateTimeFormatter consumerDateFormatter;

	@Value("${consumerBaseURL}") private String baseURL;
	@Value("${consumerMetalAPI}") private String metalAPI;
	@Value("${consumerPriceAPI}") private String priceAPI;

	@Override
	public List<MetalDTO> getAvailableMetals()
	{
		String metalApiUrl = UriComponentsBuilder.fromHttpUrl(baseURL).path(metalAPI).toUriString();

		logger.info("URL: " + metalApiUrl);

		MetalDTO[] metalDTOList = restTemplate.getForObject(metalApiUrl, MetalDTO[].class);

		return Arrays.asList(metalDTOList);
	}

	@Override
	public List<PriceDTO> getPrices(LocalDate startDate, LocalDate endDate)
	{
		String priceApiUrl = UriComponentsBuilder.fromHttpUrl(baseURL).path(priceAPI)
				.queryParam("startdate", consumerDateFormatter.format(startDate))
				.queryParam("enddate", consumerDateFormatter.format(endDate))
				.toUriString();

		logger.info("URL: " + priceApiUrl);

		PriceDTO[] priceDTOList = restTemplate.getForObject(priceApiUrl, PriceDTO[].class);

		return Arrays.asList(priceDTOList);
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public RestTemplate getRestTemplate()
	{
		return restTemplate;
	}

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	public DateTimeFormatter getConsumerDateFormatter()
	{
		return consumerDateFormatter;
	}

	@Autowired
	public void setConsumerDateFormatter(DateTimeFormatter consumerDateFormatter)
	{
		this.consumerDateFormatter = consumerDateFormatter;
	}
}