import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;

/** @ngInject **/
function config($stateProvider: IStateProvider, properties) {
	
    $stateProvider.state('app.pesquisa-avancada.processos', {
        url: '/processos',
        views: {
            'content@app.autenticado': {
                templateUrl: 'app/main/pesquisa-avancada/pesquisa-avancada.html',
                controller: 'app.pesquisa-avancada.PesquisaAvancadaController',
                controllerAs: 'vm'
            }
        },
        resolve: {
            traits: /** @ngInject **/ ($http, properties) => {
                return $http.get(properties.apiUrl + '/services/pesquisa-avancada/processos/sample/traits.json')
                    .then(response => {
                        return angular.copy(response.data);
                    });
            },
            searchResults: /** @ngInject **/ ($http, properties) => {
                return $http.get(properties.apiUrl + '/services/api/processos')
                    .then(response => {
                        return angular.copy(response.data);
                    });
            },
            savedSearchs: /** @ngInject **/ ($http, properties) => {
                return $http.get(properties.apiUrl + '/services/pesquisa-avancada/processos/sample/saved-searchs.json')
                    .then( response => {
                        var savedSearchs = angular.copy(response.data);
                        savedSearchs.forEach(savedSearch => {
                            savedSearch.criterias.forEach((criteria: any) => {
                                if (criteria.trait.dataType === 'date') {
                                    criteria.value = _.isArray(criteria) ?
                                        [new Date(criteria.value[0]), new Date(criteria.value[1])]
                                        : new Date(criteria.value);
                                }
                            });
                        });
                        return savedSearchs;
                    });
            }
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService, properties) {
	
	$translatePartialLoader.addPart(properties.apiUrl + '/services/pesquisa-avancada/processos');
}

let pesquisaAvancadaProcessos: IModule = angular.module('app.pesquisa-avancada.processos', ['app.constants'])
pesquisaAvancadaProcessos.config(config).run(run);
export default pesquisaAvancadaProcessos;