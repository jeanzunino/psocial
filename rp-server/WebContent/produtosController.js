(function () {
    'use strict';
    var app= angular.module('app');
        app.controller('ProdutosController', function($scope, $http, $window) {
  console.log('ProdutosController called .................');

  $scope.list = {};
  $scope.produto = {
    "id": 0,
    "nome": "",
    "codigoBarras": "",
    "categoria": null
  };

  $scope.produto.categoria = {
    "id":14,
    "nome":"Desconhecida",
    "pai":null
  };

  $scope.sucesso = false;
  $scope.filtro = "";

  $scope.load = function() {
    var url = 'rest/produtos';
    if($scope.filtro){
      url = url + "?filter="+$scope.filtro;
    }
    $http.get(url)
    .then(function(result) {
      $scope.list = result.data;
    }, function(error) {

    });
  }
  $scope.load();

  $scope.editar = function(item) {
    $scope.produto = item;
    $window.document.getElementById("nome").focus();
  }

  $scope.montaJson = function(item) {
    var json = $scope.produto;
    return json;
  }

  $scope.salvar = function() {
      var json = $scope.montaJson();
      var url = 'rest/produtos';
      if($scope.produto.id == 0){
        json.categoria = {
          "id":14,
          "nome":"Desconhecida",
          "pai":null
        };
        $http.post(url, json)
        .then(function(result) {
          $scope.sucesso = true;
        }, function(error) {

        });
      }else{
        $http.put(url, json)
        .then(function(result) {
          $scope.sucesso = true;
        }, function(error) {

        });
      }

  }

  $scope.excluir = function(item) {
    var url = 'rest/produtos/'+$scope.produto.id;
    $http.delete(url)
    .then(function(result) {
      $scope.sucesso = true;
        $scope.load();
    }, function(error) {

    });
  }
});
}());
