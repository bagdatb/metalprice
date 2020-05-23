package gs.testproject.metalprices.db.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import gs.testproject.metalprices.db.entity.MetalEntity;
import gs.testproject.metalprices.engine.AppInitializer;

@SpringBootTest
public class MetalDAOTest
{
	@MockBean
	private AppInitializer appInitializer;

	@Autowired
	private MetalDAO metalDAO;

	@Test
	public void findAllAndBatchInsert_happyPath()
	{
		List<MetalEntity> metals = metalDAO.findAll();
		assertThat(metals).hasSize(2);

		MetalEntity metalEntity1 = new MetalEntity(3, "Bronze");
		MetalEntity metalEntity2 = new MetalEntity(4, "Platinum");

		List<MetalEntity> metalEntities = Arrays.asList(metalEntity1, metalEntity2);

		metalDAO.batchInsert(metalEntities);

		metals = metalDAO.findAll();
		assertThat(metals).hasSize(4);
	}
}