(function () {
    angular.module("WebAppMaker") // Single argument means we are reading and
    // using the
        .config(Config); // previously created module "WebAppMaker"
    // angular knows that WebAppMaker is declared

    function Config($routeProvider, $locationProvider, $sceDelegateProvider) {
        $locationProvider.hashPrefix('!');
        $sceDelegateProvider.resourceUrlWhitelist([
            'self',
            'https://www.youtube.com/**',
            'https://docs.google.com/**'
          ]);
        $routeProvider
            /*.when(
                "/user/edituser/:uid",
                {
                    templateUrl: 'views/user/templates/edit-user.view.client.html',
                    controller: 'EditUserController',
                    controllerAs: 'model'
                })
            .when(
                "/user/:uid/course/add",
                {
                    templateUrl: 'views/course/templates/add-new-course.view.client.html',
                    controller: 'AddCourseController',
                    controllerAs: 'model'
                })
            .when(
                "/user/:uid/course/join/:cid",
                {
                    templateUrl: 'views/course/templates/join-drop-course.view.client.html',
                    controller: 'JoinDropCourseController',
                    controllerAs: 'model'
                })
            .when(
                "/user/:uid/course/edit/:ecid",
                {
                    templateUrl: 'views/course/templates/edit-course.view.client.html',
                    controller: 'EditCourseController',
                    controllerAs: 'model'
                })
            .when(
                "/user/:uid/course/modify/:cid",
                {
                    templateUrl: 'views/course/templates/add-delete-course.view.client.html',
                    controller: 'AddDeleteCourseController',
                    controllerAs: 'models'
                })
            .when(
                "/user/:uid/course/submit/assignment/:cid",
                {
                    templateUrl: 'views/assignment/templates/submit-assignment.view.client.html',
                    controller: 'SubmitAssignmentController',
                    controllerAs: 'model'
                })
            .when(
                "/user/:uid/course/add/assignment/:cid",
                {
                    templateUrl: 'views/assignment/templates/add-assignment.view.client.html',
                    controller: 'AddAssignmentController',
                    controllerAs: 'model'
                })
            .when(
                "/user/:uid/course/:cid",
                {
                    templateUrl: 'views/course/templates/manage-course.view.client.html',
                    controller: 'ManageCourseController',
                    controllerAs: 'model'
                })*/
        	.when(
                "/:roleType/:uid/course/:courseId/page/:pageId/widget/:widgetId/edit",
                {
                    templateUrl: 'views/widget/templates/edit-widget.view.client.html',
                    controller: 'EditWidgetController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/course/:courseId/page/:pageId/widget/add/:widgetType",
                {
                    templateUrl: 'views/widget/templates/add-new-widget.view.client.html',
                    controller: 'AddWidgetController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/course/:courseId/page/:pageId/widget",
                {
                    templateUrl: 'views/widget/templates/manage-widget.view.client.html',
                    controller: 'ManageWidgetController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/course/:courseId/page/add",
                {
                    templateUrl: 'views/page/templates/add-page.view.client.html',
                    controller: 'AddPageController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/course/:courseId/page/:pageId/edit",
                {
                    templateUrl: 'views/page/templates/edit-page.view.client.html',
                    controller: 'EditPageController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/course/:courseId/page",
                {
                    templateUrl: 'views/page/templates/manage-page.view.client.html',
                    controller: 'ManagePageController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/course/manage",
                {
                    templateUrl: 'views/course/templates/add-drop-course.view.client.html',
                    controller: 'AddDropCourseController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/edit/:managedRoleType/:manageduid",
                {
                    templateUrl: 'views/user/templates/edit-user.view.client.html',
                    controller: 'EditUserController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/manage/:manageRoleType",
                {
                    templateUrl: 'views/user/templates/manage-user.view.client.html',
                    controller: 'ManageUserController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/edit/course/:courseId/course",
                {
                    templateUrl: 'views/course/templates/edit-course.view.client.html',
                    controller: 'EditCourseController',
                    controllerAs: 'model'
                })
        	.when(
                "/:roleType/:uid/add/course",
                {
                    templateUrl: 'views/course/templates/add-new-course.view.client.html',
                    controller: 'AddCourseController',
                    controllerAs: 'model'
                })
            .when(
                "/user/:roleType/:uid",
                {
                    templateUrl: 'views/user/templates/dashboard.view.client.html',
                    controller: 'DashboardController',
                    controllerAs: 'model'
                })
            .when("/", {
                templateUrl: 'views/user/templates/home.view.client.html',
                controller: 'HomeController',
                controllerAs: 'model'
            })
    }
})();