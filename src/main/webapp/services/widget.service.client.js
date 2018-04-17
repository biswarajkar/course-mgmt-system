(function(){
	angular
    .module("WebAppMaker")
    .factory("WidgetService", widgetService);
	
	function widgetService($http) {
        var api = {
        	"createWidget": createWidget,
            "findByWidgetId": findByWidgetId,
            "deleteByWidgetId": deleteByWidgetId,
            "updateByWidgetId": updateByWidgetId,
            "getAllWidgetsByPage": getAllWidgetsByPage
        };
        
        
        return api;
        
        function createWidget(pageId, widgetId, widget) {
            return $http({
            	//TODO
                //method: 'POST',
                //url: 'api/course/' + courseId + '/page',
                //data: JSON.stringify(page),
                //params: {
                	
                //}
            });
        }
        
        function getAllWidgetsByPage(pageId) {
        	console.log('Fetching widgets by ' + pageId);
        	return $http({
                method: 'GET',
                url: 'api/page/' + pageId + '/widget',
                params: {
                	
                }
            });
        }
        
        function findByWidgetId(widgetId) {
        	return $http({
                method: 'GET',
                url: 'api/widget/' + widgetId,
                params: {
                	
                }
            });
        }
        
        function deleteByWidgetId(widgetId) {
        	return $http({
                method: 'DELETE',
                url: 'api/widget/' + widgetId,
                params: {
                	
                }
            });
        }
        
        function updateByWidgetId(pageId, tabId, widget) {
        	return $http({
        		//TODO
//                method: 'PUT',
//                url: 'api/page/' + pageId,
//                params: {
//                	'name' : name,
//                	'tooltipDescription' : tooltipDescription,
//                }
            });
        }
        
        
	}
})();