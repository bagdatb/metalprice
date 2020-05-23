package gs.testproject.metalprices.db.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import gs.testproject.metalprices.db.entity.PriceEntity;
import gs.testproject.metalprices.engine.AppInitializer;

@SpringBootTest
public class PriceDAOTest
{
	@MockBean
	private AppInitializer appInitializer;

	@Autowired
	private PriceDAO priceDAO;

	@Test
	public void findAndBatchInsert_happyPath()
	{
		long metalID = 2;

		List<PriceEntity> prices = priceDAO.findByMetalID(metalID, 10);
		assertThat(prices).hasSize(3);

		PriceEntity priceEntity1 = new PriceEntity(
				0, LocalDate.of(2020, 03, 22), metalID, new BigDecimal("2000.00"));
		PriceEntity priceEntity2 = new PriceEntity(
				0, LocalDate.of(2020, 03, 23), metalID, new BigDecimal("2200.00"));

		List<PriceEntity> priceEntities = Arrays.asList(priceEntity1, priceEntity2);

		priceDAO.batchInsert(priceEntities);

		prices = priceDAO.findByMetalID(metalID, 10);
		assertThat(prices).hasSize(5);
	}
}