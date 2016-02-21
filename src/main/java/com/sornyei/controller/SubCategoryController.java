package com.sornyei.controller;

import com.sornyei.model.SubCategory;
import com.sornyei.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */
@RestController
public class SubCategoryController {

	@Autowired
	private SubCategoryService service;

	@RequestMapping(value = "/subcategories", method = RequestMethod.GET)
	public ResponseEntity<List<SubCategory>> getSubCategories() {
		return new ResponseEntity<List<SubCategory>>(service.getSubCategories(), HttpStatus.OK);
	}

	@RequestMapping(value = "/subcategories/{id}", method = RequestMethod.GET)
	public ResponseEntity<SubCategory> getSubcategoryById(@PathVariable("id") long id) {
		return new ResponseEntity<SubCategory>(service.getSubCategoryById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/subcategories", method = RequestMethod.POST)
	public ResponseEntity<SubCategory> create(@RequestBody SubCategory subCategory) {
		return new ResponseEntity<SubCategory>(service.create(subCategory), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/subcategories", method = RequestMethod.PUT)
	public ResponseEntity<SubCategory> update(@RequestBody SubCategory subCategory) {
		return new ResponseEntity<SubCategory>(service.update(subCategory), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/subcategories/{id}", method = RequestMethod.DELETE)
	private ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
		return new ResponseEntity<Boolean>(service.delete(id), HttpStatus.OK);
	}
}
