package com.sornyei.repository;

import com.sornyei.model.SubCategory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaborsornyei on 16. 02. 21..
 */
@Repository
public class SubCategoryRepository {

	private List<SubCategory> subCategories;

	public SubCategoryRepository() {
		subCategories = new ArrayList<SubCategory>();
		subCategories.add(new SubCategory(1, 2, "Játék"));
		subCategories.add(new SubCategory(2, 2, "TV nézés"));
		subCategories.add(new SubCategory(3, 3, "Sport"));
		subCategories.add(new SubCategory(4, 3, "Hobbi"));
		subCategories.add(new SubCategory(5, 1, "Értekezlet"));
	}

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	public SubCategory getSubCategoryById(long id) {
		for (SubCategory subCategory : subCategories) {
			if (subCategory.getId() == id) {
				return subCategory;
			}
		}
		return null;
	}

	public List<SubCategory> getSubCategoriesByCatId(long catId) {
		List<SubCategory> result = new ArrayList<SubCategory>();
		for (SubCategory subCategory : subCategories) {
			if (subCategory.getCatId() == catId) {
				result.add(subCategory);
			}
		}

		return result;
	}

	public SubCategory create(SubCategory subCategory) {
		subCategory.setId(subCategories.size() + 1);
		subCategories.add(subCategory);

		return subCategory;
	}

	public SubCategory update(SubCategory subCategory) {
		for (SubCategory subcat : subCategories) {
			if (subcat.getId() == subCategory.getId()) {
				subcat.setCatId(subCategory.getCatId());
				subcat.setName(subCategory.getName());

				return subcat;
			}
		}

		return null;
	}

	public boolean delete(long id) {
		for (int i = 0; i < subCategories.size(); i++) {
			SubCategory currSubCat = subCategories.get(i);
			if (currSubCat.getId() == id) {
				subCategories.remove(i);
				return true;
			}
		}
		return false;
	}
}
