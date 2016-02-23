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
            if (new Date($scope.activity.endTime) < new Date($scope.activity.startTime)) {
                toastr.warning('Az időpontok ellentmondásosak!', {timeOut: 1000});
                return;
            } else if (compareDates(new Date($scope.activity.endTime), new Date($scope.activity.startTime))) {
                toastr.warning('Nulla perces tevékenység nem lehet', {timeOut: 1000});
                return;
            }
            $scope.activity.startTime = createDateTimeString($scope.activity.date, $scope.activity.startTime);
            $scope.activity.endTime = createDateTimeString($scope.activity.date, $scope.activity.endTime);
            $http.post('/activities', $scope.activity)
                .success(function () {
                    toastr.success('A mentés sikerült', {timeOut: 1000});
                    $location.path('/home');
                })
                .error(function (error, status, headers, config, statusText) {
                    if (status === 422) {
                        toastr.warning('Ez a tevékenyésg ütközik más tevékenységek időpontjával\nVáltoztassa meg az időpontokat', {timeOut: 1000});
                    } else {
                        toastr.error('Hiba: \n' + status, {timeOut: 1000});
                    }
                })
        }

        //cancel
        $scope.cancel = function () {
            $location.path('/home');
        }

        //change
        $scope.changed = function () {
            if (new Date($scope.activity.endTime) < new Date($scope.activity.startTime)) {
                $scope.activityForm.start.$setValidity('late', false);
                $scope.activityForm.end.$setValidity('late', false);
            } else {
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
            return dateString;
        }

        function compareDates(start, end) {
            if (start.getFullYear() !== end.getFullYear())
                return false;
            if (start.getMonth() !== end.getMonth())
                return false;
            if (start.getDate() !== end.getDate())
                return false;
            if (start.getHours() !== end.getHours())
                return false;
            if (start.getMinutes() !== end.getMinutes())
                return false;

            return true;
        }
    })