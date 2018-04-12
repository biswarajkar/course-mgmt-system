(function () {
    angular
        .module("WebAppMaker")
        .controller("DashboardController", DashboardController);


    function DashboardController(UserService, CourseService,$location, $routeParams) {
        var viewModel = this;

        function init() {
            var userId = $routeParams['uid'];
            var promise = UserService.findUserById(userId);
            promise.then(
                function (user) {
                    user = user.data;
                    if (user != undefined) {
                        viewModel.user = user;
                    } else {
                        viewModel.errorMessage = "Error while loading user by ID:" + userId;
                    }
                }
            );

            var promise = CourseService.findAllCoursesByProfessorId(userId);
            promise.then(
                function (response) {
                    manage_courses = response.data;
                    if (manage_courses != undefined) {
                        viewModel.manage_courses = manage_courses;
                    } else {
                        viewModel.errorMessage = "Error loading courses";
                    }
                }
            );

            var promise = CourseService.findAllCoursesByStudentId(userId);
            promise.then(
                function (response) {
                    registered_courses_homework = response.data;
                    if (registered_courses_homework != undefined) {
                        viewModel.registered_courses_homework = registered_courses_homework;
                    } else {
                        viewModel.errorMessage = "Error loading courses";
                    }
                }
            );

            var promise = CourseService.findAllCoursesByAssistantId(userId);
            promise.then(
                function (response) {
                    registered_courses_assistant = response.data;
                    if (registered_courses_assistant != undefined) {
                        viewModel.registered_courses_assistant = registered_courses_assistant;
                    } else {
                        viewModel.errorMessage = "Error loading courses";
                    }
                }
            );


        }

        init();
    }
})();