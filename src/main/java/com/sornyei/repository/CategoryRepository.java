package com.sornyei.repository;

import com.sornyei.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 20..
 */
@Repository
public class CategoryRepository {
	private List<Category> categories;

	public CategoryRepository() {
		categories = new ArrayList<Category>();
		categories.add(new Category(1, "Munka"));
		categories.add(new Category(2, "Otthon"));
		categories.add(new Category(3, "Szabadidő"));
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public Category getCategoryById(long id) {
		for (Category cat : categories) {
			if (cat.getId() == id) {
				return cat;
			}
		}

		return null;
	}

	public Category create(Category category) {
		category.setId(categories.size() + 1);
		categories.add(category);

		return category;
	}

	public Category update(Category category) {
		for (Category cat : categories) {
			if (cat.getId() == category.getId()) {
				cat.setName(category.getName());
				return category;
			}
		}

		return null;
	}

	public boolean delete(long id) {
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getId() == id) {
				categories.remove(i);
				return true;
			}
		}

		return false;
	}
}
