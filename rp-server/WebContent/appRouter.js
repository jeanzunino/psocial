var app = angular.module("app", ["ngRoute"]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'cadastro_cliente.html',
        controller: 'ClientesController'
    })
    .when('/clientes', {
        templateUrl: 'cadastro_cliente.html',
        controller: 'ClientesController'
    })
    .when('/produtos', {
        templateUrl: 'cadastro_produto.html',
        controller: 'ProdutosController'
    })
    .when('/estabelecimentos', {
        templateUrl: 'cadastro_estabelecimento.html',
        controller: 'EstabelecimentosController'
    })
    .when('/usuarios', {
        templateUrl: 'cadastro_usuario.html',
        controller: 'UsuariosController'
    })
    .otherwise({ redirectTo: '/' });
}]);
