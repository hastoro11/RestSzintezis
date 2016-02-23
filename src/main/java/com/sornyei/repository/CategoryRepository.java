package com.sornyei.repository;

import com.sornyei.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 22..
 */
@Repository
public class CategoryRepository {

	private DataSource dataSource;
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category> getRootCategories() {
		String sql = "SELECT * FROM categories WHERE parentid IS NULL";
		return jdbcTemplate.query(sql, new CategoryRowMapper());
	}

	public List<Category> getSubCategories(long parentId) {
		String sql = "SELECT * FROM categories WHERE parentid=:parentid";
		SqlParameterSource parameterSource = new MapSqlParameterSource("parentid", parentId);
		return jdbcTemplate.query(sql, parameterSource, new CategoryRowMapper());
	}

	public List<Category> getNotRootCategories(){
		String sql = "SELECT * FROM categories WHERE parentid IS NOT NULL";
		return jdbcTemplate.query(sql, new CategoryRowMapper());
	}

	class CategoryRowMapper implements RowMapper<Category> {

		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category();
			category.setId(rs.getLong("id"));
			category.setName(rs.getString("name"));
			return category;
		}
	}
}
