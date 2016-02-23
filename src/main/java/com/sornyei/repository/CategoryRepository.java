package com.sornyei.repository;

import com.sornyei.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryRepository {

	private DataSource dataSource;
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category> getRootCategories() {
		String sql = "SELECT * FROM categories WHERE parentid IS NULL OR parentid=0";
		return jdbcTemplate.query(sql, new CategoryRowMapper());
	}

	public List<Category> getSubCategories(long parentId) {
		String sql = "SELECT * FROM categories WHERE parentid=:parentid";
		SqlParameterSource parameterSource = new MapSqlParameterSource("parentid", parentId);
		return jdbcTemplate.query(sql, parameterSource, new CategoryRowMapper());
	}

	public List<Category> getNotRootCategories() {
		String sql = "SELECT * FROM categories WHERE parentid IS NOT NULL AND parentid<>0";
		return jdbcTemplate.query(sql, new CategoryRowMapper());
	}

	public List<Category> findAll() {
		String sql = "SELECT * FROM categories";
		return jdbcTemplate.query(sql, new CategoryRowMapper());
	}

	public Category findById(long id) {
		String sql = "SELECT * FROM categories WHERE id=:id";
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
		return jdbcTemplate.queryForObject(sql, parameterSource, new CategoryRowMapper());
	}

	public Category update(Category category) {
		String sql = "UPDATE categories SET name=:name, parentid=:parentId WHERE id=:id";
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(category);
		if (jdbcTemplate.update(sql, parameterSource) == 1) {
			return category;
		}

		return null;
	}

	public Category create(Category category) {
		String sql = "INSERT INTO categories(name, parentid) VALUES(:name, :parentId)";
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(category);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (jdbcTemplate.update(sql, parameterSource, keyHolder) == 1) {
			category.setId(keyHolder.getKey().longValue());
			return category;
		}
		return null;

	}

	class CategoryRowMapper implements RowMapper<Category> {

		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category();
			category.setId(rs.getLong("id"));
			category.setName(rs.getString("name"));
			category.setParentId(rs.getLong("parentid"));
			return category;
		}
	}
}
