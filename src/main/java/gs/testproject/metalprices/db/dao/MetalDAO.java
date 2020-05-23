package gs.testproject.metalprices.db.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gs.testproject.metalprices.db.entity.MetalEntity;

/**
 * DAO class to work with Metal entities.
 */
@Component
public class MetalDAO
{
	private JdbcTemplate jdbcTemplate;

	protected String QUERY_SELECT = "SELECT id, name FROM metal ORDER BY id";
	protected String QUERY_INSERT = "INSERT INTO metal (id, name) VALUES (?, ?)";

	/**
	 * Find all Metal entities.
	 *
	 * @return
	 */
	public List<MetalEntity> findAll()
	{
		return getJdbcTemplate().query(
				QUERY_SELECT,
				(rs, rowNum) ->
						new MetalEntity(
								rs.getLong("id"),
								rs.getString("name")
						)
		);
	}

	/**
	 * Batch Insert of Metal entities.
	 *
	 * @param metalEntities
	 * @return
	 */
	public int[] batchInsert(final List<MetalEntity> metalEntities)
	{
		return getJdbcTemplate().batchUpdate(
				QUERY_INSERT,
				new BatchPreparedStatementSetter()
				{
					public void setValues(PreparedStatement ps, int i) throws SQLException
					{
						ps.setLong(1, metalEntities.get(i).getId());
						ps.setString(2, metalEntities.get(i).getName());
					}

					public int getBatchSize()
					{
						return metalEntities.size();
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