(function () {
    angular
        .module("WebAppMaker")
        .factory("UserService", userService);

    function userService($http) {
        var api = {
            "findUserByCredentials": findUserByCredentials,
            "findUserById": findUserById,
            "updateUser": updateUser,
            "findUserByUsername": findUserByUsername,
            "createUser": createUser,
            "deleteUser": deleteUser,
            "findAllUsersByRole":findAllUsersByRole
        };
        return api;


        function createUser(user, userType) {

            return $http({
                method: 'POST',
                url: '/api/' + userType,
                data: JSON.stringify(user)
            });
        }

        function findUserByUsername(username) {
            return $http.get("/api/user?username=" + username);
        }

        function updateUser(userId, newUser) {
            return $http.put("/api/user/" + userId, newUser);
        }

        function findUserById(userId, roleType) {
            return $http.get("/api/" + roleType + "/" + userId);
        }

        function findUserByCredentials(username, password, roleType) {
            return $http({
                url: '/api/' + roleType + '/credentials',
                method: 'GET',
                params: {'username': username, 'password': password}
            });
        }

        function findAllUsersByRole(role) {
            return $http({
                url: '/api/find/user/role',
                method: 'GET',
                params: {role: role}
            });
        }

        function deleteUser(username, password) {
            return $http.delete("/api/user" + userId);
        }

   }
})();