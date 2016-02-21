/**
 * Created by gaborsornyei on 20/02/16.
 */
angular.module('myApp')
    .factory('CategorySrvc', function ($http) {
        var factory = {}

        factory.getCategories = function () {
            return $http.get('/categories');
        }

        factory.getCategoryById = function (id) {
            return $http.get('/categories/' + id);
        }

        factory.addCategory = function (category) {
            return $http.post('/categories', category);
        }

        factory.updateCategory = function (category) {
            return $http.put('/categories', category);
        }

        factory.deleteCategory = function (id) {
            return $http.delete('/categories/' + id);
        }

        factory.getSubCategories = function () {
            return $http.get('/subcategories');
        }

        factory.getSubCategoryiesByCatId = function (catId) {
            var subcats = subcategories.filter(function (subcat) {
                return subcat.catId == catId;
            })

            return subcats;
        }

        factory.getSubCategoryById = function (id) {
            return $http.get('/subcategories/' + id);
        }


        factory.addSubCategory = function (subcategory) {
            return $http.post('/subcategories', subcategory);
        }

        factory.updateSubCategory = function (subcategory) {
            return $http.put('/subcategories', subcategory);
        }

        factory.deleteSubCategory = function (id) {
            return $http.delete('/subcategories/' + id);
        }

        return factory;
    });
