angular.module('market', []).controller('indexController', function ($scope, $http) {
    $scope.fillTable = function () {
        $http.get('http://localhost:8080/api/v1/products')
            .then(function (response) {
                //$scope.products = response.data;
                $scope.products = response.data;
            });
        $http.get('http://localhost:8080/api/v1/cart')
            .then(function (response) {
                //$scope.products = response.data;
                $scope.cart = response.data;
                console.log($scope.cart);
            });
    };
    $scope.deleteProduct=function (id){
        $scope.removeItem(id);
        $http.delete('http://localhost:8080/api/v1/products/'+id)
            .then(function (response){
                $scope.fillTable();
            });
    };

    $scope.addProductAmount=function (id){
        $http.get('http://localhost:8080/api/v1/cart/add/'+id)
            .then(function (response){
                $scope.fillTable();
            });
    };

    $scope.subProductAmount=function (id){
        $http.get('http://localhost:8080/api/v1/cart/sub/'+id)
            .then(function (response){
                $scope.fillTable();
            });
    };

    $scope.addProductToCart=function (id){
        $http.get('http://localhost:8080/api/v1/cart/add/'+id)
            .then(function (response){
                $scope.fillTable();
            });
    };

    $scope.removeItem=function (id){
        $http.delete('http://localhost:8080/api/v1/cart/remove/'+id)
            .then(function (response){
                $scope.fillTable();
            });

    };

    $scope.createNewProduct = function () {
        // console.log($scope.newProduct);
        $http.post('http://localhost:8080/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    }

    $scope.fillTable();
});