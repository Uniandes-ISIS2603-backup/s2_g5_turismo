/**
 * @ngdoc overview
 * @name guias.module:guiaModule
 * @description
 * Definición del módulo de Angular de Guias. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Guias 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los guias en la 
 * URL: 'localhost:8080/guias/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar guias), el controlador y la vista 
 * correspondiente.
    | ESTADO          | URL                         | VISTAS          |
 * |------------------|----------------------------|------------------|
 * | guias            | /guias                     | mainView:        |
 * |                  |                            | guias.html       |
 * |                  |                            | guias.side.html  |
 * |                  |                            |                  |
 * | guiasList        | /list                      | listView:        |
 * |                  |                            | guias.list.html  |
 * |                  |                            | guias.side.html  |
 * |                  |                            |                  |
 * |guiasCreate       |/create                     | listView:        |
 * |                  |                            | /guias.new.html  |
 * |guiaUpdate        |/{guiaId:int}/update        | listView:        |
 * |                  |                            | /guias.new.html  |
 * |                  |                            |                  |
 * |guiasDelete       | /{guiaId:int}/delete       |listView:         |
 * |                  |                            |/guias.delete.html|
 * |                  |                            |                  |
 * |                  |                            |                  |
 * |                  |                            |                  |
 * |------------------|----------------------------|------------------|
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("guiaModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/guias/';
            // Mostrar la lista de guias será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/guiasList");
            // Definición del estado 'guiasList' donde se listan los guias
            $stateProvider.state('guias', {
                // Url que aparecerá en el browser
                url: '/guias',
                views: {
                    'sideViewGuias': {
                       templateUrl: basePath + 'guias.side.html'
                    },
                    'mainView': {
                        templateUrl: basePath + 'guias.html',
                        controller: 'guiaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                }).state('guiasList', {
                url: '/list',
                parent: 'guias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'guias.list.html'
                    },
                    'sideViewGuias': {
                        templateUrl: basePath + 'guias.side.html'
                    }
                }
                }).state('guiasCreate', {
                url: '/create/guias',
                parent: 'guias',
                views: {
                    'listView': {
                        templateUrl: basePath + '/new/guias.new.html',
                        controller: 'guiaNewCtrl'
                    }
                }
                }).state('guiaUpdate', {
                url: '/update/{guiaId:int}',
                parent: 'guias',
                param: {
                    guiaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/new/guias.new.html',
                        controller: 'guiaUpdateCtrl'
                    }
                }
                }).state('guiaDelete', {
                url: '/delete/{guiaId:int}',
                parent: 'guias',
                param: {
                    guiaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/delete/guias.delete.html',
                        controller: 'guiaDeleteCtrl'
                    }
                }
        });
    }]);
})(window.angular);
