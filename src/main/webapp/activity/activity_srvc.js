/**
 * Created by gaborsornyei on 16. 02. 20..
 */

angular.module('myApp')
    .factory('ActivitySrvc', function ($http) {
        var factory = {};

        factory.getActivities = function (date) {
            return $http.get('/activities/' + date);
        }

        factory.addNewActivity = function (activity) {
            return $http.post('/activities', activity);
        }

        return factory;
    })