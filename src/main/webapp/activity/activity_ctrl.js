/**
 * Created by gaborsornyei on 16. 02. 20..
 */
angular.module('myApp')
    .config(function ($routeProvider) {
        $routeProvider
            .when('/newactivity', {
                templateUrl: 'activity/activity_form.html',
                controller: 'ActivityEditCtrl'
            })
            .when('/activities', {
                templateUrl: 'activity/activities.html',
                controller: 'ActivityCtrl'
            })
            .when('/activities/:date', {
                templateUrl: 'activity/activities_list.html',
                controller: 'ActivityListCtrl'
            })
    })
    .controller('ActivityListCtrl', function ($scope, $routeParams, $location, ActivitySrvc) {
        if (!$routeParams.date) {
            toastr.error('Hiba történt', {timeOut: 1000});
            $location.path('/home');
        }


        var date = $routeParams.date;
        ActivitySrvc.getActivities(date)
            .success(function (data) {
                if (data.length == 0) {
                    $scope.nodata = true;
                } else {
                    $scope.nodata = false;
                    $scope.date = $routeParams.date;
                    $scope.activities = data;
                    $scope.totaltime = data.reduce(function (prev, curr) {
                        return prev + curr.duration;
                    }, 0);
                }
            })


        //Datepicker
        $scope.popup = {opened: false};

        $scope.open = function () {
            $scope.popup.opened = true;
        }
    })

    .controller('ActivityCtrl', function ($scope, $location) {
        $scope.submit = function () {
            console.log($scope.date);
            var date = $scope.date.getFullYear() + '-' + ($scope.date.getMonth() + 1) + '-' + $scope.date.getDate();
            $location.path('/activities/' + date);
        }

        //Datepicker
        $scope.popup = {opened: false};

        $scope.open = function () {
            $scope.popup.opened = true;
        }
    })

    //Edit
    .controller('ActivityEditCtrl', function ($scope, $routeParams, $location, CategorySrvc, ActivitySrvc) {
        $scope.activity = {};
        function checkTime() {
            if ($scope.activity.endTime <= $scope.activity.startTime) {
                $scope.activityForm.end.$setValidity('late', false);
                $scope.activityForm.start.$setValidity('late', false);
            } else {
                $scope.activityForm.end.$setValidity('late', true);
                $scope.activityForm.start.$setValidity('late', true);
            }
        }

        $scope.$watch('activity.startTime', function () {
            checkTime();
        })

        $scope.$watch('activity.endTime', function () {
            checkTime();
        })

        if ($routeParams.id) {
            //edit
        } else {
            $scope.activity.id = 0;
            $scope.activity.date = new Date();
        }

        CategorySrvc.getSubCategories()
            .success(function (data) {
                $scope.subcategories = data;
            })


        //submit
        $scope.submit = function () {

            var activity = angular.copy($scope.activity);
            var date = new Date(activity.date);
            var start = new Date(activity.startTime);
            var end = new Date(activity.endTime);

            activity.date = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
            activity.startTime = start.getHours() + ':' + start.getMinutes();
            activity.endTime = end.getHours() + ':' + end.getMinutes();

            if (activity.id == 0) {

                ActivitySrvc.addNewActivity(activity)
                    .then(function (data) {
                        toastr.success('A rögzítés sikerült', {timeOut: 1000});
                        $location.path('/home');
                    }, function (response) {
                        if (response.status === 422) {
                            toastr.error('Ez a tevékenység átfedésben van egy korábbival. Kérlek, módosítsd!', {timeOut: 1000});
                        }
                    });
            }
        }

        //cancel
        $scope.cancel = function () {
            $location.path('/home');
        }

        //Datepicker
        $scope.popup = {opened: false};

        $scope.open = function () {
            $scope.popup.opened = true;
        }
    })