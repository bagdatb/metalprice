package gs.testproject.metalprices.engine;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gs.testproject.metalprices.db.dao.MetalDAO;
import gs.testproject.metalprices.db.dao.PriceDAO;
import gs.testproject.metalprices.db.entity.MetalEntity;
import gs.testproject.metalprices.db.entity.PriceEntity;

/**
 * Service to manipulate with Metals ans Prices.
 */
@Service
@Transactional
public class MetalPriceService
{
	private MetalDAO metalDAO;
	private PriceDAO priceDAO;

	/**
	 * Find all Metal entities.
	 *
	 * @return
	 */
	public List<MetalEntity> getMetals()
	{
		return getMetalDAO().findAll();
	}

	/**
	 * Get Price entities for the requested Metal and order by price date in descending order.
	 *
	 * @param metalID
	 * @param limit The number of records that should be retrieved.
	 * @return
	 */
	public List<PriceEntity> getPrices(long metalID, int limit)
	{
		return getPriceDAO().findByMetalID(metalID, limit);
	}

	/**
	 * Checks whether the given date is already exists.
	 *
	 * @param priceDate
	 * @return
	 */
	public boolean idDateLoaded(LocalDate priceDate)
	{
		return getPriceDAO().isPriceDateExists(priceDate);
	}

	/**
	 * Save of Metal entity list.
	 *
	 * @param metalEntityList
	 */
	public void insertMetalList(List<MetalEntity> metalEntityList)
	{
		getMetalDAO().batchInsert(metalEntityList);
	}

	/**
	 * Save of Price entity list.
	 *
	 * @param priceEntityList
	 */
	public void insertPriceList(List<PriceEntity> priceEntityList)
	{
		getPriceDAO().batchInsert(priceEntityList);
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public MetalDAO getMetalDAO()
	{
		return metalDAO;
	}

	@Autowired
	public void setMetalDAO(MetalDAO metalDAO)
	{
		this.metalDAO = metalDAO;
	}

	public PriceDAO getPriceDAO()
	{
		return priceDAO;
	}

	@Autowired
	public void setPriceDAO(PriceDAO priceDAO)
	{
		this.priceDAO = priceDAO;
	}
}