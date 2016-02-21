package com.sornyei.controller;

import com.sornyei.model.Category;
import com.sornyei.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 20..
 */
@RestController
public class CategoryController {

	@Autowired
	private CategoryService service;

	final static Logger logger = Logger.getLogger(CategoryController.class.getName().toUpperCase());

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() {
		logger.info("Controller entered");
		return new ResponseEntity<List<Category>>(service.getCategories(), HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") long id) {
		Category category = service.getCategoryById(id);
		if (category != null)
			return new ResponseEntity<Category>(category, HttpStatus.OK);

		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public ResponseEntity<Category> create(@RequestBody Category category) {
		return new ResponseEntity<Category>(service.create(category), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.PUT)
	public ResponseEntity<Category> update(@RequestBody Category category) {
		Category cat = service.update(category);
		if (cat != null)
			return new ResponseEntity<Category>(cat, HttpStatus.OK);
		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
		return new ResponseEntity<Boolean>(service.delete(id), HttpStatus.OK);
	}

}
