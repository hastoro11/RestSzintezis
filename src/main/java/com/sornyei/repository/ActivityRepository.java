package com.sornyei.repository;

import com.sornyei.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 22..
 */
@Repository
public class ActivityRepository {

	private DataSource dataSource;
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Activity create(Activity activity) throws DataAccessException {
		String sql = "INSERT INTO activity(start, end, name, category_id) " +
				"VALUES(:startTime, :endTime, :name, :categoryId)";
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(activity);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		if (jdbcTemplate.update(sql, parameterSource, keyHolder) == 1) {
			activity.setId(keyHolder.getKey().intValue());
			return activity;
		} else
			return null;
	}

	public List<Activity> findAll() throws DataAccessException {
		String sql = "SELECT * FROM activity";
		return jdbcTemplate.query(sql, new ActivityRowMapper());
	}

	public Activity findById(long id) throws DataAccessException {
		String sql = "SELECT * FROM activity WHERE id=:id";
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
		return jdbcTemplate.queryForObject(sql, parameterSource, new ActivityRowMapper());
	}

	public List<Activity> findByCategoryId(long categoryId) {
		String sql = "SELECT * FROM activity WHERE category_id=:categoryId";
		SqlParameterSource parameterSource = new MapSqlParameterSource("categoryId", categoryId);
		return jdbcTemplate.query(sql, parameterSource, new ActivityRowMapper());
	}


	class ActivityRowMapper implements RowMapper<Activity> {

		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity activity = new Activity();
			activity.setId(rs.getLong("id"));
			activity.setStartTime(rs.getString("start"));
			activity.setEndTime(rs.getString("end"));
			activity.setName(rs.getString("name"));
			activity.setCategoryId(rs.getLong("category_id"));

			return activity;
		}
	}

}
