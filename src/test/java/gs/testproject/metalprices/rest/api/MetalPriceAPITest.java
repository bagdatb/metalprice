package gs.testproject.metalprices.rest.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import gs.testproject.metalprices.db.entity.MetalEntity;
import gs.testproject.metalprices.engine.MetalPriceService;
import gs.testproject.metalprices.engine.transformer.RestMetalTransformer;
import gs.testproject.metalprices.engine.transformer.RestPriceTransformer;
import gs.testproject.metalprices.rest.dto.MetalDTO;
import gs.testproject.metalprices.rest.dto.PriceDTO;

@WebMvcTest(MetalPriceAPI.class)
public class MetalPriceAPITest
{
	@Autowired private MockMvc mvc;

	@MockBean private MetalPriceService metalPriceService;
	@MockBean private RestMetalTransformer restMetalTransformer;
	@MockBean private RestPriceTransformer restPriceTransformer;

	@Test
	public void test_getAvailableMetals() throws Exception
	{
		MetalDTO metalDTO = new MetalDTO(1, "gold");
		List<MetalDTO> allMetals = Arrays.asList(metalDTO);

		given(restMetalTransformer.transformToDTOList(anyList())).willReturn(allMetals);

		mvc.perform(get("/api/metals")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is(metalDTO.getName())));
	}

	@Test
	public void test_getPrices() throws Exception
	{
		MetalEntity metalGold = new MetalEntity(1, null);
		MetalEntity metalSilver = new MetalEntity(2, null);

		List<MetalEntity> allMetals = Arrays.asList(metalGold, metalSilver);
		List<PriceDTO> allPrices = Arrays.asList(new PriceDTO());

		given(metalPriceService.getMetals()).willReturn(allMetals);
		given(restPriceTransformer.transformToDTOList(anyList())).willReturn(allPrices);

		mvc.perform(get("/api/prices")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].metal", is(1)))
				.andExpect(jsonPath("$[1].metal", is(2)))
				.andExpect(jsonPath("$[0].prices", hasSize(1)))
				.andExpect(jsonPath("$[1].prices", hasSize(1)));
	}
}