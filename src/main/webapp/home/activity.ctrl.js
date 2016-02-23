/**
 * Created by gaborsornyei on 16. 02. 23..
 */
angular.module('myApp')
    .controller('ActivityCtrl', function ($scope, $http, $location) {


        $scope.activity = {
            date: new Date(),
            startTime: new Date(),
            endTime: new Date()
        };

        $http.get('/notrootcategories')
            .success(function (data) {
                $scope.categories = data;
            })

        //submit
        $scope.submit = function () {

            $scope.activity.startTime = createDateTimeString($scope.activity.date, $scope.activity.startTime);
            $scope.activity.endTime = createDateTimeString($scope.activity.date, $scope.activity.endTime);
            $http.post('/activities', $scope.activity)
                .success(function () {
                    toastr.success('A mentés sikerült', {timeOut: 1000});
                    $location.path('/home');
                })
                .error(function (data) {
                    toastr.error('Hiba: ' + data.status, {timeOut: 1000});
                })
        }

        //cancel
        $scope.cancel = function () {
            $location.path('/home');
        }

        //change
        $scope.changed = function () {
            console.log(new Date($scope.activity.endTime) <= new Date($scope.activity.startTime))
            if (new Date($scope.activity.endTime) <= new Date($scope.activity.startTime)) {
                $scope.activityForm.start.$setValidity('late', false);
                $scope.activityForm.end.$setValidity('late', false);
            }else{
                $scope.activityForm.start.$setValidity('late', true);
                $scope.activityForm.end.$setValidity('late', true);
            }
        }

        //Datepicker
        $scope.opened = false;
        $scope.open = function () {
            $scope.opened = true;
        };

        function createDateTimeString(date, time) {
            var day = new Date(date);
            var time = new Date(time);
            dateString = day.getFullYear() + '.' + ((day.getMonth() + 1) < 10 ? '0' + (day.getMonth() + 1) : (day.getMonth() + 1)) + '.' + day.getDate() + '. '
                + time.getHours() + ':' + time.getMinutes();
            console.log(dateString);
            return dateString;
        }
    })