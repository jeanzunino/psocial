(function () {
    'use strict';
    var app= angular.module('app');
        app.controller('UsuariosController', function($scope, $http, $window) {
  console.log('UsuariosController called .................');

  $scope.list = {};
  $scope.usuario = {
    "id": 0,
    "nome": "",
    "email": ""
  };

  $scope.load = function() {
    var url = 'rest/usuarios';
    $http.get(url)
    .then(function(result) {
      $scope.list = result.data;
    }, function(error) {

    });
  }
  $scope.load();




  $scope.salvar = function() {
    var json = $scope.montaJson();
      var url = 'rest/usuarios';
      if($scope.usuario.id == 0){
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

  $scope.montaJson = function() {
    var json = $scope.usuario;
    return json;
  }

  $scope.editar = function(item) {
    $scope.usuario = item;
    $window.document.getElementById("nome").focus();
  }

  $scope.excluir = function(item) {
    var url = 'rest/usuarios/'+$scope.usuario.id;
    $http.delete(url)
    .then(function(result) {
      $scope.sucesso = true;
        $scope.load();
    }, function(error) {

    });
  }
});

}());
