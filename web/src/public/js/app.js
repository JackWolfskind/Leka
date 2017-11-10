var logger = {
    post: function () {
        var username = $("username");
        var passsword = $("password");
        var lastPath = $("last_path");
        debugger;
        $.post("/login", {
            username: username,
            passsword: passsword,
            lastPath: lastPath

        }).then(function (response) {
            debugger;
        }, function (response) {
            debugger;
        });
    }
};