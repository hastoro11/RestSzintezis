/**
 * Created by gaborsornyei on 16. 02. 22..
 */
angular.module('myApp')
    .controller('ReportCtrl', function ($scope, $http) {
        $scope.showReport = false;
        $scope.totalTime = true;

        //Datepicker
        $scope.date = new Date();
        $scope.opened = false;
        $scope.open = function () {
            $scope.opened = true;
        };

        //select
        $scope.select = function () {
            var day = new Date($scope.date);
            var dateString = day.getFullYear() + '-' + ((day.getMonth() + 1) < 10 ? '0' + (day.getMonth() + 1) : (day.getMonth() + 1)) + '-' + day.getDate();
            $http.get('/report/' + dateString)
                .success(function (data) {
                    $scope.report = data;
                    $scope.showReport = true;
                    $scope.totalTime = data.reduce(function (prev, curr) {
                        return prev + curr.totalDuration;
                    }, 0);
                })

        }

    }
)