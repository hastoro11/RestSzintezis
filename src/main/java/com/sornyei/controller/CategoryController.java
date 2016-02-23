package com.sornyei.controller;

import com.sornyei.model.Category;
import com.sornyei.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/rootcategories", method = RequestMethod.GET)
	public List<Category> getRootCategories() {
		return categoryService.getRootCategories();
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> findAll() {
		return new ResponseEntity<List<Category>>(categoryService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById(@PathVariable("id") long id) {
		return new ResponseEntity<Category>(categoryService.findById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.PUT)
	public ResponseEntity<Category> update(@RequestBody Category category) {
		Category cat = categoryService.update(category);
		if (cat == null) {
			return new ResponseEntity<Category>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public ResponseEntity<Category> create(@RequestBody Category category) {
		Category cat = categoryService.create(category);
		if (cat == null) {
			return new ResponseEntity<Category>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}
}
