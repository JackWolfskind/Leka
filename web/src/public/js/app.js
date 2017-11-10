var logger = {
    post: function () {
        var username = $("#username").val();
        var passsword = $("#password").val();
        var lastPath = $("#last_path").val();
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