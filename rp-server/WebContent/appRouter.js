var app = angular.module("app", ["ngRoute"]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'clientes.html',
        controller: 'ClientesController'
    })
    .when('/clientes', {
        templateUrl: 'clientes.html',
        controller: 'ClientesController'
    })
    .when('/produtos', {
        templateUrl: 'produtos.html',
        controller: 'ProdutosController'
    })
    .when('/estabelecimentos', {
        templateUrl: 'estabelecimentos.html',
        controller: 'EstabelecimentosController'
    })
    .otherwise({ redirectTo: '/' });
}]);
