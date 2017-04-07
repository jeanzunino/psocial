(function () {
    'use strict';
    var app= angular.module('app');
        app.controller('ClientesController', function($scope, $http) {
  console.log('ClientesController called .................');

  $scope.tipoCategoriaCliente = {
    PESSOA_FISICA : {value: 0, name: "Pessoa Física", model: "FISICA"},
    PESSOA_JURIDICA : {value: 1, name: "Pessoa Jurídica", model: "JURIDICA"}
  }

  $scope.clientes = {};
  $scope.nome = "";
  $scope.telefone = "";
  $scope.categoriaCliente = $scope.tipoCategoriaCliente.PESSOA_FISICA;
  $scope.cpfCnpj = "";



    var urlClientes = 'rest/clientes';
    $http.get(urlClientes)
    .then(function(result) {
      $scope.clientes = result.data;
    }, function(error) {

    });

  $scope.addItem = function() {

  }

  $scope.salvar = function() {
      var json = {};
      json.nome = $scope.nome;
      json.telefone = $scope.telefone;
      json.categoriaCliente = $scope.categoriaCliente.model;
      json.cpfCnpj = $scope.cpfCnpj;
      var urlClientes = 'rest/clientes';
      $http.post(urlClientes, json)
      .then(function(result) {

      }, function(error) {

      });
  }
});

}());
