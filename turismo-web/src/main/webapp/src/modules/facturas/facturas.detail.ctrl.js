(function(ng){
    var mod = ng.module("moduloFacturas");
    mod.controller("facturaDetailCtrl",["$scope","$state","$stateParams","$http","facturaContext",
        
        
        
        function($scope,$state,$stateParams,$http,context)
        {
          
            
// el controlador recibió un facturaId ??
// revisa los parámetros (ver el :facturaId en la definición de la ruta)
            if ($state.params.facturaId !== null && $state.params.facturaId !== undefined) {

                // toma el id del parámetro
                id = $state.params.facturaId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        });

                // el controlador no recibió un facturaId
            }
}
    ]);
    }
 )(window.angular);


