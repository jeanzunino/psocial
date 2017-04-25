(function () {
    'use strict';
    var app= angular.module('app');
        app.controller('ClientesController', function($scope, $http, $window) {
  console.log('ClientesController called .................');

  $scope.tipoCategoriaCliente = {
    PESSOA_FISICA : {value: 0, name: "Pessoa Física", model: "FISICA"},
    PESSOA_JURIDICA : {value: 1, name: "Pessoa Jurídica", model: "JURIDICA"}
  }

  $scope.list = {};
  $scope.cliente = {
    "id": 0,
    "nome": "",
    "telefone": "",
    "categoriaCliente": $scope.tipoCategoriaCliente.PESSOA_FISICA,
    "cpfCnpj": ""
  };

  $scope.sucesso = false;
  $scope.filtro = "";

$scope.load = function() {
    var url = 'rest/clientes';
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

$scope.montaJson = function() {
  var json = $scope.cliente;
  json.categoriaCliente = $scope.tipoCategoriaCliente.PESSOA_FISICA.model;
  return json;
}

  $scope.salvar = function() {
      var json = $scope.montaJson();
      var urlClientes = 'rest/clientes';
      if($scope.cliente.id == 0){
        $http.post(urlClientes, json)
        .then(function(result) {
          $scope.sucesso = true;
          $scope.load();
        }, function(error) {

        });
      }else{
        $http.put(urlClientes, json)
        .then(function(result) {
          $scope.sucesso = true;
        }, function(error) {

        });
      }

  }

  $scope.editar = function(item) {
    $scope.cliente = item;
    $window.document.getElementById("nomeCliente").focus();
  }

  $scope.excluir = function(item) {
    var url = 'rest/clientes/'+$scope.cliente.id;
    $http.delete(url)
    .then(function(result) {
      $scope.sucesso = true;
      $scope.load();
    }, function(error) {

    });
  }
});

}());
