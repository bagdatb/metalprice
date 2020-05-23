package gs.testproject.metalprices.db.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gs.testproject.metalprices.db.entity.PriceEntity;

/**
 * DAO class to work with Price entities.
 */
@Component
public class PriceDAO
{
	private JdbcTemplate jdbcTemplate;

	protected String QUERY_SELECT = "SELECT id, price_date, metal_id, value "
			+ "FROM price WHERE metal_id = ? ORDER BY price_date DESC LIMIT ?";

	protected String QUERY_SELECT_DATE = "SELECT count(id) FROM price WHERE price_date = ?";

	protected String QUERY_INSERT = "INSERT INTO price (price_date, metal_id, value) VALUES (?, ?, ?)";

	/**
	 * Get Price entities for the requested Metal and order by price date in descending order.
	 *
	 * @param metalID
	 * @param limit The number of records that should be retrieved.
	 * @return
	 */
	public List<PriceEntity> findByMetalID(long metalID, int limit)
	{
		return getJdbcTemplate().query(
				QUERY_SELECT,
				preparedStatement -> {
					preparedStatement.setLong(1, metalID);
					preparedStatement.setInt(2, limit);
				},
				(rs, rowNum) ->
						new PriceEntity(
								rs.getLong("id"),
								rs.getDate("price_date").toLocalDate(),
								rs.getLong("metal_id"),
								rs.getBigDecimal("value")
						)
		);
	}

	/**
	 * Checks whether the given date is already exists.
	 *
	 * @param priceDate
	 * @return
	 */
	public boolean isPriceDateExists(LocalDate priceDate)
	{
		Integer count = getJdbcTemplate().queryForObject(
				QUERY_SELECT_DATE, Integer.class, priceDate
		);
		return count != null && count > 0;
	}

	/**
	 * Batch Insert of Price entities.
	 *
	 * @param priceEntities
	 * @return
	 */
	public int[] batchInsert(final List<PriceEntity> priceEntities)
	{
		return getJdbcTemplate().batchUpdate(
				QUERY_INSERT,
				new BatchPreparedStatementSetter()
				{
					public void setValues(PreparedStatement ps, int i) throws SQLException
					{
						ps.setObject(1, priceEntities.get(i).getPriceDate());
						ps.setLong(2, priceEntities.get(i).getMetalID());
						ps.setBigDecimal(3, priceEntities.get(i).getValue());
					}

					public int getBatchSize()
					{
						return priceEntities.size();
					}
				});
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
}