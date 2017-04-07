(function () {
    'use strict';
    var app= angular.module('app');
        app.controller('ProdutosController', function($scope, $http) {
  console.log('ProdutosController called .................');

  $scope.produtos = {};
  $scope.nome = "";
  $scope.codigoBarras = "";
  $scope.categoria = {};

    var url = 'rest/produtos';
    $http.get(url)
    .then(function(result) {
      $scope.produtos = result.data;
    }, function(error) {

    });

  $scope.addItem = function() {

  }

  $scope.salvar = function() {
      var json = {};
      json.nome = $scope.nome;
      json.codigoBarras = $scope.telefone;
      json.categoria = $scope.categoria;
      var url = 'rest/produtos';
      $http.post(url, json)
      .then(function(result) {

      }, function(error) {

      });
  }
});
}());
