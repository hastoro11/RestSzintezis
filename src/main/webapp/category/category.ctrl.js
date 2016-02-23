/**
 * Created by gaborsornyei on 16. 02. 23..
 */
angular.module('myApp.category', [])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/categories', {
                templateUrl: 'category/category.list.html',
                controller: 'CategoryCtrl'
            })
            .when('/newcategory', {
                templateUrl: 'category/category.form.html',
                controller: 'CategoryEditCtrl'
            })
            .when('/categories/:id', {
                templateUrl: 'category/category.form.html',
                controller: 'CategoryEditCtrl'
            })
            .otherwise({
                redirectTo: '/home'
            })

    })

    //List
    .controller('CategoryCtrl', function ($scope, $location, CategorySrvc) {
        CategorySrvc.getAllCategories()
            .success(function (data) {
                $scope.categories = data;
            })

        $scope.edit = function (categoryId) {
            $location.path('/categories/' + categoryId);
        }

        $scope.newCategory = function () {
            $location.path('/newcategory');
        }
    })

    //Edit
    .controller('CategoryEditCtrl', function ($scope, $location, $routeParams, $q, CategorySrvc) {
        if ($routeParams.id) {
            var category = CategorySrvc.getCategoryById($routeParams.id);
            var categories = CategorySrvc.getRootCategories();
            $q.all([category, categories])
                .then(function (value) {
                    console.log(value);
                    $scope.category = value[0].data;
                    $scope.categories = value[1].data;
                })
        } else {
            CategorySrvc.getRootCategories()
                .success(function (data) {
                    $scope.categories = data;
                })
            $scope.category = {id: 0}
        }

        //cancel
        $scope.cancel = function () {
            $location.path('/categories');
        }

        //submit
        $scope.submit = function () {
            if ($scope.category.id == 0) {
                CategorySrvc.create($scope.category)
                    .success(function () {
                        toastr.success('A mentés sikerült', {timeOut: 1000});
                    })
                    .error(function (error, status) {

                    });
            } else {
                CategorySrvc.update($scope.category)
                    .success(function () {
                        toastr.success('A mentés sikerült', {timeOut: 1000});
                    })
                    .error(function (error, status) {

                    });
            }
            $location.path('/categories');
        }
    })

    //Service
    .factory('CategorySrvc', function ($http) {
        var factory = {};

        factory.getAllCategories = function () {
            return $http.get('/categories');
        }

        factory.getRootCategories = function () {
            return $http.get('/rootcategories');
        }

        factory.getCategoryById = function (categoryId) {
            return $http.get('/categories/' + categoryId);
        }

        factory.update = function (category) {
            return $http.put('/categories', category);
        }

        factory.create = function (category) {
            return $http.post('/categories', category);
        }

        return factory;
    })
