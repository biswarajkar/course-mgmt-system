(function () {
    angular
        .module("WebAppMaker")
        .controller("EditUserController", EditUserController);


    function EditUserController(UserService, $location, $routeParams) {
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
        }

        init();
    }
})();