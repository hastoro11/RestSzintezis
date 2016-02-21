/**
 * Created by gaborsornyei on 20/02/16.
 */

angular.module('myApp')
    .config(function ($routeProvider) {
        $routeProvider
            .when('/category', {
                templateUrl: 'category/categories.html',
                controller: 'CategoryCtrl'
            })
            .when('/category/:catid', {
                templateUrl: 'category/category_form.html',
                controller: 'CategoryEditCtrl'
            })
            .when('/newCategory', {
                templateUrl: 'category/category_form.html',
                controller: 'CategoryEditCtrl'
            })
            .when('/subcategory', {
                templateUrl: 'category/subcategories.html',
                controller: 'SubCategoryCtrl'
            })
            .when('/subcategory/:subcatid', {
                templateUrl: 'category/subcategory_form.html',
                controller: 'SubCategoryEditCtrl'
            })
            .when('/newSubCategory', {
                templateUrl: 'category/subcategory_form.html',
                controller: 'SubCategoryEditCtrl'
            })
    })

    .controller('CategoryCtrl', function ($scope, CategorySrvc, $location) {

        CategorySrvc.getCategories()
            .success(function (data) {
                $scope.categories = data;

            })


        $scope.addNew = function () {
            $location.path('/newCategory');
        }


    })

    .controller('CategoryEditCtrl', function ($scope, $routeParams, $location, CategorySrvc) {
        if ($routeParams.catid) {
            $scope.edit = true;
            CategorySrvc.getCategoryById($routeParams.catid)
                .success(function (data) {
                    $scope.category = data;
                })

        } else {
            $scope.edit = false;
            $scope.category = {id: 0}
        }

        $scope.cancel = function () {
            $location.path('/category');
        }

        $scope.submit = function () {
            if ($scope.category.id == 0) {

                CategorySrvc.addCategory($scope.category)
                    .success(function () {
                        toastr.success('A mentés sikerült', '', {timeOut: 1000});
                    })
                    .error(function () {
                        toastr.error('A mentés nem sikerült', '', {timeOut: 1000});
                    })
            } else {
                CategorySrvc.updateCategory($scope.category)
                    .success(function () {
                        toastr.success('A mentés sikerült', '', {timeOut: 1000});
                    })
                    .error(function () {
                        toastr.error('A mentés nem sikerült', '', {timeOut: 1000});
                    })
            }

            $location.path('/category/')
        }

        $scope.delete = function (id) {
            console.log('in delete ...')
            CategorySrvc.deleteCategory(id)
                .success(function () {
                    toastr.success('A törlés sikerült', '', {timeOut: 1000});
                    $location.path('/category/')
                })
                .error(function () {
                    toastr.error('A törlés nem sikerült', '', {timeOut: 1000});
                    $location.path('/category/')
                })
        }


    })
    .controller('SubCategoryCtrl', function ($scope, $rootScope, CategorySrvc, $location) {
        CategorySrvc.getSubCategories()
            .success(function (data) {
                $scope.subcategories = data;
                CategorySrvc.getCategories()
                    .success(function (data) {
                        $scope.categories = data;
                        $scope.subcategories.forEach(function (subcat) {
                            for(var i=0;i<$scope.categories.length;i++){
                                if(subcat.catId==$scope.categories[i].id){
                                    subcat.catName=$scope.categories[i].name;
                                    break;
                                }
                            }
                        })
                    })
            })

        $scope.addNew = function () {
            $location.path('/newSubCategory');
        }
    })
    .controller('SubCategoryEditCtrl', function ($scope, $rootScope, $routeParams, $location, CategorySrvc) {
        //$scope.categories = $scope.allcategories;
        if ($routeParams.subcatid) {
            $scope.edit = true;
            CategorySrvc.getSubCategoryById($routeParams.subcatid)
                .success(function (data) {
                    $scope.subcategory = data;
                    CategorySrvc.getCategories()
                        .success(function (data) {
                            $scope.categories = data;
                        })
                })
        } else {
            $scope.edit = false;
            $scope.subcategory = {id: 0}
            CategorySrvc.getCategories()
                .success(function (data) {
                    $scope.categories = data;
                })
        }

        $scope.cancel = function () {
            $location.path('/subcategory');
        }

        $scope.submit = function () {
            if ($scope.subcategory.id == 0) {
                CategorySrvc.addSubCategory($scope.subcategory);
            } else {
                CategorySrvc.updateSubCategory($scope.subcategory);
            }

            $location.path('/subcategory/')
        }

        $scope.delete = function (id) {
            CategorySrvc.deleteSubCategory(id)
                .success(function () {
                    toastr.success('A törlés sikerült', {timeOut: 1000});
                    $location.path('/subcategory/')
                })
        }

    })