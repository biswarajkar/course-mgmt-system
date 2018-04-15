(function () {
    angular.module("WebAppMaker") // Single argument means we are reading and
    // using the
        .config(Config); // previously created module "WebAppMaker"
    // angular knows that WebAppMaker is declared

    function Config($routeProvider, $locationProvider) {
        $locationProvider.hashPrefix('!');
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
                    controllerAs: 'model'
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