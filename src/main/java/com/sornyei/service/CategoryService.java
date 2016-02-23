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

	public List<Category> getNotRootCategories(){
		return categoryRepository.getNotRootCategories();
	}
}
