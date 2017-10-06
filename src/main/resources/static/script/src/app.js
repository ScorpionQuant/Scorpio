'use strict';

var PRICING_DESTINATION = "/app/pricing";
var QUEUE_VALUATION = "/queue/valuation";
var TOPIC_STOCK = "/topic/stock/**"

var app = angular.module('Scorpio', []);

app.controller('MainController', ['$scope', function($scope) {

    $scope.interval = 500;
    $scope.disconnect_toggle = true;
    $scope.pricingRequest = {
        symbol: '0005.hk',
        calculatorType: 'GBSM',
        input: {
            type: 'EquityOptionInput',
            strikePrice: 100.0,
            underlyingPrice: null,
            riskFreeRate: 0.05,
            dividendRate: 0.02,
            volatility: null,
            timeToMaturity: 100,
            putCall: 'CALL',
            exerciseType: 'EUROPEAN'
        }
    };

    $scope.response = null;

    var socket;
    var stompClient;
    var tickTimer;

    $scope.connect = function() {
        if (stompClient && stompClient.connected) {
            alert("Already Connected");
        } else {
            socket = new SockJS('/endpoint');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
        }
    }

    $scope.disconnect = function() {
        if (stompClient && stompClient.connected) {
            stompClient.disconnect(onDisconnected);
        } else {
            $scope.disconnect_toggle = true;
            alert("Already Disconnected");
        }
    }

    $scope.sendMessage = function() {
        if (stompClient && stompClient.connected) {
            stompClient.send('/app/pricing', {}, JSON.stringify($scope.pricingRequest));
        } else {
            alert("No WebSocket Connection Yet!!!");
        }
    }

    $scope.startTimer = function() {
        $scope.stopTimer();
        tickTimer = setInterval(function() {
            $scope.pricingRequest.input.underlyingPrice = Math.floor(Math.random() * (110 - 90) + 90);
            $scope.pricingRequest.input.volatility = parseFloat(Math.random() * (0.5 - 0.05) + 0.05).toFixed(2);
            $scope.pricingRequest.input.timeToMaturity = Math.floor(Math.random() * (100 - 1) + 1);
            angular.element(document.querySelector('#send')).triggerHandler('click');
        }, $scope.interval);
    }

    $scope.stopTimer = function() {
        clearInterval(tickTimer)
    }

    var onError = function(error) {
        alert(error);
    };

    var onConnected = function() {
        $scope.disconnect_toggle = false;
        stompClient.subscribe(QUEUE_VALUATION, onMessage);
        $scope.$digest();
    };

    var onDisconnected = function() {
        $scope.disconnect_toggle = true;
        $scope.$digest();
    };

    var onMessage = function(message) {
        var destination = message.headers.destination;
        $scope.response = JSON.parse(message.body);
        $scope.$digest();
    };
}]);
