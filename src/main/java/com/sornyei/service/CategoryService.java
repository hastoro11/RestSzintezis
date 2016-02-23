package com.sornyei.service;

import com.sornyei.model.Category;
import com.sornyei.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 23..
 */
@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> getNotRootCategories() {
		return categoryRepository.getNotRootCategories();
	}

	public List<Category> getRootCategories(){
		return categoryRepository.getRootCategories();
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(long id) {
		return categoryRepository.findById(id);
	}

	public Category update(Category category) {
		return categoryRepository.update(category);
	}

	public Category create(Category category) {
		return categoryRepository.create(category);
	}
}
