package com.sornyei.service;

import com.sornyei.model.SubCategory;
import com.sornyei.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */
@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	public List<SubCategory> getSubCategories() {
		return subCategoryRepository.getSubCategories();
	}

	public SubCategory getSubCategoryById(long id){
		return subCategoryRepository.getSubCategoryById(id);
	}

	public List<SubCategory> getSubCategoriesByCatId(long catId) {
		return subCategoryRepository.getSubCategoriesByCatId(catId);
	}

	public SubCategory create(SubCategory subCategory) {
		return subCategoryRepository.create(subCategory);
	}

	public SubCategory update(SubCategory subCategory) {
		return subCategoryRepository.update(subCategory);
	}

	public boolean delete(long id) {
		return subCategoryRepository.delete(id);
	}

}
