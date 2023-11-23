var app = angular.module('consumption', []);

    app.controller("ConsumptionController", function ($scope, $http, $filter) {
    $scope.consumptionsList = [];

    $scope.successGetConsumptionsCallback = function (response) {
        $scope.consumptionList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetConsumptionsCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of consumption notes";
    };

    $scope.getConsumptions = function () {
        $http.get('/consumptionlist').then($scope.successGetConsumptionsCallback, $scope.errorGetConsumptionsCallback);
    };

    $scope.successDeleteSchoolCallback = function (response) {
        for (var i = 0; i < $scope.consumptionList.length; i++) {
            var j = $scope.consumptionList[i];
            if (j.date === $scope.deletedDate) {
                $scope.consumptionList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteSchoolCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to delete consumption note; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteConsumption = function (date) {
        $scope.deletedDate = date;
        $http.delete('/consumption/' + date).then($scope.successDeleteSchoolCallback, $scope.errorDeleteSchoolCallback);
    };


    $scope.successGetConsumptionCallback = function (response) {
        $scope.consumptionsList.splice(0, 0, response.data);
        $scope.errormessage="";
        $scope.getConsumptions();
    };

    $scope.errorGetConsumptionCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on note date "+ $scope.inputdate;
    };

    $scope.successAddConsumptionCallback = function (response) {
        var formattedDate = $filter('date')($scope.inputdate, 'yyyy-MM-dd');
        $http.get('/consumption/' + formattedDate).then($scope.successGetConsumptionCallback, $scope.errorGetConsumptionCallback);
        $scope.errormessage="";
    };

    $scope.errorAddConsumptionCallback = function (error) {
        console.log(error);
        $scope.errormessage="Impossible to add new note; check if it's date is unique";
    };

    $scope.addConsumption = function () {
        var formattedDate = $filter('date')($scope.inputdate, 'yyyy-MM-dd');
        const obj = {
          date: formattedDate,
          coldWater: $scope.inputcoldWater,
          hotWater: $scope.inputhotWater,
          dayEnergy: $scope.inputdayEnergy,
          nightEnergy: $scope.inputnightEnergy
        };
        $http.post('/consumption/new', obj).then($scope.successAddConsumptionCallback, $scope.errorAddConsumptionCallback);
    };

    });