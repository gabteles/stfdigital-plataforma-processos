import ITranslatePartialLoaderService = angular.translate.ITranslatePartialLoaderService;
import IStateProvider = angular.ui.IStateProvider;
import IModule = angular.IModule;
import Properties = app.support.constants.Properties;

/** @ngInject **/
function config($stateProvider: IStateProvider, properties: Properties) {
	
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
            searchConfig : /** @ngInject **/ ($http, $q, $translatePartialLoader) => {
                let traits = $http.get(properties.apiUrl + '/processos/pesquisa-avancada/config/traits.json');
                let resultColumns = $http.get(properties.apiUrl + '/processos/pesquisa-avancada/config/result-columns.json');
                let api = properties.apiUrl + '/processos/api/pesquisa-avancada';
                let context = 'processos';
                
                return $q.all([traits, resultColumns])
                         .then(results => {
                             return {
                                 traits : results[0].data,
                                 resultColumns: results[1].data,
                                 api: api,
                                 context: context
                             }
                         });
            }
        }
    });
}

/** @ngInject **/
function run($translatePartialLoader: ITranslatePartialLoaderService, properties: Properties) {
    
	$translatePartialLoader.addPart(properties.apiUrl + '/processos/pesquisa-avancada');
}

let pesquisaAvancadaProcessos: IModule = angular.module('app.pesquisa-avancada.processos', ['app.support'])
pesquisaAvancadaProcessos.config(config).run(run);

export default pesquisaAvancadaProcessos;