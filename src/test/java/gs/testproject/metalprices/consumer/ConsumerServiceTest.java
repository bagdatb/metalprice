package gs.testproject.metalprices.consumer;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import gs.testproject.metalprices.consumer.dto.MetalDTO;
import gs.testproject.metalprices.consumer.dto.PriceDTO;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTest
{
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private DefaultConsumerService consumerService;

	@BeforeEach
	public void init()
	{
		ReflectionTestUtils.setField(consumerService, "baseURL", "http://consumerhost");
	}

	@Test
	public void metals_getMetals()
	{
		String resultURL = "http://consumerhost/metals";

		MetalDTO metalDTO = new MetalDTO();
		metalDTO.setId(1);

		ReflectionTestUtils.setField(consumerService, "metalAPI", "/metals");

		when(restTemplate.getForObject(resultURL, MetalDTO[].class))
				.thenReturn(new MetalDTO[] { metalDTO });

		List<MetalDTO> availableMetals = consumerService.getAvailableMetals();

		Assertions.assertEquals(availableMetals.size(), 1);
		Assertions.assertEquals(availableMetals.get(0).getId(), metalDTO.getId());
	}

	@Test
	public void prices_getPrices()
	{
		String resultURL = "http://consumerhost/prices?startdate=2020-01-01&enddate=2020-01-01";

		PriceDTO priceDTO = new PriceDTO();
		priceDTO.setMetalID(1);

		ReflectionTestUtils.setField(consumerService, "priceAPI", "/prices");

		LocalDate date = LocalDate.of(2020, 01, 01);

		when(restTemplate.getForObject(resultURL, PriceDTO[].class))
				.thenReturn(new PriceDTO[] { priceDTO });

		consumerService.setConsumerDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<PriceDTO> prices = consumerService.getPrices(date, date);

		Assertions.assertEquals(prices.size(), 1);
		Assertions.assertEquals(prices.get(0).getMetalID(), priceDTO.getMetalID());
	}
}