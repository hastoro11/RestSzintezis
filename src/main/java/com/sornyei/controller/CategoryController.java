package com.sornyei.controller;

import com.sornyei.model.Category;
import com.sornyei.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 23..
 */
@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/notrootcategories", method = RequestMethod.GET)
	public List<Category> getNotRootCategories() {
		return categoryService.getNotRootCategories();
	}
}
