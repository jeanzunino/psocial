(function () {
    'use strict';
    var app= angular.module('app');
        app.controller('EstabelecimentosController', function($scope, $http, $window) {
  console.log('EstabelecimentosController called .................');

  $scope.list = {};
  $scope.nome = "";
  $scope.cnpj = "";
  $scope.endereco = "";
  $scope.estadoSelecionado = {};
  $scope.cidadeSelecionada = {};

    var url = 'rest/estabelecimentos';
    $http.get(url)
    .then(function(result) {
      $scope.list = result.data;
    }, function(error) {

    });

  $scope.addItem = function() {

  }

  $scope.salvar = function() {

    //TODO Falta carregar estados e cidades para apresentar em uma pesquisa
      var json = {};
      json.nome = $scope.nome;
      json.cnpj = $scope.cnpj;
      json.endereco = $scope.endereco;
      json.estado = $scope.estadoSelecionado;
      json.cidade = $scope.cidadeSelecionada;
      var url = 'rest/estabelecimentos';
      $http.post(url, json)
      .then(function(result) {

      }, function(error) {

      });
  }
});

}());
