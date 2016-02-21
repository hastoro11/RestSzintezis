package com.sornyei.service;

import com.sornyei.model.Category;
import com.sornyei.repository.CategoryRepository;
import com.sornyei.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 20..
 */
@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;

	public List<Category> getCategories() {
		return repository.getCategories();
	}

	public Category getCategoryById(long id) {
		return repository.getCategoryById(id);
	}

	public Category create(Category category) {
		return repository.create(category);
	}

	public Category update(Category category) {
		return repository.update(category);
	}

	public boolean delete(long id) {
		return repository.delete(id);
	}
}
